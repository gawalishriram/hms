package com.service;

import java.util.List;

import com.model.AppointmentModel;
import com.repository.AppointmentRepo;
import com.repository.AppointmentRepoImpl;

public class AppointmentServiceImpl implements AppointmentService {

	AppointmentRepo repo = new AppointmentRepoImpl();

	@Override
	public boolean addAppointment(AppointmentModel model) {

		return repo.addAppointment(model);
	}

	@Override
	public List<AppointmentModel> getPatientAppointments(long pid) {

		return repo.getPatientAppointments(pid);
	}
}