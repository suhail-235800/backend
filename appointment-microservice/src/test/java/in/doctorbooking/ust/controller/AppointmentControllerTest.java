package in.doctorbooking.ust.controller;

import in.doctorbooking.ust.domain.Appointment;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.service.AppointmentService;
import in.doctorbooking.ust.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService mockAppointmentService;
    @MockBean
    private DoctorService mockDoctorService;


    @Test
    void testGetAppointments() throws Exception {
        // Setup
        // Configure AppointmentService.findAllAppointments(...).
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        appointment.setDoctorSpecialization("doctorSpeciality");
        appointment.setDoctorLocation("doctorLocation");
        appointment.setUserId(0);
        List<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(appointment);
        when(mockAppointmentService.findAllAppointments()).thenReturn(appointments);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8100/api/v1/appointments")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"appointmentId\":0,\"appointmentDate\":\"2020-01-01\",\"appointmentTime\":\"00:00:00\",\"doctorId\":0,\"doctorName\":\"doctorName\",\"doctorSpeciality\":\"doctorSpeciality\",\"doctorLocation\":\"doctorLocation\",\"userId\":0}]");

    }

    @Test
    void testGetAppointments_AppointmentServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockAppointmentService.findAllAppointments()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8100/api/v1/appointments")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testBookAppointment() throws Exception {
        // Setup
        final DoctorDto doctorDto = new DoctorDto(0, "doctorName", "doctorSpeciality", "doctorLocation", 0);
        when(mockDoctorService.getDoctorById(0)).thenReturn(doctorDto);

        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        appointment.setDoctorSpecialization("doctorSpeciality");
        appointment.setDoctorLocation("doctorLocation");
        appointment.setUserId(0);
        when(mockAppointmentService.getDoctorAppointmentsBydateandtime(0, LocalDate.of(2020, 1, 1),
                LocalTime.of(0, 0, 0))).thenReturn(appointment);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("http://localhost:8100/api/v1/appointments")
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }



    @Test
    void testBookAppointment_DoctorServiceReturnsNull() throws Exception {
        // Setup
        when(mockDoctorService.getDoctorById(0)).thenReturn(null);

        // Configure AppointmentService.getDoctorAppointmentsBydateandtime(...).
        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        appointment.setDoctorSpecialization("doctorSpeciality");
        appointment.setDoctorLocation("doctorLocation");
        appointment.setUserId(0);
        when(mockAppointmentService.getDoctorAppointmentsBydateandtime(0, LocalDate.of(2020, 1, 1),
                LocalTime.of(0, 0, 0))).thenReturn(appointment);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("http://localhost:8100/api/v1/appointments")
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }


    @Test
    void testBookAppointment_AppointmentServiceGetDoctorAppointmentsBydateandtimeReturnsNull() throws Exception {
        // Setup
        final DoctorDto doctorDto = new DoctorDto(0, "doctorName", "doctorSpeciality", "doctorLocation", 0);
        when(mockDoctorService.getDoctorById(0)).thenReturn(doctorDto);
        when(mockAppointmentService.getDoctorAppointmentsBydateandtime(0, LocalDate.of(2020, 1, 1), LocalTime.of(0, 0, 0))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("http://localhost:8100/api/v1/appointments")
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());

        // Confirm AppointmentService.saveAppointment(...) is not called.
        verify(mockAppointmentService, never()).saveAppointment(any(Appointment.class));
    }



    @Test
    void testGetAppointmentById() throws Exception {
        // Setup
        // Configure AppointmentService.getAppointmentById(...).
        final Appointment appointment = new Appointment();
        appointment.setAppointmentId(0);
        appointment.setAppointmentDate(LocalDate.of(2020, 1, 1));
        appointment.setAppointmentTime(LocalTime.of(0, 0, 0));
        appointment.setDoctorId(0);
        appointment.setDoctorName("doctorName");
        appointment.setDoctorSpecialization("doctorSpeciality");
        appointment.setDoctorLocation("doctorLocation");
        appointment.setUserId(0);
        when(mockAppointmentService.getAppointmentById(0)).thenReturn(appointment);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8100/api/v1/appointments/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }
}
