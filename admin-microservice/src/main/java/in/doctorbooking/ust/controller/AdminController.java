package in.doctorbooking.ust.controller;

import in.doctorbooking.ust.domain.Doctor;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.exceptions.DoctorNotFoundException;
import in.doctorbooking.ust.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminController {

    //Add/Update/Delete/View Doctors
    @Autowired
    private AdminService adminService;


    @GetMapping("/home")
    public String showAdminHome() {
        return "admin-home";
    }

    @PostMapping("")
    public ResponseEntity<DoctorDto> addNewDoctor(@Valid @RequestBody RequestDto doctorDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(adminService.save(convertRequestToEntity(doctorDto,0))));

    }

    @GetMapping("")
    public ResponseEntity<List<DoctorDto>> getDoctor() {
        List<Doctor> doctorList = adminService.findAllDoctors();
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(doctorDtoList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id){
        final var doctor = adminService.findById(id);
        return ResponseEntity.ok(convertToDto(doctor));
    }
    @GetMapping("/specializations/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>> getDoctorBySpecializations(@PathVariable String doctorSpecialization){
        final var doctorList = adminService.getDoctorBySpecializations(doctorSpecialization);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("doctorname/{doctorName}")
    public ResponseEntity<List<DoctorDto>> getDoctorByName(@PathVariable String doctorName) {
        final var doctorList = adminService.findByDoctorName(doctorName);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }
    @GetMapping("doctorlocation/{doctorLocation}")
    public ResponseEntity<List<DoctorDto>> getDoctorByLocation(@PathVariable String doctorLocation) {
        final var doctorList = adminService.getDoctorByLocation(doctorLocation);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }

    @GetMapping("/searchnamespec/{doctorName}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandSpec(@PathVariable String doctorName,@PathVariable String doctorSpecialization) {
        final var doctorList = adminService.getDoctorByNameAndSpec(doctorName,doctorSpecialization);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }
    @GetMapping("/searchlocspec/{doctorLocation}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByLocandSpec(@PathVariable String doctorLocation,@PathVariable String doctorSpecialization) {
        final var doctorList = adminService.getDoctorByLocAndSpec(doctorLocation,doctorSpecialization);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("/searchnameloc/{doctorName}/{doctorLocation}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandLoc(@PathVariable String doctorName,@PathVariable String doctorLocation) {
        final var doctorList = adminService.getDoctorByNameAndLoc(doctorName,doctorLocation);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("/searchnamelocspec/{doctorName}/{doctorLocation}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandLocandSpec(@PathVariable String doctorName,@PathVariable String doctorLocation,@PathVariable String doctorSpecialization) {
        final var doctorList = adminService.getDoctorByNameAndLocAndSpec(doctorName,doctorLocation,doctorSpecialization);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable int id,@Valid @RequestBody RequestDto doctorDto) {
        Doctor existingDoctor = adminService.findById(id);
        if (existingDoctor == null) {
            // Handle case where doctor is not found
            return ResponseEntity.notFound().build();
        }

        Doctor updatedDoctor = adminService.updateDoctor(convertRequestToEntity(doctorDto, existingDoctor.getDoctorId()));
        return ResponseEntity.ok(convertToDto(updatedDoctor));
    }

//    @DeleteMapping("/delete/{doctorName}")
//    public ResponseEntity deleteDoctor(@PathVariable String doctorName) {
//        Optional<Doctor> newdoctor = Optional.of(adminService.findDoctorByName(doctorName));
//        adminService.remove(newdoctor.get());
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDoctor(@PathVariable int id) {
        Optional<Doctor> newdoctor = Optional.of(adminService.findById(id));
        if(newdoctor.isEmpty()){
            throw new DoctorNotFoundException("No doctor to delete");
        }
        adminService.remove(newdoctor.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    public DoctorDto convertToDto(Doctor doctor){
        return new DoctorDto(doctor.getDoctorId(),doctor.getDoctorName(),doctor.getDoctorSpecialization(),doctor.getDoctorLocation(),doctor.getDoctorRating());
    }

    public Doctor convertToEntity(DoctorDto doctorDto){
        return new Doctor(doctorDto.doctorId(),doctorDto.doctorName(),doctorDto.doctorSpecialization(),doctorDto.doctorLocation(),doctorDto.doctorRating());
    }
    public Doctor convertRequestToEntity(RequestDto doctorDto,int id){
        return new Doctor(id,doctorDto.doctorName(),doctorDto.doctorSpecialization(),doctorDto.doctorLocation(),doctorDto.doctorRating());
    }


}
