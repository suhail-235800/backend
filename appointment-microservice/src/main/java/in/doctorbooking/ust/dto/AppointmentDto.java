package in.doctorbooking.ust.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDto (int appointmentId,
                              @JsonFormat(pattern = "yyyy-MM-dd")
                              @JsonDeserialize(using = LocalDateDeserializer.class)
                              LocalDate appointmentDate,
                              @JsonFormat(pattern = "HH:mm")
                              @JsonDeserialize(using = LocalTimeDeserializer.class)
                              LocalTime appointmentTime,
                              int doctorId,
                              String doctorName,
                              String doctorSpecialization,
                              String doctorLocation,
                              int userId){
}
