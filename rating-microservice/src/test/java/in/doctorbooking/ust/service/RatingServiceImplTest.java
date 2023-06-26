package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Rating;
import in.doctorbooking.ust.exceptions.RatingNotFoundException;
import in.doctorbooking.ust.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ratingService = new RatingServiceImpl(ratingRepository);
    }

    @Test
    void getAll_WhenRatingsExist_ShouldReturnListOfRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating());
        ratings.add(new Rating());
        when(ratingRepository.findAll()).thenReturn(ratings);

        List<Rating> result = ratingService.getAll();

        assertNotNull(result);
        assertEquals(ratings.size(), result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void getAll_WhenNoRatingsExist_ShouldThrowException() {
        when(ratingRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(RatingNotFoundException.class, () -> ratingService.getAll());

        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void saveRating_ShouldSaveRating() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.saveRating(rating);

        assertNotNull(result);
        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }
}
