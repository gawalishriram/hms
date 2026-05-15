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

@WebServlet("/patientpassword")
public class PatientPassword extends HttpServlet {

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

		String email = (String) session.getAttribute("email");

		if(email == null)
		{
			response.sendRedirect("login.html");
			return;
		}

		request.setAttribute("includePage", "yes");

		RequestDispatcher r =
		request.getRequestDispatcher("patientdashboard");

		r.include(request, response);

		RegisterPatient patient =
		new RegisterPatientImpl();

		PatientModel model =
		patient.getPatientByEmail(email);

		out.println("<div class='container-fluid'>");

		out.println("<div class='card shadow-lg border-0 rounded-4'>");

		out.println("<div class='card-body p-4'>");

		out.println("<h2 class='text-success mb-4 fw-bold'>Change Password</h2>");

		out.println("<form action='patientchangepassword' method='post'>");

		out.println("<table class='table table-bordered table-hover'>");

		out.println("<tr class='table-success'><th width='30%'>Field</th><th>Details</th></tr>");

		out.println("<tr>");

		out.println("<th>Email</th>");

		out.println("<td><input type='email' readonly name='email' class='form-control' value='"+model.getEmail()+"'></td>");

		out.println("</tr>");


		out.println("<tr>");

		out.println("<th>Password</th>");

		out.println("<td>");

		out.println("<div class='input-group'>");

		out.println("<input type='password' id='password' name='password' class='form-control' value='"+model.getPassword()+"'>");

		out.println("<button type='button' class='btn btn-outline-secondary' onclick='togglePassword()'>Show</button>");

		out.println("</div>");

		out.println("</td>");

		out.println("</tr>");

		out.println("</table>");

		out.println("<button type='submit' class='btn btn-success'>Change Password</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");


		out.println("<script>");

		out.println("function togglePassword(){");

		out.println("var x=document.getElementById('password');");

		out.println("if(x.type==='password'){");

		out.println("x.type='text';");

		out.println("}");

		out.println("else{");

		out.println("x.type='password';");

		out.println("}");

		out.println("}");
 
		out.println("</script>");

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