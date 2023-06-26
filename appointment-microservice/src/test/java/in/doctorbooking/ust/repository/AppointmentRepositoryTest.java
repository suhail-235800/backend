package in.doctorbooking.ust.repository;

import in.doctorbooking.ust.domain.Appointment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        // Perform any setup operations if needed
    }

    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
    }

    @Test
    void findByAppointmentDateAndAppointmentTime() {
        // Create test data
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);
        appointment.setAppointmentDate(LocalDate.of(2021, 6, 1));
        appointment.setAppointmentTime(LocalTime.of(9, 0));
        appointmentRepository.save(appointment);

        // Perform the search
        Appointment foundAppointment = appointmentRepository.findByAppointmentDateAndAppointmentTime(
                LocalDate.of(2021, 6, 1), LocalTime.of(9, 0));

        // Assert the result
        assertNotNull(foundAppointment);
        assertEquals(1, foundAppointment.getAppointmentId());
        assertEquals(LocalDate.of(2021, 6, 1), foundAppointment.getAppointmentDate());
        assertEquals(LocalTime.of(9, 0), foundAppointment.getAppointmentTime());
    }


    @Test
    void findByDoctorId() {
        // Create test data
        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(1);
        appointment1.setDoctorId(100);
        appointmentRepository.save(appointment1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentId(2);
        appointment2.setDoctorId(200);
        appointmentRepository.save(appointment2);

        // Perform the search
        Appointment foundAppointment = appointmentRepository.findByDoctorId(100);

        // Assert the result
        assertNotNull(foundAppointment);
        assertEquals(1, foundAppointment.getAppointmentId());
        assertEquals(100, foundAppointment.getDoctorId());
    }
}
