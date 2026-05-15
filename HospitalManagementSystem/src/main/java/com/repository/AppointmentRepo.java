package com.repository;

import java.util.List;

import com.model.AppointmentModel;

public interface AppointmentRepo {
	public boolean addAppointment(AppointmentModel model);
	public List<AppointmentModel> getPatientAppointments(long pid);
}
