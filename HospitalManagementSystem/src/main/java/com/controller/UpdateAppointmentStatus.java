package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/updateAppointmentStatus")
public class UpdateAppointmentStatus
extends HttpServlet {

	protected void doGet(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		int id =
		Integer.parseInt(
		request.getParameter("id"));

		String status =
		request.getParameter("status");

		DoctorService service =
		new DoctorServiceImpl();

		service.updateAppointmentStatus(
		id,
		status);

		response.sendRedirect(
		"viewAppointmentsDoctor");
	}
}