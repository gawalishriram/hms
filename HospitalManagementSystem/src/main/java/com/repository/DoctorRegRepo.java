package com.repository;

import com.model.DoctorModel;

public interface DoctorRegRepo {
	public boolean addDoctor(DoctorModel model);
	public boolean getDoctorLogin(String email,String password);
}