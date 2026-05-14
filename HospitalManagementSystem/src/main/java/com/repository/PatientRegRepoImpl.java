package com.repository;

import java.sql.DriverManager;
import java.util.List;

import com.model.PatientModel;

public class PatientRegRepoImpl extends DBconfig implements PatientRegRepo{
	public boolean addPatient(PatientModel model) 
	{
        try 
        {
            pst = con.prepareStatement("INSERT INTO patients(name,age,gender,mobile,email,disease,password) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, model.getName());
            pst.setInt(2, model.getAge());
            pst.setString(3, model.getGender());
            pst.setLong(4, model.getMobile());
            pst.setString(5, model.getEmail());
            pst.setString(6, model.getDisease());
            pst.setString(7, model.getPassword());
            int val = pst.executeUpdate();
            return val > 0;

        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return false;
    }

	@Override
	public boolean getPatientLogin(String email, String password) {
		try
		{
			pst=con.prepareStatement("SELECT * FROM patients WHERE email=? AND password=?");
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			if(rs.next())
			{
				return true;
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}

	@Override
	public PatientModel getPatientByEmail(String email) {
		try
		{
			pst=con.prepareStatement("select * from patients where email=?");
			pst.setString(1, email);
			rs=pst.executeQuery();
			if(rs.next())
			{
				PatientModel model=new PatientModel();
				model.setId(rs.getInt(1));				
				model.setName(rs.getString(2));
				model.setAge(rs.getInt(3));
				model.setGender(rs.getString(4));
				model.setMobile(rs.getLong(5));
				model.setEmail(rs.getString(6));
				model.setDisease(rs.getString(7));
				model.setPassword(rs.getString(8));
				return model;
			}
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return null;
	}

	@Override
	public boolean getUpdatePatient(PatientModel model) {
		try
		{
			pst=con.prepareStatement("update patients SET name=?, age=?, gender=?, mobile=?, disease=? WHERE email=?");
			pst.setString(1, model.getName());
			pst.setInt(2, model.getAge());
			pst.setString(3, model.getGender());
			pst.setLong(4, model.getMobile());
			pst.setString(5, model.getDisease());
			pst.setString(6, model.getEmail());
			
			int val=pst.executeUpdate();
			return val>0 ? true:false;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	


}