package in.doctorbooking.ust.controller;

import in.doctorbooking.ust.domain.Doctor;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.exceptions.DoctorNotFoundException;
import in.doctorbooking.ust.service.AdminService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(AdminController.class);


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
        logger.info("getting Doctor list");
        List<Doctor> doctorList = adminService.findAllDoctors();
        logger.info("converting doctor list to dto");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(doctorDtoList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int id){
        logger.info("getDoctorById");
        final var doctor = adminService.findById(id);
        logger.info("getDoctorById succesfull with id {}", id);
        return ResponseEntity.ok(convertToDto(doctor));
    }
    @GetMapping("/specializations/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>> getDoctorBySpecializations(@PathVariable String doctorSpecialization){

        final var doctorList = adminService.getDoctorBySpecializations(doctorSpecialization);
        logger.info("getDoctorBySpecializations successfull");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("convereted doctorlist to dto successfull");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("doctorname/{doctorName}")
    public ResponseEntity<List<DoctorDto>> getDoctorByName(@PathVariable String doctorName) {
        logger.info("gettingby doctorname");
        final var doctorList = adminService.findByDoctorName(doctorName);
        logger.info("findbydoctorname sucessfull with doctorname {}",doctorName);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converted list to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }
    @GetMapping("doctorlocation/{doctorLocation}")
    public ResponseEntity<List<DoctorDto>> getDoctorByLocation(@PathVariable String doctorLocation) {
        logger.info("gettingby doctorlocation");
        final var doctorList = adminService.getDoctorByLocation(doctorLocation);
        logger.info("findbydoctorlocation sucessful with location {}",doctorLocation);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converted list to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }

    @GetMapping("/searchnamespec/{doctorName}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandSpec(@PathVariable String doctorName,@PathVariable String doctorSpecialization) {
        logger.info("gettingby doctorspecialization");
        final var doctorList = adminService.getDoctorByNameAndSpec(doctorName,doctorSpecialization);
        logger.info("findbydoctorspecialization sucessful with specialization {}",doctorSpecialization);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converted list to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }
    @GetMapping("/searchlocspec/{doctorLocation}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByLocandSpec(@PathVariable String doctorLocation,@PathVariable String doctorSpecialization) {
        logger.info("getting by doctor location {} and specialization {}",doctorLocation,doctorSpecialization);
        final var doctorList = adminService.getDoctorByLocAndSpec(doctorLocation,doctorSpecialization);
        logger.info("findbydoctorlocationandspecialization suucessful");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converted list to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("/searchnameloc/{doctorName}/{doctorLocation}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandLoc(@PathVariable String doctorName,@PathVariable String doctorLocation) {
        logger.info("getting by doctor location {} and name {}",doctorName,doctorLocation);
        final var doctorList = adminService.getDoctorByNameAndLoc(doctorName,doctorLocation);
        logger.info("searchDoctorByNameandLoc successfull");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("doctorlist to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("/searchnamelocspec/{doctorName}/{doctorLocation}/{doctorSpecialization}")
    public ResponseEntity<List<DoctorDto>>searchDoctorByNameandLocandSpec(@PathVariable String doctorName,@PathVariable String doctorLocation,@PathVariable String doctorSpecialization) {
        logger.info("getting by doctor location name {} and spec {}",doctorName,doctorLocation);
        final var doctorList = adminService.getDoctorByNameAndLocAndSpec(doctorName,doctorLocation,doctorSpecialization);
        logger.info("search Doctor By doctor location name and spec successfull");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converting doctorlist to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @GetMapping("/searchkeyword/{searchkeyword}")
    public ResponseEntity<List<DoctorDto>> searchKeyword(@PathVariable String searchkeyword){

        logger.info("getting by doctor name keyword {}", searchkeyword);
        final var doctorList = adminService.getDoctorByKeyword(searchkeyword);
        logger.info("search Doctor By doctor location name and spec successfull");
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        logger.info("converting doctorlist to dto");
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable int id,@Valid @RequestBody RequestDto doctorDto) {
        logger.info("updating doctor");
        Doctor existingDoctor = adminService.findById(id);
        logger.info("getting doctor  by id {}",id);
        if (existingDoctor == null) {
            // Handle case where doctor is not found
            logger.info("doctor not found");
            return ResponseEntity.notFound().build();
        }
        logger.info("doctor found with id");

        Doctor updatedDoctor = adminService.updateDoctor(convertRequestToEntity(doctorDto, existingDoctor.getDoctorId()));
        logger.info("succesfully updated doctor with id {}",existingDoctor.getDoctorId());
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
        logger.info("searching for doctor with id");
        Optional<Doctor> newdoctor = Optional.of(adminService.findById(id));

        if(newdoctor.isEmpty()){
            logger.info("no doctor found with id {}",id);
            throw new DoctorNotFoundException("No doctor to delete");
        }
        logger.info("found doctor with id {}",id);
        adminService.remove(newdoctor.get());
        logger.info("doctor deleted successfully");
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
