package in.doctorbooking.ust.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import in.doctorbooking.ust.domain.Appointment;
import in.doctorbooking.ust.dto.AppointmentDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.service.AppointmentService;
import in.doctorbooking.ust.service.DoctorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/appointments")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;
    private Logger logger = LoggerFactory.getLogger(AppointmentController.class);


// ...

    @GetMapping("")
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        List<Appointment> list = appointmentService.findAllAppointments();
        List<AppointmentDto> appointmentList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentList);
    }


    @PostMapping("")
    public ResponseEntity<AppointmentDto> bookAppointment(@RequestBody RequestDto requestDto){
        var doctordto = doctorService.getDoctorById(requestDto.doctorId());

        LocalDate date = LocalDate.parse(requestDto.appointmentDate());
        LocalTime time = LocalTime.parse(requestDto.appointmentTime());

        final var exist = appointmentService.getDoctorAppointmentsBydateandtime(requestDto.doctorId(),date,time);
        if(doctordto == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        if(exist!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(date);


        appointment.setAppointmentTime(time);

        appointment.setDoctorId(doctordto.doctorId());
        appointment.setDoctorName(doctordto.doctorName());
        appointment.setDoctorSpecialization(doctordto.doctorSpecialization());
        appointment.setDoctorLocation(doctordto.doctorLocation());
        appointment.setUserId(0);

        appointmentService.saveAppointment(appointment);

        AppointmentDto appointmentDto = convertToDto(appointment);
        return ResponseEntity.ok(appointmentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable int id) {
        var appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(convertToDto(appointment));

    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByUserId(@PathVariable int userId){
        List<Appointment> list = appointmentService.findAppointmentsByUserId(userId);
        List<AppointmentDto> appointmentList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentList);
    }
    @GetMapping("/doctorName/{doctorName}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByDoctorName(@PathVariable String doctorName){
        List<Appointment> list = appointmentService.findAppointmentsByDoctorName(doctorName);
        List<AppointmentDto> appointmentList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(appointmentList);
    }


    public AppointmentDto convertToDto(Appointment appointment){
        return new AppointmentDto(appointment.getAppointmentId(),appointment.getAppointmentDate(),appointment.getAppointmentTime(),appointment.getDoctorId(),appointment.getDoctorName(),appointment.getDoctorSpecialization(),appointment.getDoctorLocation(),appointment.getUserId());
    }
    public Appointment convertToEntity(AppointmentDto dto){
        return new Appointment(dto.appointmentId(),dto.appointmentDate(),dto.appointmentTime(),dto.doctorId(),dto.doctorName(),dto.doctorSpecialization(),dto.doctorLocation(),dto.userId());
    }

}
