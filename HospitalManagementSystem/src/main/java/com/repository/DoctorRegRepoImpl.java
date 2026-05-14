package com.repository;

import com.model.DoctorModel;
import com.model.PatientModel;

public class DoctorRegRepoImpl extends DBconfig implements DoctorRegRepo{

	@Override
	public boolean addDoctor(DoctorModel model) {
		try
		{
			pst = con.prepareStatement(
				   "INSERT INTO doctors(name, specialization, experience, mobile, email, password) VALUES (?,?,?,?,?,?)"
				);			
			pst.setString(1, model.getName());
			pst.setString(2,model.getSpecialization());
			pst.setInt(3, model.getExperience());
			pst.setLong(4, model.getMobile());
			pst.setString(5,model.getEmail());
			pst.setString(6, model.getPassword());
			int val=pst.executeUpdate();
			return val>0? true:false;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean getDoctorLogin(String email, String password) {
		try
		{
			pst=con.prepareStatement("select * from doctors where email=? and password=?");
			pst.setString(1,email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next())
			{
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public DoctorModel getDoctorByEmail(String email) {
		try
		{
			pst = con.prepareStatement("select * from doctors where email=?");
			pst.setString(1, email);

			rs = pst.executeQuery();

			if(rs.next())
			{
				DoctorModel model = new DoctorModel();

				model.setId(rs.getInt(1));
				model.setName(rs.getString(2));
				model.setSpecialization(rs.getString(3));
				model.setExperience(rs.getInt(4));
				model.setMobile(rs.getLong(5));
				model.setEmail(rs.getString(6));
				model.setPassword(rs.getString(7));

				return model;
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}

		return null;
	}
	

}