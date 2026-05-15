package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.model.DoctorModel;

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
	public List<DoctorModel> getAllDoctors() {
		List<DoctorModel> list=new ArrayList<DoctorModel>();
		try
		{
			pst=con.prepareStatement("select * from doctors");
			rs=pst.executeQuery();
			while(rs.next())
			{
				DoctorModel model=new DoctorModel();
				model.setName(rs.getString("name"));
				model.setSpecialization(rs.getString("specialization"));
				model.setExperience(rs.getInt("experience"));
				model.setId(rs.getInt("id"));
				list.add(model);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}