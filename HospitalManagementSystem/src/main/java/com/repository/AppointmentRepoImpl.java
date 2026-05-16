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

			"insert into patient_appointments(patient_id,doctor_id,disease,appointment_date,status) values(?,?,?,?,?)");

			pst.setLong(1,
			model.getPatientId());

			pst.setLong(2,
			model.getDoctorId());

			pst.setString(3,
			model.getDisease());

			pst.setString(4,
			model.getAppointmentDate());

			pst.setString(5,
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

					"SELECT p.name AS patient_name," +

					"d.name AS doctor_name," +

					"a.appointment_date," +

					"a.status " +

					"FROM patient_appointments a " +

					"JOIN patients p " +

					"ON a.patient_id=p.id " +

					"JOIN doctors d " +

					"ON a.doctor_id=d.id " +

					"WHERE a.patient_id=? " +

					"ORDER BY a.appointment_date DESC");

					pst.setLong(1,pid);
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