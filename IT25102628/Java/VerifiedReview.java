package com.example.medicalstore.review;

public class VerifiedReview extends Review {

    public VerifiedReview(String reviewId, String userId,
                          String medId, int rating, String comment) {
        super(reviewId, userId, medId, rating, comment);
    }

    @Override
    public String getDisplayLabel() {
        return "VERIFIED";
    }
}