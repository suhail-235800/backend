////package in.doctorbooking.ust.repository;
////
////import in.doctorbooking.ust.domain.Doctor;
////import org.junit.jupiter.api.AfterEach;
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
////import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
////import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
////import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.test.context.junit.jupiter.SpringExtension;
////
////import java.util.List;
////import java.util.Objects;
////
////import static org.hamcrest.MatcherAssert.assertThat;
////import static org.hamcrest.Matchers.samePropertyValuesAs;
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.junit.jupiter.api.Assertions.assertNotNull;
////
////import org.junit.jupiter.api.Test;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
////import static org.junit.jupiter.api.Assertions.*;
////
////@SpringBootTest
////class AdminRepositoryTest {
////
////    @Autowired
////    private AdminRepository adminRepository;
////
////    @BeforeEach
////    void setUp() {
////        // Perform any setup operations if needed
////    }
////
////    @AfterEach
////    void tearDown() {
////        adminRepository.deleteAll();
////    }
////
////
////    @Test
////    void testFindByDoctorId_InvalidId() {
////        // Setup
////        int doctorId = 123; // Specify an invalid doctorId
////
////        // Call the method under test
////        Doctor actualDoctor = adminRepository.findByDoctorId(doctorId);
////
////        // Verify the results
////        assertNull(actualDoctor);
////    }
////
////    @Test
////    void testFindByDoctorName_ValidName() {
////        // Setup
////        String doctorName = "John Doe"; // Specify the desired doctorName
////        String specialization = "Cardiology";
////        Doctor expectedDoctor = new Doctor(29, "John Doe", specialization, "location", 4);
////
////        // Save the doctor in the repository
////        adminRepository.save(expectedDoctor);
////
////        // Call the method under test
//////        Doctor actualDoctor = adminRepository.findByDoctorName(doctorName);
////
////        // Verify the results
////        assertNotNull(actualDoctor);
////
////    }
//
//    @Test
//    void testFindByDoctorName_InvalidName() {
//        // Setup
//        String doctorName = "John Doe"; // Specify an invalid doctorName
//
//        // Call the method under test
//        Doctor actualDoctor = adminRepository.findByDoctorName(doctorName);
//
//        // Verify the results
//        assertNull(actualDoctor);
//    }
//
//    @Test
//    void testFindByDoctorSpecialization_ValidSpecialization() {
//        // Setup
//        String specialization = "Cardiology"; // Specify the desired specialization
//        Doctor doctor1 = new Doctor(123, "John Doe", specialization, "location", 4);
//        Doctor doctor2 = new Doctor(456, "Jane Smith", specialization, "location", 4);
//
//        // Save the doctors in the repository
//        adminRepository.save(doctor1);
//        adminRepository.save(doctor2);
//
//        // Call the method under test
//        List<Doctor> doctors = adminRepository.findByDoctorSpecialization(specialization);
//
//        // Verify the results
//        assertNotNull(doctors);
//        assertEquals(2, doctors.size());
//
//        // Verify doctor1
//        Doctor foundDoctor1 = doctors.stream().filter(d -> Objects.equals(d.getDoctorSpecialization(), doctor1.getDoctorSpecialization())).findFirst().orElse(null);
//
//
//        assertEquals(doctor1.getDoctorName(), foundDoctor1.getDoctorName());
//        assertEquals(doctor1.getDoctorSpecialization(), foundDoctor1.getDoctorSpecialization());
//        assertEquals(doctor1.getDoctorLocation(), foundDoctor1.getDoctorLocation());
//        assertEquals(doctor1.getDoctorRating(), foundDoctor1.getDoctorRating());
//
//    }
//
//
//    @Test
//    void testFindByDoctorSpecialization_InvalidSpecialization() {
//        // Setup
//        String specialization = "Cardiology"; // Specify an invalid specialization
//
//        // Call the method under test
//        List<Doctor> doctors = adminRepository.findByDoctorSpecialization(specialization);
//
//        // Verify the results
//        assertNotNull(doctors);
//        assertTrue(doctors.isEmpty());
//    }
//
//}


