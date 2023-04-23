package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.DBConnection;
import kz.techorda.bitlab.servlet.db.Item;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/index.html")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<Item> items = DBConnection.getItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
