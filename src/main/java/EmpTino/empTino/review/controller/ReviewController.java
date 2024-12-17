package EmpTino.empTino.review.controller;

import EmpTino.empTino.review.domain.Review;
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
@RequiredArgsConstructor
@RequestMapping("api/reviews")
public class ReviewController {

    public final ReviewService reviewService;

    @PostMapping
    public String createReview(@ModelAttribute Review review) {
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

    @GetMapping
    public String findAllReview(Model model) {
        List<Review> reviews = reviewService.findAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviewList";
    }

    @GetMapping("/news")
    public String newReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "reviewForm";
    }

}
