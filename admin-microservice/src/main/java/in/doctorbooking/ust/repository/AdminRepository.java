package in.doctorbooking.ust.repository;

import in.doctorbooking.ust.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Doctor,Integer> {

    Doctor findByDoctorId(int id);

    List<Doctor> findByDoctorName(String doctorName);

    List<Doctor> findByDoctorSpecialization(String specialization);

    List<Doctor> findByDoctorNameAndDoctorSpecialization(String doctorName, String doctorSpecialization);

    List<Doctor> findByDoctorNameAndDoctorLocation(String doctorName, String doctorLocation);

    List<Doctor> findByDoctorLocation(String doctorLocation);

    List<Doctor> findByDoctorNameAndDoctorLocationAndDoctorSpecialization(String doctorName, String doctorLocation, String doctorSpecialization);

    List<Doctor> findByDoctorLocationAndDoctorSpecialization(String doctorLocation, String doctorSpecialization);
    @Query("SELECT d FROM Doctor d WHERE d.doctorName LIKE :word%")
    List<Doctor> findBydoctorNamecontaining(@Param("word") String word);

}