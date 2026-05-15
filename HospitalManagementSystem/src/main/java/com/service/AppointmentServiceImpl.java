package com.service;

import com.model.AppointmentModel;
import com.repository.AppointmentRepo;
import com.repository.AppointmentRepoImpl;

public class AppointmentServiceImpl implements AppointmentService{

	AppointmentRepo repo=new AppointmentRepoImpl();
	@Override
	public boolean addAppointment(AppointmentModel model) {
		// TODO Auto-generated method stub
		return repo.addAppointment(model);
	}

}
