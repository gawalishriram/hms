package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.model.AppointmentModel;
import com.model.PatientModel;
import com.service.AppointmentService;
import com.service.AppointmentServiceImpl;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/patientappointment")
public class PatientAppointment extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		//request.getParameter("pid")
		// add session logic here and remove above method 

		HttpSession session =request.getSession(false);

				String email =
				(String) session.getAttribute("email");

				RegisterPatient patientService =
				new RegisterPatientImpl();

				PatientModel patient =
				patientService.getPatientByEmail(email);
				String patientName =patient.getName();
// add new above method
				long pid =
				patient.getId();

		String did = request.getParameter("did");

		//String patientName =
		//request.getParameter("patientName");
		// remove above code
		String dName =
		request.getParameter("dName");

		String date =
		request.getParameter("appointmentdate");

		String time =
		request.getParameter("appointmenttime");

		String dateTime = date + " " + time + ":00";

		AppointmentModel model =
		new AppointmentModel();

		model.setPatientId(pid);

		model.setDoctorId(Long.parseLong(did));

		//model.setPatientName(patientName);
		// above code 
		model.setDoctorName(dName);
		model.setPatientName(patientName);
		// add new method above
		model.setAppointmentDate(dateTime);
		model.setDisease(patient.getDisease());
		//add new above mehthod
		

		model.setStatus("Pending");

		AppointmentService service =
		new AppointmentServiceImpl();

		boolean res =
		service.addAppointment(model);

		if(res)
		{
			response.sendRedirect(
			"viewpatientappointment?pid="+pid);
		}
		else
		{
			out.println(
			"not insert data into appointment table");
		}
	}

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		doGet(request, response);
	}
}