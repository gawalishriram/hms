package com.repository;

import java.util.List;

import com.model.PatientModel;

public interface PatientRegRepo {
	public boolean addPatient(PatientModel model);
	public boolean getPatientLogin(String email,String password);
	public PatientModel getPatientByEmail(String email);
	public boolean getUpdatePatient(PatientModel model);
	public boolean getChangePasswordPatient(String email,String password);
	public List<PatientModel>getAllPatients(String email);
}