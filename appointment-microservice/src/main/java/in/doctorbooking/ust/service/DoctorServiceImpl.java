package in.doctorbooking.ust.service;

import in.doctorbooking.ust.domain.Doctor;
import in.doctorbooking.ust.dto.DoctorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DoctorServiceImpl implements DoctorService {

    private RestTemplate restTemplate;

    public DoctorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String url = "http://ADMIN-SERVICE/api/v1/admin/id/{id}";
    private Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Override
    public DoctorDto getDoctorById(int id) {
        try{
            final var response = restTemplate.getForEntity(url,DoctorDto.class,id);
            logger.info(String.valueOf(response.getBody()));

            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}