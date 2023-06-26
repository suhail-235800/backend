package in.doctorbooking.ust.service;

import in.doctorbooking.ust.dto.AppointmentDto;
import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.DoctorRequestDto;
import in.doctorbooking.ust.dto.RequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DoctorServiceImpl implements DoctorService{

    private RestTemplate restTemplate;

    public DoctorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }




    @Override
    public void setDoctor(DoctorRequestDto dto, int id) {
        String url = "http://ADMIN-SERVICE/api/v1/admin/id/"+id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DoctorRequestDto> entity = new HttpEntity<>(dto, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
    }
}

