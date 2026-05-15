package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.AppointmentModel;
import com.service.AppointmentService;
import com.service.AppointmentServiceImpl;

@WebServlet("/patientappointment")
public class PatientAppointment extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String pid = request.getParameter("pid");
		String did = request.getParameter("did");
		String date = request.getParameter("appointmentdate");
		String time = request.getParameter("appointmenttime");

		String dateTime = date + " " + time + ":00";
		
		request.setAttribute("includePage", "yes");
		
		AppointmentModel model=new AppointmentModel();
		model.setPatientId(Long.parseLong(pid));
		model.setDoctorId(Long.parseLong(did));
		model.setAppointmentDate(dateTime);
		model.setStatus("Pending");
		
		AppointmentService service =  new AppointmentServiceImpl();
		boolean res=service.addAppointment(model);
		
		if(res)
		{
			response.sendRedirect("viewpatientappointment");
		}
		else
		{
			out.println("not insert data into appointment table");
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
