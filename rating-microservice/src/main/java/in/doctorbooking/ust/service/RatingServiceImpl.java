package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Rating;
import in.doctorbooking.ust.exceptions.RatingNotFoundException;
import in.doctorbooking.ust.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{


    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAll() {
        List<Rating> test = ratingRepository.findAll();
        if(test.isEmpty()||test==null){
            throw new RatingNotFoundException("No ratings found");
        }
        return test;
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Double getReviewByDoctor(Integer doctorId) {
        List<Rating> list = ratingRepository.findByDoctorId(doctorId);
        double averageRating = list.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);
        return averageRating;
    }

    @Override
    public List<Rating> getRatingById(int id) {
        List<Rating> list = ratingRepository.findByUserId(id);
        if(list.isEmpty()){
            throw new RatingNotFoundException("No ratings found");
        }
        return list;
    }

}
