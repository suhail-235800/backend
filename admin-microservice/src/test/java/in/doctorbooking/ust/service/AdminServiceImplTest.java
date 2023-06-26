package in.doctorbooking.ust.service;

import in.doctorbooking.ust.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.*;


import in.doctorbooking.ust.domain.Doctor;
import in.doctorbooking.ust.exceptions.DoctorAlreadyExistsException;
import in.doctorbooking.ust.exceptions.DoctorNotFoundException;
import in.doctorbooking.ust.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceImplTest {

    @Mock
    private AdminRepository doctorRepository;

    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adminService = new AdminServiceImpl(doctorRepository);
    }

    @Test
    void saveDoctor_ShouldSaveDoctor() {
        Doctor doctor = new Doctor();
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor savedDoctor = adminService.save(doctor);

        assertNotNull(savedDoctor);
        assertEquals(doctor, savedDoctor);
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    void findAllDoctors_ShouldReturnListOfDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        doctors.add(new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> result = adminService.findAllDoctors();

        assertNotNull(result);
        assertEquals(doctors.size(), result.size());
        verify(doctorRepository, times(1)).findAll();
    }

    // Add more test cases for the remaining methods...

    @Test
    void removeDoctor_ShouldDeleteDoctor() {
        Doctor doctor = new Doctor();

        assertDoesNotThrow(() -> adminService.remove(doctor));

        verify(doctorRepository, times(1)).delete(doctor);
    }

    @Test
    void getDoctorBySpecializations_WhenDoctorExists_ShouldReturnListOfDoctors() {
        String specialization = "Cardiology";
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        doctors.add(new Doctor());
        when(doctorRepository.findByDoctorSpecialization(specialization)).thenReturn(doctors);

        List<Doctor> result = adminService.getDoctorBySpecializations(specialization);

        assertNotNull(result);
        assertEquals(doctors.size(), result.size());
        verify(doctorRepository, times(1)).findByDoctorSpecialization(specialization);
    }

    @Test
    void getDoctorBySpecializations_WhenDoctorDoesNotExist_ShouldThrowException() {
        String specialization = "Neurology";
        when(doctorRepository.findByDoctorSpecialization(specialization)).thenReturn(new ArrayList<>());

        assertThrows(DoctorNotFoundException.class, () -> adminService.getDoctorBySpecializations(specialization));

        verify(doctorRepository, times(1)).findByDoctorSpecialization(specialization);
    }
}
