package com.repository;

import com.model.DoctorModel;
import com.model.PatientModel;

public interface DoctorRegRepo {
	public boolean addDoctor(DoctorModel model);
	public boolean getDoctorLogin(String email,String password);
	public DoctorModel getDoctorByEmail(String email);
}