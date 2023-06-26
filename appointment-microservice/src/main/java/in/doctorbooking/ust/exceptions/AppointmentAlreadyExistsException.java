package in.doctorbooking.ust.exceptions;

public class AppointmentAlreadyExistsException extends RuntimeException{

    public AppointmentAlreadyExistsException(String message){
        super(message);
    }
}
