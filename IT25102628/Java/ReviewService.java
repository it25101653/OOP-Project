package com.example.medicalstore.review;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private static final String FILE = "data/reviews.txt";

    public List<Review> getAllReviews() {
        List<Review> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (!line.trim().isEmpty())
                    list.add(Review.fromFileString(line.trim()));
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }

    public List<Review> getReviewsForMedicine(String medId) {
        return getAllReviews().stream()
                .filter(r -> r.getMedId().equals(medId))
                .collect(Collectors.toList());
    }

    public void addReview(String userId, String medId, int rating, String comment) {
        String id = "REV" + System.currentTimeMillis();
        VerifiedReview r = new VerifiedReview(id, userId, medId, rating, comment);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(r.toFileString()); bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void update(String reviewId, int newRating, String newComment) {
        List<Review> list = getAllReviews();
        for (Review r : list)
            if (r.getReviewId().equals(reviewId)) {
                r.setRating(newRating);
                r.setComment(newComment);
            }
        saveAll(list);
    }

    public void delete(String reviewId) {
        List<Review> list = getAllReviews().stream()
                .filter(r -> !r.getReviewId().equals(reviewId))
                .collect(Collectors.toList());
        saveAll(list);
    }

    private void saveAll(List<Review> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, false))) {
            for (Review r : list) { bw.write(r.toFileString()); bw.newLine(); }
        } catch (IOException e) { e.printStackTrace(); }
    }
}