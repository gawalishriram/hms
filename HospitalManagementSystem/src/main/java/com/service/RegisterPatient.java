package com.service;

import java.util.List;
import java.util.Optional;

import com.model.PatientModel;

public interface RegisterPatient {
	public boolean addPatient(PatientModel model); 
    public boolean getPatientLogin(String email,String password);
}
