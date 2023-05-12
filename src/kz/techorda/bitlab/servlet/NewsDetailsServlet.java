package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.Category;
import kz.techorda.bitlab.servlet.db.Comment;
import kz.techorda.bitlab.servlet.db.DBManager;
import kz.techorda.bitlab.servlet.db.News;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/news-details")
public class NewsDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        News news = DBManager.getNewsById(id);
        request.setAttribute("news", news);

        ArrayList<Category> categories = DBManager.getCategories();
        request.setAttribute("cats", categories);

        if (news != null) {
            ArrayList<Comment> comments = DBManager.getComments(news.getId());
            request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("/newsdetails.jsp").forward(request, response);
    }
}
