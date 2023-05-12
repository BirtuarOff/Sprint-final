package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.DBManager;
import kz.techorda.bitlab.servlet.db.News;

import java.io.IOException;
import java.util.ArrayList;

public class NewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<News> news = DBManager.getNews();

        request.setAttribute("news", news);
        request.getRequestDispatcher("/news.jsp").forward(request,response);
    }
}
