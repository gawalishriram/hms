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

import com.model.PatientModel;
import com.service.RegisterPatient;
import com.service.RegisterPatientImpl;

@WebServlet("/profilepatient")
public class PatientProfileController extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

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

		RegisterPatient service = new RegisterPatientImpl();

		PatientModel patient = service.getPatientByEmail(email);

		if(patient == null) {
			out.println("<h2>Patient Not Found</h2>");
			return;
		}

		request.setAttribute("includePage", "yes");

		RequestDispatcher rd = request.getRequestDispatcher("patientdashboard");
		rd.include(request, response);

		out.println("<div class='container-fluid'>");
		out.println("<div class='card shadow-lg border-0 rounded-4'>");
		out.println("<div class='card-body p-4'>");

		out.println("<h2 class='text-success mb-4 fw-bold'>Patient Profile</h2>");
		out.println("<form action='updatepatient' method='post'>");
		out.println("<table class='table table-bordered table-hover'>");

		out.println("<tr class='table-success'>");
		out.println("<th width='30%'>Field</th>");
		out.println("<th>Details</th>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<td><input type='text' name='name' class='form-control' value='"+patient.getName()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Age</th>");
		out.println("<td><input type='number' name='age' class='form-control' value='"+patient.getAge()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Gender</th>");
		out.println("<td><input type='text' name='gender' class='form-control' value='"+patient.getGender()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Mobile</th>");
		out.println("<td><input type='text' name='mobile' class='form-control' value='"+patient.getMobile()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Email</th>");
		out.println("<td><input type='email' readonly name='email' class='form-control' value='"+patient.getEmail()+"'></td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<th>Disease</th>");
		out.println("<td><input type='text' name='disease' class='form-control' value='"+patient.getDisease()+"'></td>");
		out.println("</tr>");

		out.println("</table>");

		
		out.println("<button type='submit' class='btn btn-success'>Update Profile</button>");

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