package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.model.DoctorModel;
import com.service.DoctorService;
import com.service.DoctorServiceImpl;

@WebServlet("/doctorreg")
public class DoctorRegister extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String speci=request.getParameter("specialization");
		int exp=Integer.parseInt(request.getParameter("experience"));
		long mobile = Long.parseLong(request.getParameter("mobile"));
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		DoctorService doctorService=new DoctorServiceImpl();
		DoctorModel model=new DoctorModel();
		model.setName(name);
		model.setSpecialization(speci);
		model.setExperience(exp);
		model.setMobile(mobile);
		model.setEmail(email);
		model.setPassword(password);
		boolean result=doctorService.addDoctor(model);
		if(result)
		{
			RequestDispatcher r=request.getRequestDispatcher("doctordashboard");
			r.forward(request, response);
			//out.println("doctor register");
		}
		else
		{
			out.println("doctor not Register");
		}
		
	}

}