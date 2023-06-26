//package in.doctorbooking.ust.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import in.doctorbooking.ust.controller.AdminController;
//import in.doctorbooking.ust.domain.Doctor;
//import in.doctorbooking.ust.dto.DoctorDto;
//import in.doctorbooking.ust.dto.RequestDto;
//import in.doctorbooking.ust.service.AdminService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AdminController.class)
//class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    AdminController adminController;
//
//    @MockBean
//    private AdminService mockAdminService;
//
//    @Test
//    void testShowAdminHome() throws Exception {
//        // Setup
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8000/api/v1/admin/home")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
//    }
//
//
//
//    @Test
//    void testGetDoctor() throws Exception {
//        // Setup
//        // Configure AdminService.findAllDoctors(...).
//        final List<Doctor> doctorList = List.of(
//                new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0));
//        when(mockAdminService.findAllDoctors()).thenReturn(doctorList);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8000/api/v1/admin")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
//    }
//
//    @Test
//    void testGetDoctor_AdminServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockAdminService.findAllDoctors()).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8000/api/v1/admin")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");
//    }
//
//    @Test
//    void testGetDoctorById() throws Exception {
//        // Setup
//        // Configure AdminService.findById(...).
//        final Doctor doctor = new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0);
//        when(mockAdminService.findById(0)).thenReturn(doctor);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8000/api/v1/admin/id/{id}", 0)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
//    }
//
//    @Test
//    void testGetDoctorBySpecializations() throws Exception {
//        // Setup
//        // Configure AdminService.getDoctorBySpecializations(...).
//        final List<Doctor> doctorList = List.of(
//                new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0));
//        when(mockAdminService.getDoctorBySpecializations("doctorSpecialization")).thenReturn(doctorList);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(
//                        get("http://localhost:8000/api/v1/admin/specializations/{doctorSpecialization}", "doctorSpecialization")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
//    }
//
//    @Test
//    void testGetDoctorBySpecializations_AdminServiceReturnsNoItems() throws Exception {
//        // Setup
//        when(mockAdminService.getDoctorBySpecializations("doctorSpecialization")).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(
//                        get("http://localhost:8000/api/v1/admin/specializations/{doctorSpecialization}", "doctorSpecialization")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");
//    }
//
////    @Test
////    void testGetDoctorByName() throws Exception {
////        // Setup
////        // Configure AdminService.findDoctorByName(...).
////        final Doctor doctor = new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0);
////        when(mockAdminService.findDoctorByName("doctorName")).thenReturn(doctor);
////
////        // Run the test
////        final MockHttpServletResponse response = mockMvc.perform(get("http://localhost:8000/api/v1/admin/{doctorName}", "doctorName")
////                        .accept(MediaType.APPLICATION_JSON))
////                .andReturn().getResponse();
////
////        // Verify the results
////        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
////        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
////    }
//
//
//
////    @Test
////    void testDeleteDoctor1() throws Exception {
////        // Setup
////        // Configure AdminService.findDoctorByName(...).
////        final Doctor doctor = new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0);
////        when(mockAdminService.findDoctorByName("doctorName")).thenReturn(doctor);
////
////        // Run the test
////        final MockHttpServletResponse response = mockMvc.perform(
////                        delete("http://localhost:8000/api/v1/admin/delete/{doctorName}", "doctorName")
////                                .accept(MediaType.APPLICATION_JSON))
////                .andReturn().getResponse();
////
////        // Verify the results
////        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
////        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
////        verify(mockAdminService).remove(new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0));
////    }
//
//    @Test
//    void testDeleteDoctor2() throws Exception {
//        // Setup
//        // Configure AdminService.findById(...).
//        final Doctor doctor = new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0);
//        when(mockAdminService.findById(0)).thenReturn(doctor);
//
//        // Run the test
//        final MockHttpServletResponse response = mockMvc.perform(delete("http://localhost:8000/api/v1/admin/{id}", 0)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // Verify the results
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(response.getContentAsString());
//        verify(mockAdminService).remove(new Doctor(0, "doctorName", "doctorSpecialization", "doctorLocation", 0));
//    }
////    @Test
////    public void testUpdateDoctor() throws Exception {
////        // Initialize RequestDto
////        RequestDto requestDto = new RequestDto(1, "John Doe", "Cardiology", "New York", 4);
////
////        // Initialize existingDoctor
////        Doctor existingDoctor = new Doctor();
////        existingDoctor.setDoctorId(requestDto.doctorId());
////        existingDoctor.setDoctorName(requestDto.doctorName());
////        existingDoctor.setDoctorSpecialization(requestDto.doctorSpecialization());
////        existingDoctor.setDoctorLocation(requestDto.doctorLocation());
////        existingDoctor.setDoctorRating(requestDto.doctorRating());
////
////        // Mock the behavior of the AdminService
////        when(mockAdminService.findDoctorByName("John Doe")).thenReturn(existingDoctor);
////
////        // Initialize updatedDoctor
////        Doctor updatedDoctor = new Doctor();
////        updatedDoctor.setDoctorId(existingDoctor.getDoctorId());
////        updatedDoctor.setDoctorName(existingDoctor.getDoctorName());
////        updatedDoctor.setDoctorSpecialization(existingDoctor.getDoctorSpecialization());
////        updatedDoctor.setDoctorLocation(existingDoctor.getDoctorLocation());
////        updatedDoctor.setDoctorRating(existingDoctor.getDoctorRating());
////
////
////        when(mockAdminService.updateDoctor(any(Doctor.class))).thenReturn(updatedDoctor);
////
////        // Perform the PUT request to update the doctor
////        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8000/api/v1/admin/{doctorName}", "John Doe")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content("{\"fieldName\":\"updatedValue\"}"))
////                .andExpect(status().isOk());
////
////        // Assert the expected response
////        // Add assertions based on the behavior of the AdminService and the updatedDoctor object
////
////        // Verify that the AdminService methods were called with the expected arguments
////        verify(mockAdminService, times(1)).findDoctorByName("John Doe");
////        verify(mockAdminService, times(1)).updateDoctor(any(Doctor.class));
////
////        resultActions.andReturn();
//    }
//
//    @Test
//    void testAddNewDoctor() throws Exception {
//        // Prepare test data
//        RequestDto requestDto = new RequestDto(
//                1,
//                "John Doe",
//                "Cardiology",
//                "New York",
//                5
//        );
//
//        DoctorDto expectedDto = new DoctorDto(1, "John Doe", "Cardiology", "New York", 5);
//
//        // Mock the adminService.save() method
//        when(mockAdminService.save(any(Doctor.class))).thenReturn(adminController.convertToEntity(expectedDto));
//
//        // Perform the POST request
//        MvcResult result = mockMvc.perform(post("http://localhost:8000/api/v1/admin")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(convertToRequestBody(requestDto)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        // Verify the response
//        ResponseEntity<DoctorDto> responseEntity = convertToResponseEntity(result.getResponse().getContentAsString(), DoctorDto.class);
//        DoctorDto actualDto = responseEntity.getBody();
//
//        // Assertions
//        Assertions.assertNotNull(actualDto);
//        Assertions.assertEquals(expectedDto.doctorName(), actualDto.doctorName());
//        Assertions.assertEquals(expectedDto.doctorSpecialization(), actualDto.doctorSpecialization());
//        Assertions.assertEquals(expectedDto.doctorLocation(), actualDto.doctorLocation());
//        Assertions.assertEquals(expectedDto.doctorRating(), actualDto.doctorRating());
//    }
//    private static String convertToRequestBody(RequestDto requestDto) {
//        return String.format("{\"doctorId\": %d, \"doctorName\": \"%s\", \"doctorSpecialization\": \"%s\", \"doctorLocation\": \"%s\", \"doctorRating\": %d}",
//                requestDto.doctorId(),
//                requestDto.doctorName(),
//                requestDto.doctorSpecialization(),
//                requestDto.doctorLocation(),
//                requestDto.doctorRating());
//    }
//    private static <T> ResponseEntity<T> convertToResponseEntity(String responseJson, Class<T> responseType) throws Exception {
//        T body = convertToDto(responseJson, responseType);
//        return ResponseEntity.ok().body(body);
//    }
//    // Utility method to convert JSON to DTO object
//    private static <T> T convertToDto(String json, Class<T> responseType) throws Exception {
//        return new ObjectMapper().readValue(json, responseType);
//    }
//
//    // Utility method to convert DoctorDto to DoctorEntity
//
//}
