package springboot.jpa.booking.service.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.jpa.booking.core.domain.coupon.Coupon;
import springboot.jpa.booking.core.domain.product.InfoByDate;
import springboot.jpa.booking.core.domain.product.Product;
import springboot.jpa.booking.core.domain.reservation.Reservation;
import springboot.jpa.booking.core.domain.reservation.ReservationOption;
import springboot.jpa.booking.core.domain.reservation.ReservationRepository;
import springboot.jpa.booking.core.validator.CouponValidator;
import springboot.jpa.booking.core.validator.ReservationValidator;
import springboot.jpa.booking.service.converter.ReservationConverter;
import springboot.jpa.booking.service.coupon.CouponService;
import springboot.jpa.booking.service.product.InfoByDateService;
import springboot.jpa.booking.service.product.ProductService;
import springboot.jpa.booking.web.reservation.dto.ReservationResponseDto;
import springboot.jpa.booking.web.reservation.dto.ReservationSaveDto;
import springboot.jpa.booking.web.reservation.dto.SelectedDataDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static springboot.jpa.booking.core.domain.reservation.ReservationOption.createReservationOptions;
import static springboot.jpa.booking.support.utils.DateTimeUtils.createDateTime;


@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final CouponService couponService;
    private final InfoByDateService infoByDateService;
    private final ReservationValidator reservationValidator;
    private final CouponValidator couponValidator;
    private final ReservationConverter reservationConverter;


    public ReservationResponseDto getReservationDto(Long id) {
        return reservationConverter.convert(reservationRepository.findById(id).get());
    }

    public Long selectedDataUpdate(SelectedDataDto selectedDataDto) {
        Reservation reservation = reservationRepository.findById(selectedDataDto.getReservationId()).get();
        Product product = productService.findById(selectedDataDto.getProductId());
        List<ReservationOption> reservationOptions = createReservationOptions(product.getOptions(), selectedDataDto);
        return reservation.update(selectedDataDto, reservationOptions);
    }

    //결제페이지로 데이터 넘기기 위한 저장
    public Long selectedDataSave(SelectedDataDto selectedDataDto) {
        Product product = productService.findById(selectedDataDto.getProductId());
        List<ReservationOption> reservationOptions = createReservationOptions(product.getOptions(), selectedDataDto);
        return reservationRepository.save(selectedDataDto.toEntity(reservationOptions)).getId();
    }

    public void reservationSave(ReservationSaveDto reservationSaveDto) {
        Reservation selectedData = reservationRepository.findById(reservationSaveDto.getSelectedDataId()).get();
        Product product = productService.findById(selectedData.getProductId());
        reservationValidator.forBookingValidation(product, selectedData);
        selectedData.addFinalPrice(reservationSaveDto.getFinalPrice());
        selectedData.addMemberId(reservationSaveDto.getMemberId());
        if (couponValidator.checkDiscount(reservationSaveDto.getCouponId())) {
            Coupon coupon = couponService.findById(reservationSaveDto.getCouponId());
            couponValidator.changeCouponStatus(coupon);
            selectedData.addCouponId(reservationSaveDto.getCouponId());
        }
        reservationRepository.save(selectedData);
    }

    public void checkReservationStatus(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            LocalDate selectDate = reservation.getSelectDate();
            String selectTime = reservation.getSelectTime();
            if (selectDate != null) {
                LocalDateTime dateTime = createDateTime(selectDate+" "+selectTime);
                if (LocalDateTime.now().isAfter(dateTime)) {
                    reservation.changeToAfter();
                }
            }
        }
    }

    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        Product product = productService.findById(reservation.getProductId());
        if (product.getCategoryId() != 4 && reservationValidator.isAfterTime(reservation)) {
            InfoByDate reservationDateInfo = infoByDateService.findSelectDateInfo(product.getInfoByDates(), reservation.getSelectDate().toString());
            reservationDateInfo.plusQuantity(reservation.getSelectTime(), reservation.getTotalCnt());
        }
        if (reservation.getCouponId() != null) {
            Coupon coupon = couponService.findById(reservation.getCouponId());
            couponValidator.checkExpirationDate(coupon);
            coupon.cancelThenStatusChangeToBefore();
        }
        reservation.cancel();
    }

    public List<ReservationResponseDto> getReservationDtosByMemberId(Long memberId) {
        List<Reservation> reservations = findByMemberId(memberId);
        checkReservationStatus(reservations);
        return reservations.stream().map(reservationConverter::convert).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Reservation> findByMemberId(Long memberId) {return reservationRepository.findByMemberId(memberId);}
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {return reservationRepository.findAll();}
    @Transactional(readOnly = true)
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).get();
    }
}
