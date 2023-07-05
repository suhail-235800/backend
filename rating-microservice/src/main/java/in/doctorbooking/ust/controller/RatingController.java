package in.doctorbooking.ust.controller;

import in.doctorbooking.ust.domain.Rating;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.DoctorRequestDto;
import in.doctorbooking.ust.dto.RatingDto;
import in.doctorbooking.ust.dto.RequestDto;
import in.doctorbooking.ust.service.AppointmentService;
import in.doctorbooking.ust.service.DoctorService;
import in.doctorbooking.ust.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(RatingController.class);


    public RatingController(RatingService ratingService,AppointmentService appointmentService,DoctorService doctorService) {
        this.ratingService = ratingService;
        this.appointmentService=appointmentService;
        this.doctorService=doctorService;
    }

    @GetMapping("")
    public ResponseEntity<List<RatingDto>> getAll(){
        logger.info("getting all ratings");
        List<Rating> list = ratingService.getAll();
        logger.info("getting all ratings successfull");
        logger.info("converting dto to list");
        List<RatingDto> ratingList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ratingList);
    }

    @GetMapping("/userid/{id}")
    public ResponseEntity<List<RatingDto>> getRatingById(@PathVariable int id){
        logger.info("getRatingById {}" , id);
        List<Rating> list = ratingService.getRatingById(id);
        logger.info("getting ratings with id {} successfull",id);
        logger.info("converting dto to list");
        List<RatingDto> ratingList = list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ratingList);
    }

    @PostMapping("")
    public ResponseEntity<RatingDto> giveRating(@RequestBody RequestDto requestDto){
        logger.info("new rating");
        logger.info("checking for appointments with appointment id");
        var appointmentDto = appointmentService.getAppointment(requestDto.appointmentId());
        if(appointmentDto == null){
            logger.info("appointment not found with the id: {} ",requestDto.appointmentId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        logger.info("appointment found with appointment id {} ",appointmentDto.appointmentId());

        var existindrating = ratingService.getReviewByAppointmentIdandUserId(requestDto.appointmentId(),appointmentDto.userId());
        logger.info("calculating the new rating");
        var updatedRating = ((ratingService.getReviewByDoctor(appointmentDto.doctorId()))+requestDto.rating())/2;

        DoctorRequestDto dto = new DoctorRequestDto(appointmentDto.doctorId(),appointmentDto.doctorName(),appointmentDto.doctorSpecialization(),appointmentDto.doctorLocation(),updatedRating);
        logger.info("updating the doctor with new rating");
        doctorService.setDoctor(dto,appointmentDto.doctorId());

        logger.info("creating a new rating object");
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

        logger.info("saving the rating");
        ratingService.saveRating(rating);



        return ResponseEntity.status(HttpStatus.OK).body(convertToDto(rating));

    }


    public RatingDto convertToDto(Rating rating){
        return new RatingDto(rating.getRatingId(),rating.getRating(),rating.getReview(),rating.getDoctorId()
                ,rating.getDoctorName(), rating.getDoctorSpecialization(), rating.getDoctorLocation(), rating.getAppointmentId(),rating.getAppointmentDate(),rating.getAppointmentTime(),rating.getUserId());
    }

}
