package com.example.medicalstore.review;


public abstract class Review {

    private String reviewId;
    private String userId;
    private String medId;
    private int rating;
    private String comment;

    public Review(String reviewId, String userId, String medId, int rating, String comment) {
        this.reviewId = reviewId;
        this.userId   = userId;
        this.medId    = medId;
        this.rating   = rating;
        this.comment  = comment;
    }

    public String getReviewId() {
        return reviewId;
    }
    public String getUserId() {
        return userId;
    }
    public String getMedId(){
        return medId;
    }
    public int getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setRating(int rating){
        this.rating = rating;
    }

    public abstract String getDisplayLabel();

    public String toFileString() {
        return reviewId+","+userId+","+medId+","+rating+","+comment+","+getDisplayLabel();
    }

    public static Review fromFileString(String line) {
        String[] p = line.split(",", 6);
        return new VerifiedReview(p[0], p[1], p[2], Integer.parseInt(p[3]), p[4]);
    }
}