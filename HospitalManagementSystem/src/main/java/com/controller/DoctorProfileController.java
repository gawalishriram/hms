package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.model.DoctorModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/doctorprofile")
public class DoctorProfileController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		// SESSION CHECK
		HttpSession session = request.getSession(false);

		if(session == null) {
			response.sendRedirect("login.html");
			return;
		}

		String email = (String) session.getAttribute("email");

		if(email == null) {
			response.sendRedirect("login.html");
			return;
		}

		// SERVICE
		DoctorService service = new DoctorServiceImpl();

		DoctorModel doctor = service.getDoctorByEmail(email);

		if(doctor == null) {
			out.println("<h2>Doctor Not Found</h2>");
			return;
		}

		// HTML START
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");

		out.println("<head>");

		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

		out.println("<title>Doctor Profile</title>");

		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

		// CSS
		out.println("<style>");

		out.println("body{ margin:0; font-family:Segoe UI,sans-serif; background:#f4f6f9; }");

		// BLUE SIDEBAR
		out.println(".sidebar{ position:fixed; left:0; top:0; width:250px; height:100vh; background:#0d6efd; padding-top:20px; }");

		out.println(".sidebar h3{ color:white; text-align:center; margin-bottom:30px; font-weight:bold; }");

		out.println(".sidebar a{ display:block; color:white; padding:14px 20px; text-decoration:none; transition:0.3s; font-size:16px; }");

		// BLUE HOVER
		out.println(".sidebar a:hover{ background:#0b5ed7; padding-left:25px; }");

		out.println(".main-content{ margin-left:250px; padding:30px; }");

		out.println(".topbar{ background:white; padding:15px 25px; border-radius:12px; box-shadow:0px 2px 10px rgba(0,0,0,0.1); margin-bottom:30px; display:flex; justify-content:space-between; align-items:center; }");

		out.println("</style>");

		out.println("</head>");

		out.println("<body>");

		// SIDEBAR
		out.println("<div class='sidebar'>");

		out.println("<h3>Doctor Panel</h3>");

		out.println("<a href='doctordashboard'>Dashboard</a>");

		out.println("<a href='viewAppointmentsDoctor'>My Appointments</a>");

		out.println("<a href='viewPatients'>View Patients</a>");

		out.println("<a href='doctorprofile'>Doctor Profile</a>");

		out.println("<a href='changePasswordDoctor'>Change Password</a>");

		out.println("<a href='logout'>Logout</a>");

		out.println("</div>");

		// MAIN CONTENT
		out.println("<div class='main-content'>");

		// TOPBAR
		out.println("<div class='topbar'>");

		out.println("<div>");
		out.println("<h3>Welcome Doctor</h3>");
		out.println("<p class='mb-0'>Hospital Management System</p>");
		out.println("</div>");

		out.println("<div>");
		out.println("<h5 class='text-primary'>" + email + "</h5>");
		out.println("</div>");

		out.println("</div>");

		// PROFILE CARD
		out.println("<div class='card shadow-lg border-0 rounded-4'>");

		out.println("<div class='card-body p-4'>");

		// BLUE HEADING
		out.println("<h2 class='text-primary mb-4 fw-bold'>Doctor Profile</h2>");

		// FORM
		out.println("<form action='updatedoctor' method='post'>");

		out.println("<table class='table table-bordered table-hover'>");

		// BLUE TABLE HEADER
		out.println("<tr class='table-primary'>");
		out.println("<th width='30%'>Field</th>");
		out.println("<th>Details</th>");
		out.println("</tr>");

		// NAME
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<td><input type='text' name='name' class='form-control' value='"+doctor.getName()+"'></td>");
		out.println("</tr>");

		// SPECIALIZATION
		out.println("<tr>");
		out.println("<th>Specialization</th>");
		out.println("<td><input type='text' name='specialization' class='form-control' value='"+doctor.getSpecialization()+"'></td>");
		out.println("</tr>");

		// EXPERIENCE
		out.println("<tr>");
		out.println("<th>Experience</th>");
		out.println("<td><input type='number' name='experience' class='form-control' value='"+doctor.getExperience()+"'></td>");
		out.println("</tr>");

		// MOBILE
		out.println("<tr>");
		out.println("<th>Mobile</th>");
		out.println("<td><input type='text' name='mobile' class='form-control' value='"+doctor.getMobile()+"'></td>");
		out.println("</tr>");

		// EMAIL
		out.println("<tr>");
		out.println("<th>Email</th>");
		out.println("<td><input type='email' readonly name='email' class='form-control' value='"+doctor.getEmail()+"'></td>");
		out.println("</tr>");

		out.println("</table>");

		// BLUE BUTTON
		out.println("<button type='submit' class='btn btn-primary'>Update Profile</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");

		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		doGet(request, response);
	}
}