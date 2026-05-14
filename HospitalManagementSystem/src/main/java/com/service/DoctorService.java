package com.service;

import com.model.DoctorModel;
import com.model.PatientModel;

public interface DoctorService {
	public boolean addDoctor(DoctorModel model);
	public boolean getDoctorLogin(String email,String password);
	public DoctorModel getDoctorByEmail(String email);
}