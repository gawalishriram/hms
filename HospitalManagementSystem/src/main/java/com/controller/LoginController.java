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

		// Get Form Data
		String role = request.getParameter("role");

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		// Create Service Objects
		RegisterPatient patient =
		new RegisterPatientImpl();

		DoctorService doctor =
		new DoctorServiceImpl();

		// Check Login
		boolean patientResult =
		patient.getPatientLogin(email, password);

		boolean doctorResult =
		doctor.getDoctorLogin(email, password);

		// PATIENT LOGIN
		if(role.equals("patient") && patientResult)
		{
			HttpSession session =
			request.getSession();

			session.setAttribute("email", email);

			session.setAttribute("role", "patient");

			response.sendRedirect("patientdashboard");

			return;
		}

		// DOCTOR LOGIN
		else if(role.equals("doctor") && doctorResult)
		{
			HttpSession session =
			request.getSession();

			session.setAttribute("email", email);

			session.setAttribute("role", "doctor");

			response.sendRedirect("doctordashboard");

			return;
		}

		// INVALID LOGIN
		else
		{
			out.println("<!DOCTYPE html>");

			out.println("<html>");

			out.println("<head>");

			out.println("<title>Login Failed</title>");

			out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

			out.println("</head>");

			out.println("<body class='bg-light'>");

			out.println("<div class='container mt-5'>");

			out.println("<div class='card shadow p-5 text-center'>");

			out.println("<h2 class='text-danger'>Invalid Email or Password</h2>");

			out.println("<a href='login.html' class='btn btn-success mt-4'>Try Again</a>");

			out.println("</div>");

			out.println("</div>");

			out.println("</body>");

			out.println("</html>");
		}

		out.close();
	}
}