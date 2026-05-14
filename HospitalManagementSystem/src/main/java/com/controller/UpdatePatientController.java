package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.model.PatientModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/updatepatient")
public class UpdatePatientController extends HttpServlet {

	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String gender=request.getParameter("gender");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");	
		String disease=request.getParameter("disease");	
		
		RegisterPatient patient=new RegisterPatientImpl();
		PatientModel model=new PatientModel();
		
		
		
		RequestDispatcher r=request.getRequestDispatcher("profilepatient");
		r.include(request, response);
		
	}
}