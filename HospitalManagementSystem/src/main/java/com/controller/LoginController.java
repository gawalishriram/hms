package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.service.DoctorService;
import com.service.DoctorServiceImpl;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RegisterPatient patient = new RegisterPatientImpl();
		DoctorService doctor=new DoctorServiceImpl();

		boolean patientResult =patient.getPatientLogin(email, password);
		boolean doctorResult=doctor.getDoctorLogin(email, password);

		if(role.equals("patient") && patientResult)
		{
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("patientdashboard");
			return;
		}
		else if(role.equals("doctor") && doctorResult)
		{
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("doctordashboard");
			return;
		}
		else
		{
			out.println("<h2 class='text-center'>Invalid Email or Password</h2>");
		}
	}
}