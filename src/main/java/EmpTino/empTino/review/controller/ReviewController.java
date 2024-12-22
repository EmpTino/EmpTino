package EmpTino.empTino.review.controller;

import EmpTino.empTino.review.domain.ReviewDAO;
import EmpTino.empTino.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public String createReview(@ModelAttribute ReviewDAO reviewDAO) {
        reviewService.createReview(reviewDAO);
        return "redirect:/reviews";
    }

    @GetMapping
    public String findAllReview(Model model) {
        List<ReviewDAO> reviewDAOS = reviewService.findAllReviews();
        model.addAttribute("reviews", reviewDAOS);
        return "reviewList";
    }

    @GetMapping("/news")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new ReviewDAO());
        return "reviewForm";
    }

}
