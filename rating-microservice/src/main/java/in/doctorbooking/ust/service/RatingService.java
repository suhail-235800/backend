package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAll();
    Rating saveRating(Rating rating);
    Double getReviewByDoctor(Integer doctorId);

    List<Rating> getRatingById(int id);
}
