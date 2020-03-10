package kr.team.ticketing.service.reservation;

import kr.team.ticketing.core.domain.coupon.Coupon;
import kr.team.ticketing.core.domain.product.InfoByDate;
import kr.team.ticketing.core.domain.product.Product;
import kr.team.ticketing.core.domain.reservation.Reservation;
import kr.team.ticketing.core.domain.reservation.ReservationOption;
import kr.team.ticketing.core.domain.reservation.ReservationRepository;
import kr.team.ticketing.core.validator.CouponValidator;
import kr.team.ticketing.core.validator.ReservationValidator;
import kr.team.ticketing.service.converter.ReservationConverter;
import kr.team.ticketing.service.coupon.CouponService;
import kr.team.ticketing.service.product.InfoByDateService;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.web.reservation.dto.ReservationResponseDto;
import kr.team.ticketing.web.reservation.dto.ReservationSaveDto;
import kr.team.ticketing.web.reservation.dto.SelectedDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static kr.team.ticketing.core.domain.reservation.ReservationOption.createReservationOptions;
import static kr.team.ticketing.support.utils.DateTimeUtils.createDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ProductService productService;
    private final CouponService couponService;
    private final InfoByDateService infoByDateService;
    private final ReservationValidator reservationValidator;
    private final CouponValidator couponValidator;
    private final ReservationConverter reservationConverter;


    @Transactional
    public ReservationResponseDto getReservationDto(Long id) {
        return reservationConverter.convert(reservationRepository.findById(id).get());
    }

    @Transactional
    public Long selectedDataUpdate(SelectedDataDto selectedDataDto) {
        Reservation reservation = reservationRepository.findById(selectedDataDto.getReservationId()).get();
        Product product = productService.findById(selectedDataDto.getProductId());
        List<ReservationOption> reservationOptions = createReservationOptions(product.getOptions(), selectedDataDto);
        return reservation.update(selectedDataDto, reservationOptions);
    }

    //결제페이지로 데이터 넘기기 위한 저장
    @Transactional
    public Long selectedDataSave(SelectedDataDto selectedDataDto) {
        Product product = productService.findById(selectedDataDto.getProductId());
        List<ReservationOption> reservationOptions = createReservationOptions(product.getOptions(), selectedDataDto);
        return reservationRepository.save(selectedDataDto.toEntity(reservationOptions)).getId();
    }

    @Transactional
    public void reservationSave(ReservationSaveDto reservationSaveDto) {
        Reservation selectedData = reservationRepository.findById(reservationSaveDto.getSelectedDataId()).get();
        Product product = productService.findById(selectedData.getProductId());
        if (couponValidator.checkDiscount(reservationSaveDto.getCouponId())) {
            Coupon coupon = couponService.findById(reservationSaveDto.getCouponId());
            couponValidator.changeCouponStatus(coupon);
        }
        reservationValidator.forBookingValidation(product, selectedData);
        selectedData.addFinalPrice(reservationSaveDto.getFinalPrice());
        selectedData.addMemberId(reservationSaveDto.getMemberId());
        reservationRepository.save(selectedData);
    }

    @Transactional
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

    @Transactional
    public void cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        Product product = productService.findById(reservation.getProductId());
        if (product.getCategoryId() != 4) {
            InfoByDate reservationDateInfo = infoByDateService.findSelectDateInfo(product.getInfoByDates(), reservation.getSelectDate().toString());
            reservationDateInfo.plusQuantity(reservation.getSelectTime(), reservation.getTotalCnt());
        }
        reservation.cancel();
    }

    @Transactional
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
