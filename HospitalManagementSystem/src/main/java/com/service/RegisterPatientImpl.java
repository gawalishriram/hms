package com.service;

import java.util.List;

import com.model.PatientModel;
import com.repository.PatientRegRepo;
import com.repository.PatientRegRepoImpl;

public class RegisterPatientImpl implements RegisterPatient{

	PatientRegRepo patientRegRepo=new PatientRegRepoImpl();
	@Override
	public boolean addPatient(PatientModel model) {
		
		return patientRegRepo.addPatient(model);
	}
	@Override
	public boolean getPatientLogin(String email, String password) {
		// TODO Auto-generated method stub
		return patientRegRepo.getPatientLogin(email, password);
	}
	@Override
	public PatientModel getPatientByEmail(String email) {
		// TODO Auto-generated method stub
		return  patientRegRepo.getPatientByEmail(email);
	}
	
	@Override
	public boolean getUpdatePatient(PatientModel model) {
		
		return  patientRegRepo.getUpdatePatient(model);
	}
	@Override
	public boolean getChangePasswordPatient(String email,String password) {
		
		return patientRegRepo.getChangePasswordPatient(email,password);
	}
	
	
	
	

}