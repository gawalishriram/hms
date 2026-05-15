package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.repository.PatientRegRepo;
import com.repository.PatientRegRepoImpl;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/patientchangepassword")
public class PatientChangePassword extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		RegisterPatient patient=new RegisterPatientImpl();
		boolean result=patient.getChangePasswordPatient(email, password);
		
		if(result)
		{
			response.sendRedirect("patientpassword");
			
		}
		else
		{
			out.println("Not Changed Password..");
		}
		
		RequestDispatcher r=request.getRequestDispatcher("");
		r.include(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
