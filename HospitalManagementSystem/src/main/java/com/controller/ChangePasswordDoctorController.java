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

import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/changePasswordDoctor")
public class ChangePasswordDoctorController extends HttpServlet {

	protected void doGet(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out =
		response.getWriter();

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

		request.setAttribute(
		"includePage",
		"yes");

		RequestDispatcher r =
		request.getRequestDispatcher(
		"doctordashboard");

		r.include(request, response);

		out.println(
		"<div class='container-fluid' style='margin-left:300px; width:75%; padding-top:20px;'>");

		out.println(
		"<div class='card shadow-lg border-0 rounded-4'>");

		out.println(
		"<div class='card-body p-4'>");

		out.println(
		"<h2 class='text-primary mb-4 fw-bold'>Change Password</h2>");

		out.println(
		"<form action='changePasswordDoctor' method='post'>");

		out.println(
		"<table class='table table-bordered table-hover'>");

		out.println(
		"<tr class='table-primary'>");

		out.println(
		"<th width='30%'>Field</th>");

		out.println(
		"<th>Details</th>");

		out.println("</tr>");

		out.println("<tr>");

		out.println("<th>Email</th>");

		out.println("<td>");

		out.println(
		"<input type='email' readonly name='email' class='form-control' value='"+email+"'>");

		out.println("</td>");

		out.println("</tr>");

		out.println("<tr>");

		out.println("<th>Old Password</th>");

		out.println("<td>");

		out.println(
		"<input type='password' name='oldpassword' class='form-control' required>");

		out.println("</td>");

		out.println("</tr>");

		out.println("<tr>");

		out.println("<th>New Password</th>");

		out.println("<td>");

		out.println(
		"<input type='password' name='newpassword' class='form-control' required>");

		out.println("</td>");

		out.println("</tr>");

		out.println("</table>");

		out.println(
		"<button type='submit' class='btn btn-primary'>Change Password</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</div>");

		out.println("</div>");
	}

	protected void doPost(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		String email =
		request.getParameter("email");

		String oldPassword =
		request.getParameter("oldpassword");

		String newPassword =
		request.getParameter("newpassword");

		DoctorService service =
		new DoctorServiceImpl();

		boolean b =
		service.changeDoctorPassword(
		email,
		oldPassword,
		newPassword);

		if(b)
		{
			response.sendRedirect(
			"doctordashboard");
		}
		else
		{
			PrintWriter out =
			response.getWriter();

			out.println(
			"<h3 style='color:red; margin-left:320px;'>Wrong Old Password</h3>");

			doGet(request,response);
		}
	}
}