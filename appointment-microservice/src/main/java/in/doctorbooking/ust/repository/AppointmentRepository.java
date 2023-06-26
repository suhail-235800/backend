package in.doctorbooking.ust.repository;

import in.doctorbooking.ust.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    Appointment findByAppointmentDateAndAppointmentTime(LocalDate date, LocalTime time);
    Appointment findByDoctorId(int doctorId);

    List<Appointment> findByUserId(int userId);

    List<Appointment> findByDoctorName(String doctorName);
}
