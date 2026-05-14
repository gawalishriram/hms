package com.service;

import com.model.DoctorModel;
import com.model.PatientModel;
import com.repository.DoctorRegRepo;
import com.repository.DoctorRegRepoImpl;

public class DoctorServiceImpl implements DoctorService {

	DoctorRegRepo doctorRegRepo=new DoctorRegRepoImpl();
	@Override
	public boolean addDoctor(DoctorModel model) {
		
		return doctorRegRepo.addDoctor(model);
	}
	@Override
	public boolean getDoctorLogin(String email, String password) {
		// TODO Auto-generated method stub
		return doctorRegRepo.getDoctorLogin(email, password);
	}
	@Override
	public DoctorModel getDoctorByEmail(String email) {

		return doctorRegRepo.getDoctorByEmail(email);
	}

}