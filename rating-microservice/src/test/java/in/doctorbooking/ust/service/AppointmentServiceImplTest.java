package in.doctorbooking.ust.service;

import in.doctorbooking.ust.dto.AppointmentDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @Test
    void testGetAppointment_ValidId() {
        // Setup
        int appointmentId = 123; // Specify the desired appointmentId
        AppointmentDto expectedDto = new AppointmentDto(appointmentId, LocalDate.now(), LocalTime.now(), 1,
                "Doctor Name", "Speciality", "Location", 1);
        RestTemplate restTemplate = mock(RestTemplate.class);
        AppointmentServiceImpl appointmentService = new AppointmentServiceImpl(restTemplate);

        // Configure restTemplate.getForEntity(...).
        ResponseEntity<AppointmentDto> responseEntity = ResponseEntity.ok(expectedDto);
        when(restTemplate.getForEntity(anyString(), eq(AppointmentDto.class), eq(appointmentId)))
                .thenReturn(responseEntity);

        // Call the method under test
        AppointmentDto actualDto = appointmentService.getAppointment(appointmentId);

        // Verify the results
        assertNotNull(actualDto);
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void testGetAppointment_InvalidId() {
        // Setup
        int appointmentId = 123; // Specify the desired appointmentId
        RestTemplate restTemplate = mock(RestTemplate.class);
        AppointmentServiceImpl appointmentService = new AppointmentServiceImpl(restTemplate);

        // Configure restTemplate.getForEntity(...).
        ResponseEntity<AppointmentDto> responseEntity = ResponseEntity.notFound().build();
        when(restTemplate.getForEntity(anyString(), eq(AppointmentDto.class), eq(appointmentId)))
                .thenReturn(responseEntity);

        // Call the method under test
        AppointmentDto actualDto = appointmentService.getAppointment(appointmentId);

        // Verify the results
        assertNull(actualDto);
    }

    @Test
    void testGetAppointment_Exception() {
        // Setup
        int appointmentId = 123; // Specify the desired appointmentId
        RestTemplate restTemplate = mock(RestTemplate.class);
        AppointmentServiceImpl appointmentService = new AppointmentServiceImpl(restTemplate);

        // Configure restTemplate.getForEntity(...) to throw an exception
        when(restTemplate.getForEntity(anyString(), eq(AppointmentDto.class), eq(appointmentId)))
                .thenThrow(new RuntimeException("Connection timeout"));

        // Call the method under test
        AppointmentDto actualDto = appointmentService.getAppointment(appointmentId);

        // Verify the results
        assertNull(actualDto);
    }
}
