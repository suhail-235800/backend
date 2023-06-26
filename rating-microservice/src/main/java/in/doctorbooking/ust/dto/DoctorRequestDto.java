package in.doctorbooking.ust.dto;

public record DoctorRequestDto(
        int doctorId,
        String doctorName,
        String doctorSpecialization,
        String doctorLocation,
        double doctorRating) {
}
