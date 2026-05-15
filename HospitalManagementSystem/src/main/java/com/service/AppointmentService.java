package com.service;

import java.util.List;

import com.model.AppointmentModel;

public interface AppointmentService {
	
	public boolean addAppointment(AppointmentModel model);
	public List<AppointmentModel> getPatientAppointments(long pid);
	
}
