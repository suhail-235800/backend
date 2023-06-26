package in.doctorbooking.ust.controller;

import in.doctorbooking.ust.domain.Rating;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.DoctorRequestDto;
import in.doctorbooking.ust.dto.RatingDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.service.AppointmentService;
import in.doctorbooking.ust.service.DoctorService;
import in.doctorbooking.ust.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ratings")
@CrossOrigin("*")
public class RatingController {

    private final RatingService ratingService;

    private final AppointmentService appointmentService;

    private final DoctorService doctorService;

    public RatingController(RatingService ratingService,AppointmentService appointmentService,DoctorService doctorService) {
        this.ratingService = ratingService;
        this.appointmentService=appointmentService;
        this.doctorService=doctorService;
    }

    @GetMapping("")
    public ResponseEntity<List<RatingDto>> getAll(){
        List<Rating> list = ratingService.getAll();
        List<RatingDto> ratingList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingList);
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<RatingDto>> getRatingById(@PathVariable int id){
        List<Rating> list = ratingService.getRatingById(id);
        List<RatingDto> ratingList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingList);
    }

    @PostMapping("")
    public ResponseEntity<RatingDto> giveRating(@RequestBody RequestDto requestDto){
        var appointmentDto = appointmentService.getAppointment(requestDto.appointmentId());
        if(appointmentDto == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        var updatedRating = ((ratingService.getReviewByDoctor(appointmentDto.doctorId()))+requestDto.rating())/2;

        DoctorRequestDto dto = new DoctorRequestDto(appointmentDto.doctorId(),appointmentDto.doctorName(),appointmentDto.doctorSpecialization(),appointmentDto.doctorLocation(),updatedRating);
        doctorService.setDoctor(dto,appointmentDto.doctorId());

        Rating rating = new Rating();
        rating.setAppointmentDate(appointmentDto.appointmentDate());
        rating.setAppointmentTime(appointmentDto.appointmentTime());
        rating.setAppointmentId(appointmentDto.appointmentId());
        rating.setRating(requestDto.rating());
        rating.setReview(requestDto.review());
        rating.setDoctorId(appointmentDto.doctorId());
        rating.setDoctorName(appointmentDto.doctorName());
        rating.setDoctorSpecialization(appointmentDto.doctorSpecialization());
        rating.setDoctorLocation(appointmentDto.doctorLocation());

        rating.setUserId(appointmentDto.userId());

        ratingService.saveRating(rating);



        return ResponseEntity.status(HttpStatus.OK).body(convertToDto(rating));

    }


    public RatingDto convertToDto(Rating rating){
        return new RatingDto(rating.getRatingId(),rating.getRating(),rating.getReview(),rating.getDoctorId()
                ,rating.getDoctorName(), rating.getDoctorSpecialization(), rating.getDoctorLocation(), rating.getAppointmentId(),rating.getAppointmentDate(),rating.getAppointmentTime(),rating.getUserId());
    }

}
