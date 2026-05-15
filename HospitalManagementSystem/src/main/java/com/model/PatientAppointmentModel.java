package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PatientAppointmentModel {

	private int id;

	private String patientName;

	private String disease;

	private String appointmentDate;

	private String status;
}