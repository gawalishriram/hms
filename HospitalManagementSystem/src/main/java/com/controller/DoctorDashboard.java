package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/doctordashboard")
public class DoctorDashboard extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.html");
            return;
        }

        String email = (String) session.getAttribute("email");

        if (email == null) {
            response.sendRedirect("login.html");
            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");

        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Doctor Dashboard</title>");

        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");

        out.println("<style>");

        out.println("body{ margin:0; font-family:Segoe UI,sans-serif; background:#f4f6f9; }");

        out.println(".sidebar{ position:fixed; left:0; top:0; width:250px; height:100vh; background:#0d6efd; padding-top:20px; }");

        out.println(".sidebar h3{ color:white; text-align:center; margin-bottom:30px; font-weight:bold; }");

        out.println(".sidebar a{ display:block; color:white; padding:14px 20px; text-decoration:none; transition:0.3s; font-size:16px; }");

        out.println(".sidebar a:hover{ background:#0b5ed7; padding-left:25px; }");

        out.println(".main-content{ margin-left:250px; padding:30px; }");

        out.println(".topbar{ background:white; padding:15px 25px; border-radius:12px; box-shadow:0px 2px 10px rgba(0,0,0,0.1); margin-bottom:30px; display:flex; justify-content:space-between; align-items:center; }");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // SIDEBAR
        out.println("<div class='sidebar'>");

        out.println("<h3>Doctor Panel</h3>");

        String includePage =
        request.getParameter("includePage");

        if(includePage == null)
        {
            out.println("<a href='doctordashboard'>Dashboard</a>");

            out.println("<a href='viewAppointmentsDoctor'>Appointments Request</a>");

            out.println("<a href='ViewPatients'>My Appointments</a>");
        }

        out.println("<a href='doctorprofile'>Profile</a>");

        out.println("<a href='changePasswordDoctor'>Change Password</a>");

        out.println("<a href='logout'>Logout</a>");

        out.println("</div>");

        // MAIN CONTENT
        out.println("<div class='main-content'>");

        // TOPBAR
        out.println("<div class='topbar'>");

        out.println("<div>");
        out.println("<h3>Welcome Doctor</h3>");
        out.println("<p class='mb-0'>Hospital Management System</p>");
        out.println("</div>");

        out.println("<div>");
        out.println("<h5 class='text-primary'>" + email + "</h5>");
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