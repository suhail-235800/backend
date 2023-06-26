package in.doctorbooking.ust.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "doctors")
public class Doctor
{
    @Id
    @GeneratedValue
    private int doctorId;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorLocation;
    private double doctorRating;


    public Doctor(int doctorId, String doctorName, String doctorSpecialization, String doctorLocation, double doctorRating) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorLocation = doctorLocation;
        this.doctorRating = doctorRating;
    }
}