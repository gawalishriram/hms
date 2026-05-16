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

import com.model.PatientAppointmentModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/viewAppointmentsDoctor")
public class ViewAppointmentsDoctor
extends HttpServlet {

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

		request.setAttribute(
		"includePage",
		"yes");

		RequestDispatcher r =
		request.getRequestDispatcher(
		"doctordashboard");

		r.include(request, response);

		DoctorService service =
		new DoctorServiceImpl();

		List<PatientAppointmentModel>
		list =
		service.getDoctorAppointments(
		email);

		out.println(
		"<div class='container-fluid' style='margin-left:300px;width:75%;padding-top:20px;'>");

		out.println(
		"<div class='card shadow-lg border-0 rounded-4'>");

		out.println(
		"<div class='card-body p-4'>");

		out.println(
		"<h2 class='text-primary mb-4 fw-bold'>My Appointments</h2>");

		out.println(
		"<table class='table table-bordered table-hover'>");

		out.println(
		"<tr class='table-primary'>");

		out.println("<th>ID</th>");
		out.println("<th>Patient Name</th>");
		out.println("<th>Disease</th>");
		out.println("<th>Date & Time</th>");
		out.println("<th>Status</th>");
		out.println("<th>Action</th>");

		out.println("</tr>");

		for(PatientAppointmentModel model:list)
		{
			out.println("<tr>");

			out.println("<td>"+model.getId()+"</td>");

			out.println("<td>"+model.getPatientName()+"</td>");

			out.println("<td>"+model.getDisease()+"</td>");

			out.println("<td>"+model.getAppointmentDate()+"</td>");

			out.println("<td>"+model.getStatus()+"</td>");

			out.println("<td>");

			out.println("<a href='updateAppointmentStatus?id="+model.getId()+"&status=Approved' class='btn btn-success btn-sm'>Accept</a>");

			out.println(" ");

			out.println("<a href='updateAppointmentStatus?id="+model.getId()+"&status=Rejected' class='btn btn-danger btn-sm'>Reject</a>");

			out.println("</td>");

			out.println("</tr>");
		}

		out.println("</table>");

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	}
}