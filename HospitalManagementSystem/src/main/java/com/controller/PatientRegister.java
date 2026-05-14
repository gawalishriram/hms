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

@WebServlet("/patient")
public class PatientRegister extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        long mobile = Long.parseLong(request.getParameter("mobile"));
        String disease = request.getParameter("disease");
        String password = request.getParameter("password");
        String email=request.getParameter("email");

        PatientModel model = new PatientModel();
        model.setName(name);
        model.setAge(age);
        model.setGender(gender);
        model.setMobile(mobile);
        model.setDisease(disease);
        model.setPassword(password);
        model.setEmail(email);

        RegisterPatient service = new RegisterPatientImpl();
        boolean result = service.addPatient(model);

        if (result) {
        	    HttpSession session = request.getSession();
        	    session.setAttribute("patient", name);
        	    response.sendRedirect("patientDashboard.html");
        	
        } else {
            out.println("<h3>Registration Failed</h3>");
        }


	}

}
