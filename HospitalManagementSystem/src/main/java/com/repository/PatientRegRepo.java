package com.repository;

import java.util.List;

import com.model.PatientModel;

public interface PatientRegRepo {
	public boolean addPatient(PatientModel model);
	public boolean getPatientLogin(String email,String password);
}
