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

		RegisterPatient service =
		new RegisterPatientImpl();

		PatientModel patient =
		service.getPatientByEmail(email);

		if(patient == null)
		{
			out.println("<h2>Patient Not Found</h2>");
			return;
		}

		request.setAttribute("includePage", "yes");

		RequestDispatcher rd =
		request.getRequestDispatcher("patientdashboard");

		rd.include(request, response);

		out.println("<div class='card shadow-lg border-0 rounded-4'>");

		out.println("<div class='card-body p-4'>");

		out.println("<h2 class='text-success fw-bold mb-4'>Patient Profile</h2>");

		out.println("<form action='updatepatient' method='post'>");

		out.println("<div class='row'>");

		// Name
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Name</label>");

		out.println("<input type='text' name='name' class='form-control' value='"+patient.getName()+"'>");

		out.println("</div>");

		// Age
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Age</label>");

		out.println("<input type='number' name='age' class='form-control' value='"+patient.getAge()+"'>");

		out.println("</div>");

		// Gender
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Gender</label>");

		out.println("<input type='text' name='gender' class='form-control' value='"+patient.getGender()+"'>");

		out.println("</div>");

		// Mobile
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Mobile</label>");

		out.println("<input type='text' name='mobile' class='form-control' value='"+patient.getMobile()+"'>");

		out.println("</div>");

		// Email
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Email</label>");

		out.println("<input type='email' readonly name='email' class='form-control bg-light' value='"+patient.getEmail()+"'>");

		out.println("</div>");

		// Disease
		out.println("<div class='col-md-6 mb-3'>");

		out.println("<label class='form-label fw-bold'>Disease</label>");

		out.println("<input type='text' name='disease' class='form-control' value='"+patient.getDisease()+"'>");

		out.println("</div>");

		out.println("</div>");

		out.println("<button type='submit' class='btn btn-success px-4 py-2 rounded-pill'>Update Profile</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");

		// CLOSE TAGS HERE
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