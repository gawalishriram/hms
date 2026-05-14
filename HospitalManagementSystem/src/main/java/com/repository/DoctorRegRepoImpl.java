package com.repository;

import com.model.DoctorModel;

public class DoctorRegRepoImpl extends DBconfig implements DoctorRegRepo{

	@Override
	public boolean addDoctor(DoctorModel model) {
		try
		{
			pst=con.prepareStatement("insert into doctors values('0',?,?,?,?,?)");
			pst.setString(1, model.getName());
			pst.setString(2,model.getSpecialization());
			pst.setInt(3, model.getExperience());
			pst.setLong(4, model.getMobile());
			pst.setString(5, model.getPassword());
			int val=pst.executeUpdate();
			return val>0? true:false;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
