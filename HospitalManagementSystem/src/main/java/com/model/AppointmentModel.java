package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppointmentModel {
	private long id;
    private long patientId;
    private long doctorId;
    private String appointmentDate;
    private String status;

}
