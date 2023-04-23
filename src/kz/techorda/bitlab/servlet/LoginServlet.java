package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.DBConnection;

import java.io.IOException;

@WebServlet(value = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email_entered");
        String password = request.getParameter("pwd_entered");
        if (DBConnection.checkUser(email,password)){
            String fullName = DBConnection.getFullName(email);
            request.setAttribute("fullName", fullName);
            request.getRequestDispatcher("/userpage.jsp").forward(request,response);
        } else
            response.sendRedirect("/login.jsp?wronguser");
    }
}
