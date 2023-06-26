package in.doctorbooking.ust.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.doctorbooking.ust.domain.Rating;
import in.doctorbooking.ust.dto.AppointmentDto;
import in.doctorbooking.ust.dto.RatingDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.service.AppointmentService;
import in.doctorbooking.ust.service.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RatingController.class)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RatingController ratingController;

    @MockBean
    private RatingService mockRatingService;
    @MockBean
    private AppointmentService mockAppointmentService;

    @Test
    void testGetAll() throws Exception {
        // Setup
        // Configure RatingService.getAll(...).
        final Rating rating = new Rating();
        rating.setRatingId(0);
        rating.setRating(0);
        rating.setReview("review");
        rating.setDoctorId(0);
        rating.setDoctorName("doctorName");
        rating.setAppointmentId(0);
        rating.setAppointmentDate(LocalDate.of(2020, 1, 1));
        rating.setUserId(0);
        final List<Rating> ratings = List.of(rating);
        when(mockRatingService.getAll()).thenReturn(ratings);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8200/api/v1/ratings")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
    }

    @Test
    void testGetAll_RatingServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockRatingService.getAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8200/api/v1/ratings")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGiveRating() throws Exception {
        // Setup
        // Configure AppointmentService.getAppointment(...).
        final AppointmentDto appointmentDto = new AppointmentDto(0, LocalDate.of(2020, 1, 1), LocalTime.of(0, 0, 0), 0,
                "doctorName", "doctorSpeciality", "doctorLocation", 0);
        when(mockAppointmentService.getAppointment(0)).thenReturn(appointmentDto);

        // Create the request body
        RequestDto requestDto = new RequestDto(5, "Great service!", 0);

        // Create MockMvc instance
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();

        // Convert requestDto to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(requestDto);

        // Perform the request
        MvcResult result = mockMvc.perform(post("http://localhost:8200/api/v1/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        // Verify the results
        int expectedStatus = HttpStatus.OK.value();
        int actualStatus = result.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);

        String expectedContent = result.getResponse().getContentAsString();
        assertEquals(expectedContent, expectedContent);

        // Confirm RatingService.saveRating(...).
        Rating expectedRating = new Rating();
        expectedRating.setRatingId(0);
        expectedRating.setRating(requestDto.rating());
        expectedRating.setReview(requestDto.review());
        expectedRating.setDoctorId(appointmentDto.doctorId());
        expectedRating.setDoctorName(appointmentDto.doctorName());
        expectedRating.setAppointmentId(requestDto.appointmentId());
        expectedRating.setAppointmentDate(appointmentDto.appointmentDate());
        expectedRating.setUserId(appointmentDto.userId());

        verify(mockRatingService).saveRating(expectedRating);
    }

    @Test
    void testGiveRating_AppointmentServiceReturnsNull() throws Exception {
        // Setup
        // Configure AppointmentService.getAppointment(...).
        AppointmentService mockAppointmentService = mock(AppointmentService.class);
        when(mockAppointmentService.getAppointment(0)).thenReturn(null);

        // Create the request body
        RequestDto requestDto = new RequestDto(5, "Great service!", 0);

        // Create MockMvc instance
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();

        // Convert requestDto to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(requestDto);

        // Perform the request
        MvcResult result = mockMvc.perform(post("http://localhost:8200/api/v1/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        // Verify the results
        int expectedStatus = HttpStatus.NO_CONTENT.value();
        int actualStatus = result.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);

        // Confirm that RatingService.saveRating(...) is not called
        verify(mockRatingService, never()).saveRating(any());
    }

}
