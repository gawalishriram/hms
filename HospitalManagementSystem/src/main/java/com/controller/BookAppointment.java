package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.model.DoctorModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/bookappointment")
public class BookAppointment extends HttpServlet {

	protected void doGet(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		request.setAttribute("includePage", "yes");

		RequestDispatcher r =request.getRequestDispatcher("patientdashboard");

		r.include(request, response);

		DoctorService service =
		new DoctorServiceImpl();

		List<DoctorModel> model = service.getAllDoctor();

		out.println("<html>");

		out.println("<head>");

		out.println("<title>Book Appointment</title>");

		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");



		// custom css

		out.println("<style>");

		out.println("body{");
		out.println("background:linear-gradient(to right,#e3f2fd,#f8f9fa);");
		out.println("font-family:Arial;");
		out.println("}");

		out.println(".main-title{");
		out.println("font-size:40px;");
		out.println("font-weight:bold;");
		out.println("color:#0d6efd;");
		out.println("margin-bottom:40px;");
		out.println("text-shadow:2px 2px 5px lightgray;");
		out.println("}");

		out.println(".doctor-card{");
		out.println("border:none;");
		out.println("border-radius:20px;");
		out.println("transition:0.4s;");
		out.println("overflow:hidden;");
		out.println("background:white;");
		out.println("}");

		out.println(".doctor-card:hover{");
		out.println("transform:translateY(-10px);");
		out.println("box-shadow:0px 10px 25px rgba(0,0,0,0.2);");
		out.println("}");

		out.println(".doctor-img{");
		out.println("width:100px;");
		out.println("height:100px;");
		out.println("border-radius:50%;");
		out.println("display:block;");
		out.println("margin:auto;");
		out.println("border:4px solid #0d6efd;");
		out.println("}");

		out.println(".doctor-name{");
		out.println("font-size:24px;");
		out.println("font-weight:bold;");
		out.println("color:#0d6efd;");
		out.println("margin-top:15px;");
		out.println("text-align:center;");
		out.println("}");

		out.println(".doctor-info{");
		out.println("font-size:16px;");
		out.println("margin-top:10px;");
		out.println("}");

		out.println(".book-btn{");
		out.println("border-radius:30px;");
		out.println("padding:10px;");
		out.println("font-weight:bold;");
		out.println("transition:0.3s;");
		out.println("}");

		out.println(".book-btn:hover{");
		out.println("background:#198754;");
		out.println("transform:scale(1.05);");
		out.println("}");

		out.println("</style>");

		out.println("</head>");



		out.println("<body>");

		out.println("<div class='container py-5'>");



		out.println("<h1 class='text-center main-title'>");
		out.println("Book Appointment with Doctors");
		out.println("</h1>");



		out.println("<div class='row'>");



		for(DoctorModel m : model)
		{
			out.println("<div class='col-md-4 mb-4'>");



			out.println("<div class='card doctor-card shadow-lg p-4'>");



			

			out.println("<img src='https://cdn-icons-png.flaticon.com/512/3774/3774299.png' class='doctor-img'>");



			// doctor name

			out.println("<h3 class='doctor-name'>");
			out.println("Dr. " + m.getName());
			out.println("</h3>");



			// specialization

			out.println("<p class='doctor-info'>");
			out.println("<b>Specialization :</b> "
			+ m.getSpecialization());
			out.println("</p>");



			// experience

			out.println("<p class='doctor-info'>");
			out.println("<b>Experience :</b> "
			+ m.getExperience()
			+ " Years");
			out.println("</p>");



			// button

			out.println("<a href='appointmentform?id="+ m.getId()+"&name="+m.getName()+"' class='btn btn-success book-btn w-100'>");

			out.println("Book Appointment");

			out.println("</a>");



			out.println("</div>");

			out.println("</div>");
		}



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