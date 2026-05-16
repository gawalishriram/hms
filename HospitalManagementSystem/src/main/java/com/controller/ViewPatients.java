package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.model.PatientModel;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/ViewPatients")
public class ViewPatients extends HttpServlet {

	protected void doGet(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out =
		response.getWriter();

		HttpSession session =
		request.getSession(false);

		if(session == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		String email =
		(String) session.getAttribute("email");

		if(email == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		request.setAttribute(
		"includePage",
		"yes");

		RequestDispatcher r =
		request.getRequestDispatcher(
		"doctordashboard");

		r.include(request, response);

		RegisterPatient service =
		new RegisterPatientImpl();

		List<PatientModel> list =
		service.getAllPatients(email);

		out.println(
		"<div class='container-fluid' style='margin-left:300px;width:75%;padding-top:20px;'>");

		out.println(
		"<div class='card shadow-lg border-0 rounded-4'>");

		out.println(
		"<div class='card-body p-4'>");

		out.println(
		"<h2 class='text-primary mb-4 fw-bold'>View Patients</h2>");

		out.println(
		"<table class='table table-bordered table-hover'>");

		out.println(
		"<tr class='table-primary'>");

		out.println("<th>ID</th>");
		out.println("<th>Name</th>");
		out.println("<th>Email</th>");
		out.println("<th>Mobile</th>");
		out.println("<th>Disease</th>");
		out.println("<th>Date & Time<th>");

		out.println("</tr>");

		for(PatientModel model:list)
		{
			out.println("<tr>");

			out.println("<td>"+model.getId()+"</td>");

			out.println("<td>"+model.getName()+"</td>");

			out.println("<td>"+model.getEmail()+"</td>");

			out.println("<td>"+model.getMobile()+"</td>");

			out.println("<td>"+model.getDisease()+"</td>");
			
			out.println("<td>"+model.getAppointmentDate()+"</td>");

			out.println("</tr>");
		}

		out.println("</table>");

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	}
}