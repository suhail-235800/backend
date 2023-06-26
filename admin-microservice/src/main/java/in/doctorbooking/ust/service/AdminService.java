package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Doctor;

import java.util.List;

public interface AdminService {

    Doctor save(Doctor doctor);

    List<Doctor> findAllDoctors();

    List<Doctor> findByDoctorName(String doctorName);
    
    Doctor updateDoctor(Doctor doctor);

    void remove(Doctor doctor);

    List<Doctor> getDoctorBySpecializations(String specialization);

    Doctor findById(int id);

    List<Doctor> getDoctorByNameAndSpec(String doctorName, String doctorSpecialization);

    List<Doctor> getDoctorByNameAndLoc(String doctorName, String doctorLocation);

    List<Doctor> getDoctorByLocation(String doctorLocation);

    List<Doctor> getDoctorByNameAndLocAndSpec(String doctorName, String doctorLocation, String doctorSpecialization);

    List<Doctor> getDoctorByLocAndSpec(String doctorLocation, String doctorSpecialization);
}
