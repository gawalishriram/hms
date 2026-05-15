package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.model.DoctorModel;

public class DoctorRegRepoImpl extends DBconfig implements DoctorRegRepo {

    @Override
    public boolean addDoctor(DoctorModel model) {
        try {
            pst = con.prepareStatement(
                "INSERT INTO doctors(name, specialization, experience, mobile, email, password) VALUES (?,?,?,?,?,?)"
            );

            pst.setString(1, model.getName());
            pst.setString(2, model.getSpecialization());
            pst.setInt(3, model.getExperience());
            pst.setLong(4, model.getMobile());
            pst.setString(5, model.getEmail());
            pst.setString(6, model.getPassword());

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Add Doctor Error: " + e);
            return false;
        }
    }

    @Override
    public boolean getDoctorLogin(String email, String password) {
        try {
            pst = con.prepareStatement(
                "SELECT * FROM doctors WHERE email=? AND password=?"
            );

            pst.setString(1, email);
            pst.setString(2, password);

            rs = pst.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Login Error: " + e);
            return false;
        }
    }

    @Override
    public List<DoctorModel> getAllDoctors() {
        List<DoctorModel> list = new ArrayList<>();

        try {
            pst = con.prepareStatement("SELECT * FROM doctors");
            rs = pst.executeQuery();

            while (rs.next()) {
                DoctorModel model = new DoctorModel();

                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setSpecialization(rs.getString("specialization"));
                model.setExperience(rs.getInt("experience"));
                model.setMobile(rs.getLong("mobile"));
                model.setEmail(rs.getString("email"));
                model.setPassword(rs.getString("password"));

                list.add(model);
            }

        } catch (Exception e) {
            System.out.println("Fetch Doctors Error: " + e);
        }

        return list;
    }

    @Override
    public DoctorModel getDoctorByEmail(String email) {
        try {
            pst = con.prepareStatement(
                "SELECT * FROM doctors WHERE email=?"
            );

            pst.setString(1, email);

            rs = pst.executeQuery();

            if (rs.next()) {
                DoctorModel model = new DoctorModel();

                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setSpecialization(rs.getString("specialization"));
                model.setExperience(rs.getInt("experience"));
                model.setMobile(rs.getLong("mobile"));
                model.setEmail(rs.getString("email"));
                model.setPassword(rs.getString("password"));

                return model;
            }

        } catch (Exception e) {
            System.out.println("Get Doctor By Email Error: " + e);
        }

        return null;
    }

	@Override
	public boolean changeDoctorPassword(
			String email,
			String oldPassword,
			String newPassword) {

				try
				{
					pst = con.prepareStatement(
					"update doctors set password=? where email=? and password=?");

					pst.setString(1, newPassword);
					pst.setString(2, email);
					pst.setString(3, oldPassword);

					int val = pst.executeUpdate();

					return val > 0 ? true : false;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				return false;
			}
}