package in.doctorbooking.ust.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public record RequestDto(String appointmentDate, String appointmentTime, int doctorId) {
}
