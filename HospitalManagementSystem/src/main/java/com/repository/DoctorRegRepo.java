package com.repository;

import java.util.List;

import com.model.DoctorModel;

public interface DoctorRegRepo {
	public boolean addDoctor(DoctorModel model);
	public boolean getDoctorLogin(String email,String password);
	public List<DoctorModel> getAllDoctors();
}