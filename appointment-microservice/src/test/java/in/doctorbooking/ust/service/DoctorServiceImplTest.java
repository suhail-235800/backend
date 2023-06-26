package in.doctorbooking.ust.service;

import in.doctorbooking.ust.dto.DoctorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private DoctorServiceImpl doctorServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        doctorServiceImplUnderTest = new DoctorServiceImpl(mockRestTemplate);
    }


    @Test
    void testGetDoctorById() {
        // Setup
        final DoctorDto expectedResult = new DoctorDto(0, "doctorName", "doctorSpecialization", "doctorLocation", 0);

        // Configure RestTemplate.getForEntity(...).
        final ResponseEntity<DoctorDto> doctorDtoResponseEntity = new ResponseEntity<>(
                new DoctorDto(0, "doctorName", "doctorSpecialization", "doctorLocation", 0), HttpStatus.OK);
        when(mockRestTemplate.getForEntity(eq("http://ADMIN-SERVICE/api/v1/admin/id/{id}"), eq(DoctorDto.class), anyInt()))
                .thenReturn(doctorDtoResponseEntity);

        // Run the test
        final DoctorDto result = doctorServiceImplUnderTest.getDoctorById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDoctorById_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.getForEntity(eq("http://ADMIN-SERVICE/api/v1/admin/id/{id}"), eq(DoctorDto.class), eq(0)))
                .thenThrow(RestClientException.class);

        // Run the test
        final DoctorDto result = doctorServiceImplUnderTest.getDoctorById(0);

        // Verify the results
        assertThat(result).isNull();
    }

}
