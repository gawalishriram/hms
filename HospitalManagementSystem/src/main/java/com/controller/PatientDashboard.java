package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/patientdashboard")
public class PatientDashboard extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		HttpSession session =request.getSession(false);

		if(session == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		String email =(String)session.getAttribute("email");

		if(email == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		String includePage = (String)request.getAttribute("includePage");

		out.println("<!DOCTYPE html>");

		out.println("<html lang='en'>");

		out.println("<head>");

		out.println("<meta charset='UTF-8'>");

		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

		out.println("<title>Patient Dashboard</title>");

		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

		out.println("<style>");


		out.println("body{");
		out.println("margin:0;");
		out.println("font-family:Segoe UI,sans-serif;");
		out.println("background:#f4f6f9;");
		out.println("}");


		out.println(".sidebar{");
		out.println("position:fixed;");
		out.println("left:0;");
		out.println("top:0;");
		out.println("width:250px;");
		out.println("height:100vh;");
		out.println("background:#198754;");
		out.println("padding-top:20px;");
		out.println("}");

		out.println(".sidebar h3{");
		out.println("color:white;");
		out.println("text-align:center;");
		out.println("margin-bottom:30px;");
		out.println("font-weight:bold;");
		out.println("}");

		out.println(".sidebar a{");
		out.println("display:block;");
		out.println("color:white;");
		out.println("padding:14px 20px;");
		out.println("text-decoration:none;");
		out.println("transition:0.3s;");
		out.println("font-size:16px;");
		out.println("}");

		out.println(".sidebar a:hover{");
		out.println("background:#146c43;");
		out.println("padding-left:25px;");
		out.println("}");


		out.println(".main-content{");
		out.println("margin-left:250px;");
		out.println("padding:30px;");
		out.println("}");

		

		out.println(".topbar{");
		out.println("background:white;");
		out.println("padding:15px 25px;");
		out.println("border-radius:12px;");
		out.println("box-shadow:0px 2px 10px rgba(0,0,0,0.1);");
		out.println("margin-bottom:30px;");
		out.println("display:flex;");
		out.println("justify-content:space-between;");
		out.println("align-items:center;");
		out.println("}");

		out.println("</style>");

		out.println("</head>");

		out.println("<body>");

		
		out.println("<div class='sidebar'>");

		out.println("<h3>Patient Panel</h3>");

		out.println("<a href='patientdashboard'>Dashboard</a>");

		out.println("<a href='viewDoctors'>View Doctors</a>");

		out.println("<a href='bookAppointment'>Book Appointment</a>");

		out.println("<a href='viewAppointments'>My Appointments</a>");

		out.println("<a href='profilepatient'>Patient Profile</a>");

		out.println("<a href='changePassword'>Change Password</a>");

		out.println("<a href='logout'>Logout</a>");

		out.println("</div>");

		
		out.println("<div class='main-content'>");

		

		out.println("<div class='topbar'>");

		out.println("<div>");
		out.println("<h3>Welcome Patient</h3>");
		out.println("<p class='mb-0'>Hospital Management System</p>");
		out.println("</div>");

		out.println("<div>");
		out.println("<h5 class='text-success'>");
		out.println(email);
		out.println("</h5>");
		out.println("</div>");

		out.println("</div>");

		if(includePage == null)
		{
			out.println("<div class='card shadow p-5'>");

			out.println("<h2 class='text-success'>Patient Dashboard</h2>");

			out.println("<p class='mt-3'>Welcome to Hospital Management System</p>");

			out.println("</div>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		doGet(request, response);
	}
}