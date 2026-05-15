package com.service;

import java.util.List;

import com.model.DoctorModel;
import com.model.PatientAppointmentModel;
import com.repository.DoctorRegRepo;
import com.repository.DoctorRegRepoImpl;

public class DoctorServiceImpl implements DoctorService {

    DoctorRegRepo doctorRegRepo = new DoctorRegRepoImpl();

    @Override
    public boolean addDoctor(DoctorModel model) {
        return doctorRegRepo.addDoctor(model);
    }

    @Override
    public boolean getDoctorLogin(String email, String password) {
        return doctorRegRepo.getDoctorLogin(email, password);
    }

    @Override
    public List<DoctorModel> getAllDoctors() {
        return doctorRegRepo.getAllDoctors();
    }

    @Override
    public DoctorModel getDoctorByEmail(String email) {
        return doctorRegRepo.getDoctorByEmail(email);
    }

	@Override
	public boolean changeDoctorPassword(String email, String oldPassword, String newPassword) {
		
		return doctorRegRepo.changeDoctorPassword(
				email,
				oldPassword,
				newPassword);
	}
	
	@Override
	public List<PatientAppointmentModel>
	getDoctorAppointments(String email) {

		return doctorRegRepo
		.getDoctorAppointments(email);
	}

	@Override
	public boolean
	updateAppointmentStatus(
	int id,
	String status) {

		return doctorRegRepo
		.updateAppointmentStatus(
		id,
		status);
	}
}