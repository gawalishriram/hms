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

@WebServlet("/appointmentform")
public class AppointmentForm extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		
		String id = request.getParameter("id");
		String dname = request.getParameter("name");
		
		HttpSession session =
		request.getSession(false);

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

		
		RegisterPatient service = new RegisterPatientImpl();

		PatientModel patient =service.getPatientByEmail(email);

		
		String patientName = patient.getName();

		request.setAttribute("includePage", "yes");

		RequestDispatcher r =request.getRequestDispatcher("patientdashboard");

		r.include(request, response);

		// CUSTOM STYLE
		out.println("<style>");

		out.println(".appointment-card{");
		out.println("background:white;");
		out.println("border-radius:25px;");
		out.println("padding:40px;");
		out.println("box-shadow:0px 5px 25px rgba(0,0,0,0.1);");
		out.println("}");

		out.println(".appointment-title{");
		out.println("font-size:38px;");
		out.println("font-weight:bold;");
		out.println("color:#198754;");
		out.println("margin-bottom:10px;");
		out.println("}");

		out.println(".appointment-subtitle{");
		out.println("color:gray;");
		out.println("margin-bottom:30px;");
		out.println("}");

		out.println(".form-label{");
		out.println("font-weight:600;");
		out.println("margin-bottom:8px;");
		out.println("}");

		out.println(".form-control{");
		out.println("height:50px;");
		out.println("border-radius:12px;");
		out.println("border:1px solid #ced4da;");
		out.println("}");

		out.println(".form-control:focus{");
		out.println("box-shadow:none;");
		out.println("border-color:#198754;");
		out.println("}");

		out.println(".book-btn{");
		out.println("background:#198754;");
		out.println("border:none;");
		out.println("height:52px;");
		out.println("font-size:18px;");
		out.println("font-weight:bold;");
		out.println("border-radius:14px;");
		out.println("transition:0.3s;");
		out.println("}");

		out.println(".book-btn:hover{");
		out.println("background:#146c43;");
		out.println("transform:translateY(-2px);");
		out.println("}");

		out.println(".appointment-icon{");
		out.println("width:90px;");
		out.println("height:90px;");
		out.println("background:#e9f7ef;");
		out.println("border-radius:50%;");
		out.println("display:flex;");
		out.println("align-items:center;");
		out.println("justify-content:center;");
		out.println("margin:auto;");
		out.println("margin-bottom:20px;");
		out.println("font-size:40px;");
		out.println("color:#198754;");
		out.println("}");

		out.println("</style>");

		// MAIN CONTENT
		out.println("<div class='container-fluid'>");

		out.println("<div class='row justify-content-center'>");

		out.println("<div class='col-lg-7 col-md-9'>");

		out.println("<div class='appointment-card'>");

		// ICON
		out.println("<div class='text-center'>");

		out.println("<div class='appointment-icon'>");

		out.println("🩺");

		out.println("</div>");

		out.println("<h1 class='appointment-title'>Book Appointment</h1>");

		out.println("<p class='appointment-subtitle'>");
		out.println("Fill appointment details carefully");
		out.println("</p>");

		out.println("</div>");

		// FORM START
		out.println("<form action='patientappointment' method='post'>");

		out.println("<input type='hidden' name='did' value='"+id+"'>");

		//out.println("<input type='hidden' name='pid' value='"+patient.getId()+"'>");
			//delet above line 
		// PATIENT NAME
		out.println("<div class='mb-4'><label class='form-label'>Patient Name</label><input type='text' value='"+patientName+"' name='patientName' class='form-control bg-light' readonly></div>");

		out.println("<div class='mb-4'><label class='form-label'>Doctor Name</label><input type='text' value='"+dname+"' name='dName' class='form-control bg-light' readonly></div>");

		// DATE
		out.println("<div class='mb-4'><label class='form-label'>Appointment Date</label><input type='date' name='appointmentdate' class='form-control' required></div>");

		// TIME
		out.println("<div class='mb-4'><label class='form-label'>Appointment Time</label><input type='time' name='appointmenttime' class='form-control' required></div>");

		// BUTTON
		out.println("<button type='submit' class='btn book-btn w-100 text-white'>Confirm Appointment</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");

		// CLOSE TAGS
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