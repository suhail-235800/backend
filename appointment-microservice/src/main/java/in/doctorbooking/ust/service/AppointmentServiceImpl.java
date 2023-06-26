package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Appointment;
import in.doctorbooking.ust.exceptions.AppointmentNotFoundException;
import in.doctorbooking.ust.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{


    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAllAppointments() {
        List<Appointment> test = appointmentRepository.findAll();
        if (test == null || test.isEmpty()) {
            throw new AppointmentNotFoundException("No appointment found");
        }
        return test;
    }


    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        var appointment = appointmentRepository.findById(id);
        if(appointment.isEmpty()){
            throw new AppointmentNotFoundException("Appointment not found with id:"+id);
        }
        return appointment.get();
    }

    @Override
    public Appointment getDoctorAppointmentsBydateandtime(int i, LocalDate localDate, LocalTime localTime) {
        var appointment = appointmentRepository.findByAppointmentDateAndAppointmentTime(localDate, localTime);
        if (appointment != null && appointment.getAppointmentId() == i) {
            return appointment;
        } else {
            return null;
        }
    }

    @Override
    public List<Appointment> findAppointmentsByUserId(int userId) {
        var list = appointmentRepository.findByUserId(userId);
        if(list.isEmpty()){
            throw new AppointmentNotFoundException("Appointment not found with userId:"+userId);
        }
        return list;
    }

    @Override
    public List<Appointment> findAppointmentsByDoctorName(String doctorName) {
        var list = appointmentRepository.findByDoctorName(doctorName);
        if(list.isEmpty()){
            throw new AppointmentNotFoundException("Appointment not found with doctorName"+doctorName);
        }
        return list;
    }


}
