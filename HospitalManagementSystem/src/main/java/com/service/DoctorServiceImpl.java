package com.service;

import com.model.DoctorModel;
import com.repository.DoctorRegRepo;
import com.repository.DoctorRegRepoImpl;

public class DoctorServiceImpl implements DoctorService {

	DoctorRegRepo doctorRegRepo=new DoctorRegRepoImpl();
	@Override
	public boolean addDoctor(DoctorModel model) {
		
		return doctorRegRepo.addDoctor(model);
	}

}
