package in.doctorbooking.ust.service;

import in.doctorbooking.ust.dto.AppointmentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private RestTemplate restTemplate;

    public AppointmentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String url="http://APPOINTMENT-SERVICE/api/v1/appointments/{id}";
    @Override
    public AppointmentDto getAppointment(int id) {
        try{
            var response = restTemplate.getForEntity(url, AppointmentDto.class,id);
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
