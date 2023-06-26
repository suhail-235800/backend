package in.doctorbooking.ust.dto;

public record DoctorDto(
        int doctorId,
        String doctorName,
        String doctorSpecialization,
        String doctorLocation,
        int doctorRating) {
}
