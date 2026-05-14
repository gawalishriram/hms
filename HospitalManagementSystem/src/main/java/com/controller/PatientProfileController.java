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

import com.model.DoctorModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/doctorprofile")
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

		DoctorService service = new DoctorServiceImpl();

		DoctorModel doctor = service.getDoctorByEmail(email);

		if(doctor == null) {
			out.println("<h2>Doctor Not Found</h2>");
			return;
		}

		request.setAttribute("includePage", "yes");

		RequestDispatcher rd = request.getRequestDispatcher("doctordashboard");

		rd.include(request, response);

		out.println("<div class='container-fluid'>");

		out.println("<div class='card shadow-lg border-0 rounded-4'>");

		out.println("<div class='card-body p-4'>");

		out.println("<h2 class='text-success mb-4 fw-bold'>Doctor Profile</h2>");

		// FORM START
		out.println("<form action='updatedoctor' method='post'>");

		out.println("<table class='table table-bordered table-hover'>");

		out.println("<tr class='table-success'>");
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

		// UPDATE BUTTON
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