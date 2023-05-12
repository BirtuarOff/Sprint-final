package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.DBManager;
import kz.techorda.bitlab.servlet.db.User;

import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("re_password");
        String fullName = request.getParameter("full_name");

        User user = DBManager.getUser(email);

        if(user==null){
            if(password.equals(rePassword)){

                User createUser = new User();
                createUser.setEmail(email);
                createUser.setPassword(password);
                createUser.setFullname(fullName);
                createUser.setRole(2);

                DBManager.addUser(createUser);
                response.sendRedirect("/register?success");

            }else{
                response.sendRedirect("/register?passworderror");
            }

        }else{
            response.sendRedirect("/register?emailerror");
        }
    }
}
