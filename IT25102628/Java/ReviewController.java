package com.example.medicalstore.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public String showReviews(@RequestParam(required = false) String medId,
                              Model model) {
        List<Review> reviews = (medId != null && !medId.isEmpty())
                ? reviewService.getReviewsForMedicine(medId)
                : reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @PostMapping("/reviews/add")
    public String addReview(@RequestParam String userId,
                            @RequestParam String medId,
                            @RequestParam int rating,
                            @RequestParam String comment) {
        reviewService.addReview(userId, medId, rating, comment);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/edit")
    public String editReview(@RequestParam String reviewId,
                             @RequestParam int rating,
                             @RequestParam String comment) {
        reviewService.update(reviewId, rating, comment);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/delete")
    public String deleteReview(@RequestParam String id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }
}