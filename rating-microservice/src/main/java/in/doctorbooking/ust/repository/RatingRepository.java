package in.doctorbooking.ust.repository;

import in.doctorbooking.ust.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Integer> {

    List<Rating> findByDoctorId(int doctorId);
    List<Rating> findByUserId(int userId);
}
