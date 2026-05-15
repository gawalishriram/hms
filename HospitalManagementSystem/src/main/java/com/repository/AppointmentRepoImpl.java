package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.model.AppointmentModel;

public class AppointmentRepoImpl extends DBconfig
implements AppointmentRepo {

	@Override
	public boolean addAppointment(AppointmentModel model) {

		try {

			pst = con.prepareStatement(
			"insert into appointments values(0,?,?,?,?,?,?)");

			pst.setLong(1,
			model.getPatientId());

			pst.setLong(2,
			model.getDoctorId());

			pst.setString(3,
			model.getPatientName());

			pst.setString(4,
			model.getDoctorName());

			pst.setString(5,
			model.getAppointmentDate());

			pst.setString(6,
			model.getStatus());

			int val = pst.executeUpdate();

			return val > 0;

		}
		catch (Exception e) {

			System.out.println(e);
		}

		return false;
	}

	@Override
	public List<AppointmentModel>
	getPatientAppointments(long pid) {

		List<AppointmentModel> list =
		new ArrayList<>();

		try {

			pst = con.prepareStatement(

			"SELECT patient_name, doctor_name, appointment_date, status FROM appointments WHERE patient_id=? ORDER BY appointment_date DESC");

			pst.setLong(1, pid);

			rs = pst.executeQuery();

			while(rs.next()) {

				AppointmentModel model =
				new AppointmentModel();

				model.setPatientName(
				rs.getString("patient_name"));

				model.setDoctorName(
				rs.getString("doctor_name"));

				model.setAppointmentDate(
				rs.getString("appointment_date"));

				model.setStatus(
				rs.getString("status"));

				list.add(model);
			}

		}
		catch (Exception e) {

			System.out.println(e);
		}

		return list;
	}
}