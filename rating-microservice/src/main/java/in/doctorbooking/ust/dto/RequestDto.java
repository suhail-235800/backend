package in.doctorbooking.ust.dto;

public record RequestDto (
        int rating,
        String review,
        int appointmentId
){
}
