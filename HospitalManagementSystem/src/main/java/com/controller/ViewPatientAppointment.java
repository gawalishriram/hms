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

import com.model.AppointmentModel;
import com.model.PatientModel;
import com.service.AppointmentService;
import com.service.AppointmentServiceImpl;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/viewpatientappointment")
public class ViewPatientAppointment extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		HttpSession session =
		request.getSession(false);

		if(session == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		String email =
		(String)session.getAttribute("email");

		if(email == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		RegisterPatient patientService =
		new RegisterPatientImpl();

		PatientModel patient =
		patientService.getPatientByEmail(email);

		long pid = patient.getId();

		request.setAttribute("includePage", "yes");

		RequestDispatcher r =
		request.getRequestDispatcher("patientdashboard");

		r.include(request, response);

		AppointmentService service =
		new AppointmentServiceImpl();

		List<AppointmentModel> list =
		service.getPatientAppointments(pid);

		out.println("<div class='container mt-4'>");

		out.println("<div class='card shadow-lg border-0'>");

		out.println("<div class='card-header bg-success text-white'>");

		out.println("<h3 class='mb-0'>My Appointments</h3>");

		out.println("</div>");

		out.println("<div class='card-body'>");

		if(list.size() > 0)
		{
			out.println("<table class='table table-bordered table-hover text-center'>");

			out.println("<tr class='table-success'>");

			out.println("<th>Patient Name</th>");
			out.println("<th>Doctor Name</th>");
			out.println("<th>Appointment Date</th>");
			out.println("<th>Status</th>");

			out.println("</tr>");

			for(AppointmentModel model : list)
			{
				out.println("<tr>");

				out.println("<td>"
				+model.getPatientName()+
				"</td>");

				out.println("<td>"
				+model.getDoctorName()+
				"</td>");

				out.println("<td>"
				+model.getAppointmentDate()+
				"</td>");

				out.println("<td>");

				if(model.getStatus().equalsIgnoreCase("Pending"))
				{
					out.println("<span class='badge bg-warning text-dark'>"
					+model.getStatus()+
					"</span>");
				}
				else
				{
					out.println("<span class='badge bg-success'>"
					+model.getStatus()+
					"</span>");
				}

				out.println("</td>");

				out.println("</tr>");
			}

			out.println("</table>");
		}
		else
		{
			out.println("<h4 class='text-danger text-center'>No Appointments Found</h4>");
		}

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");
	}

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		doGet(request, response);
	}
}