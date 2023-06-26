package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Appointment;
import in.doctorbooking.ust.exceptions.AppointmentNotFoundException;
import in.doctorbooking.ust.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository mockAppointmentRepository;

    private AppointmentServiceImpl appointmentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        appointmentServiceImplUnderTest = new AppointmentServiceImpl(mockAppointmentRepository);
    }

    @Test
    void testFindAllAppointments() {
        // Setup
        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        final List<Appointment> expectedResult = List.of(appointment);

        // Configure AppointmentRepository.findAll(...).
        final Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(0);
        appointment1.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment1.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment1.setDoctorId(0);
        appointment1.setDoctorName("doctorName");
        final List<Appointment> appointments = List.of(appointment1);
        when(mockAppointmentRepository.findAll()).thenReturn(appointments);

        // Run the test
        final List<Appointment> result = appointmentServiceImplUnderTest.findAllAppointments();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllAppointments_AppointmentRepositoryReturnsNull() {
        // Setup
        when(mockAppointmentRepository.findAll()).thenReturn(Collections.emptyList());

        // Create an instance of AppointmentServiceImpl with the mocked repository
        AppointmentService appointmentService = new AppointmentServiceImpl(mockAppointmentRepository);

        // Run the test
        Exception exception = assertThrows(AppointmentNotFoundException.class, () -> {
            appointmentService.findAllAppointments();
        });

        // Verify the exception message
        String expectedMessage = "No appointment found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testFindAllAppointments_AppointmentRepositoryReturnsNoItems() {
        // Setup
        when(mockAppointmentRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> appointmentServiceImplUnderTest.findAllAppointments())
                .isInstanceOf(AppointmentNotFoundException.class);
    }

    @Test
    void testSaveAppointment() {
        // Setup
        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");

        // Run the test
        appointmentServiceImplUnderTest.saveAppointment(appointment);

        // Verify the results
        // Confirm AppointmentRepository.save(...).
        final Appointment entity = new Appointment();
        entity.setAppointmentId(0);
        entity.setAppointmentDate(LocalDate.of(2020, 1, 1));
        entity.setAppointmentTime(LocalTime.of(0, 0, 0));
        entity.setDoctorId(0);
        entity.setDoctorName("doctorName");
        verify(mockAppointmentRepository).save(entity);
    }

    @Test
    void testGetAppointmentById() {
        // Setup
        final Appointment expectedResult = new Appointment();
        expectedResult.setAppointmentId(0);
        expectedResult.setAppointmentDate(LocalDate.of(2020, 1, 1));
        expectedResult.setAppointmentTime(LocalTime.of(0, 0, 0));
        expectedResult.setDoctorId(0);
        expectedResult.setDoctorName("doctorName");

        // Configure AppointmentRepository.findById(...).
        final Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(0);
        appointment1.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment1.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment1.setDoctorId(0);
        appointment1.setDoctorName("doctorName");
        final Optional<Appointment> appointment = Optional.of(appointment1);
        when(mockAppointmentRepository.findById(0)).thenReturn(appointment);

        // Run the test
        final Appointment result = appointmentServiceImplUnderTest.getAppointmentById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAppointmentById_AppointmentRepositoryReturnsAbsent() {
        // Setup
        when(mockAppointmentRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> appointmentServiceImplUnderTest.getAppointmentById(0))
                .isInstanceOf(AppointmentNotFoundException.class);
    }

    @Test
    void testGetDoctorAppointmentsBydateandtime() {
        // Setup
        final Appointment expectedResult = new Appointment();
        expectedResult.setAppointmentId(0);
        expectedResult.setAppointmentDate(LocalDate.of(2020, 1, 1));
        expectedResult.setAppointmentTime(LocalTime.of(0, 0, 0));
        expectedResult.setDoctorId(0);
        expectedResult.setDoctorName("doctorName");

        // Configure AppointmentRepository.findByAppointmentDateAndAppointmentTime(...).
        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        when(mockAppointmentRepository.findByAppointmentDateAndAppointmentTime(LocalDate.of(2020, 1, 1),
                LocalTime.of(0, 0, 0))).thenReturn(appointment);

        // Run the test
        final Appointment result = appointmentServiceImplUnderTest.getDoctorAppointmentsBydateandtime(0,
                LocalDate.of(2020, 1, 1), LocalTime.of(0, 0, 0));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
