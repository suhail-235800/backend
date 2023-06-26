package in.doctorbooking.ust.service;

import in.doctorbooking.ust.dto.DoctorDto;
import in.doctorbooking.ust.dto.DoctorRequestDto;

public interface DoctorService {

    void setDoctor(DoctorRequestDto dto, int id);
}
