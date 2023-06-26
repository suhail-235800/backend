package in.doctorbooking.ust.domain;


public class Doctor
{

    private int doctorId;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorLocation;
    private int doctorRating;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public void setDoctorLocation(String doctorLocation) {
        this.doctorLocation = doctorLocation;
    }
    public Doctor(){

    }

    public int getDoctorRating() {
        return doctorRating;
    }

    public void setDoctorRating(int doctorRating) {
        this.doctorRating = doctorRating;
    }

    public Doctor(int doctorId, String doctorName, String doctorSpecialization, String doctorLocation, int doctorRating) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.doctorLocation = doctorLocation;
        this.doctorRating = doctorRating;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpecialization='" + doctorSpecialization + '\'' +
                ", doctorLocation='" + doctorLocation + '\'' +
                ", doctorRating=" + doctorRating +
                '}';
    }
}