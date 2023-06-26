package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    List<Appointment> findAllAppointments();

    void saveAppointment(Appointment appointment);

    Appointment getAppointmentById(int id);

    Appointment getDoctorAppointmentsBydateandtime(int i, LocalDate localDate, LocalTime localTime);

    List<Appointment> findAppointmentsByUserId(int userId);

    List<Appointment> findAppointmentsByDoctorName(String doctorName);
}
