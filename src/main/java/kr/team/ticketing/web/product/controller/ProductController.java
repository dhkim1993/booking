package kr.team.ticketing.web.product.controller;

import kr.team.ticketing.config.auth.LoginUser;
import kr.team.ticketing.config.auth.dto.SessionUser;
import kr.team.ticketing.service.comment.CommentService;
import kr.team.ticketing.service.product.InfoByDateService;
import kr.team.ticketing.service.product.ProductService;
import kr.team.ticketing.web.product.dto.InfoByDateResponseDto;
import kr.team.ticketing.web.product.dto.ProductOptionResponseDto;
import kr.team.ticketing.web.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CommentService commentService;
    private final InfoByDateService infoByDateService;

    @GetMapping
    public String product_save(Model model) {
        model.addAttribute("categories", productService.getCategoriesResponseDto());
        return "/product/create";
    }

    @GetMapping("/detail/{id}")
    public String product_detail(@PathVariable("id") Long productId,
                                 @LoginUser SessionUser user,
                                 Model model) {
        if (user != null) {
            model.addAttribute("memberId", user.getId());
        }
        List<Long> grades = commentService.searchByGradeComments(productId);
        model.addAttribute("grade5", grades.get(0));
        model.addAttribute("grade4", grades.get(1));
        model.addAttribute("grade3", grades.get(2));
        model.addAttribute("grade2", grades.get(3));
        model.addAttribute("grade1", grades.get(4));
        model.addAttribute("product", productService.findByIdResponseDto(productId));
        Long totalCnt = commentService.getTotalCnt(productId);
        if (totalCnt != 0) {
            model.addAttribute("avg", commentService.getAvg(productId));
        }
        model.addAttribute("total", totalCnt);
        model.addAttribute("comments", commentService.findAllDtoByProductId(productId));
        return "/product/detail";
    }

    @GetMapping("/update/{id}")
    public String product_update(Model model, @PathVariable("id") Long id) {
        ProductResponseDto productDto = productService.findByIdResponseDto(id);
        List<ProductOptionResponseDto> productOptionResponseDtos = productService.optionResponseDtos(id);
        ProductOptionResponseDto option1 = productOptionResponseDtos.get(0);
        ProductOptionResponseDto option2 = productOptionResponseDtos.get(1);
        ProductOptionResponseDto option3 = productOptionResponseDtos.get(2);
        model.addAttribute("product", productDto);
        model.addAttribute("option1", option1);
        model.addAttribute("option2", option2);
        model.addAttribute("option3", option3);
        if (productDto.getCategoryId() != 4) {
            InfoByDateResponseDto infoByDate = infoByDateService.getWeekEndDay(id);
            model.addAttribute("firstTime", infoByDate.getFirstTime());
            model.addAttribute("secondTime", infoByDate.getSecondTime());
            model.addAttribute("thirdTime", infoByDate.getThirdTime());
            model.addAttribute("fourthTime", infoByDate.getFourthTime());
        }
        return "/product/update";
    }
}
