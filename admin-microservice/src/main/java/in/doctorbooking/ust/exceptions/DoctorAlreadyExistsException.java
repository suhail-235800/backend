package in.doctorbooking.ust.exceptions;


public class DoctorAlreadyExistsException extends RuntimeException{

    public DoctorAlreadyExistsException(String message){
        super(message);
    }
}
