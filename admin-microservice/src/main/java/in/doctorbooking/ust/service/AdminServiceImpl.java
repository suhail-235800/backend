package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Doctor;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.exceptions.DoctorAlreadyExistsException;
import in.doctorbooking.ust.exceptions.DoctorNotFoundException;
import in.doctorbooking.ust.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository doctorRepository;

    public AdminServiceImpl(AdminRepository doctorRepository) {
        this.doctorRepository=doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        Optional<List<Doctor>> test = Optional.of(doctorRepository.findAll());
        if(test.isEmpty()){
            throw new DoctorNotFoundException("No doctors found");
        }
        return test.get();
    }

    @Override
    public List<Doctor> findByDoctorName(String doctorName) {
        List<Doctor> test = doctorRepository.findByDoctorName(doctorName);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("No doctor by name:"+doctorName);
        }
        return test;
    }

    @Override
    public Doctor findById(int id) {
        final var test = doctorRepository.findByDoctorId(id);
        if(test==null){
            throw new DoctorNotFoundException("Cannot find the doctor with id " + id);
        }
        return doctorRepository.save(test);

    }

    @Override
    public List<Doctor> getDoctorByNameAndSpec(String doctorName, String doctorSpecialization) {
        final var test = doctorRepository.findByDoctorNameAndDoctorSpecialization(doctorName,doctorSpecialization);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("no doctors found with that name and specialization:");
        }
        return test;
    }

    @Override
    public List<Doctor> getDoctorByNameAndLoc(String doctorName, String doctorLocation) {
        final var test = doctorRepository.findByDoctorNameAndDoctorLocation(doctorName,doctorLocation);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("no doctors found with that name");
        }
        return test;
    }

    @Override
    public List<Doctor> getDoctorByLocation(String doctorLocation) {
        List<Doctor> test = doctorRepository.findByDoctorLocation(doctorLocation);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("No doctor found in location: "+doctorLocation);
        }
        return test;
    }

    @Override
    public List<Doctor> getDoctorByNameAndLocAndSpec(String doctorName, String doctorLocation, String doctorSpecialization) {
        final var test = doctorRepository.findByDoctorNameAndDoctorLocationAndDoctorSpecialization(doctorName,doctorLocation,doctorSpecialization);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("no doctors found with that name");
        }
        return test;
    }

    @Override
    public List<Doctor> getDoctorByLocAndSpec(String doctorLocation, String doctorSpecialization) {
        final var test = doctorRepository.findByDoctorLocationAndDoctorSpecialization(doctorLocation,doctorSpecialization);
        if(test.isEmpty()){
            throw new DoctorNotFoundException("no doctors found with that name and specialization:");
        }
        return test;
    }


    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Optional<Doctor> test = doctorRepository.findById(doctor.getDoctorId());
        if(test.isEmpty()){
            throw new DoctorNotFoundException("Cannot find the doctor with id " + doctor.getDoctorId());
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public void remove(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public List<Doctor> getDoctorBySpecializations(String specialization) {
        var list = doctorRepository.findByDoctorSpecialization(specialization);
        if(list.isEmpty()){
            throw new DoctorNotFoundException("Cannot find the doctor with specialization" +specialization);
        }
        return list;
    }
}
