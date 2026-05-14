package com.service;

import com.model.DoctorModel;

public interface DoctorService {
	public boolean addDoctor(DoctorModel model);
	public boolean getDoctorLogin(String email,String password);
}