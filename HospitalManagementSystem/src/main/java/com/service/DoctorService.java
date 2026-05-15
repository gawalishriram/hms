package com.service;

import java.util.List;
import com.model.DoctorModel;

public interface DoctorService {

    boolean addDoctor(DoctorModel model);

    boolean getDoctorLogin(String email, String password);

    List<DoctorModel> getAllDoctors();

    DoctorModel getDoctorByEmail(String email);
    public boolean changeDoctorPassword(
    		String email,
    		String oldPassword,
    		String newPassword);
}