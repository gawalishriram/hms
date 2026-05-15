package com.repository;

import com.model.AppointmentModel;

public class AppointmentRepoImpl extends DBconfig implements AppointmentRepo {

	@Override
	public boolean addAppointment(AppointmentModel model) {
		try
		{
			pst=con.prepareStatement("insert into appointments values('0',?,?,?,?)");
			pst.setLong(1, model.getPatientId());
			pst.setLong(2, model.getDoctorId());
			pst.setString(3,model.getAppointmentDate());
			pst.setString(4, model.getStatus());
			int val=pst.executeUpdate();
			
			return val>0 ? true : false;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}

}
