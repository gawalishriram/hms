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

	


}
