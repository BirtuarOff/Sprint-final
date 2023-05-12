package kz.techorda.bitlab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.techorda.bitlab.servlet.db.Category;
import kz.techorda.bitlab.servlet.db.DBManager;
import kz.techorda.bitlab.servlet.db.News;
import kz.techorda.bitlab.servlet.db.User;

import java.io.IOException;

@WebServlet(value = "/save-news")
public class ToUpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        if(user!=null) {

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("id"));
            int category_id = Integer.parseInt(request.getParameter("category"));

            News news = DBManager.getNewsById(id);
            Category c = DBManager.getCategory(category_id);

            if(news!=null) {
                news.setTitle(title);
                news.setContent(content);
                news.setCategory(c);

                DBManager.updateNews(news);

                response.sendRedirect("/news-details?id="+id);

            }else{
                response.sendRedirect("/");
            }

        }else{
            response.sendRedirect("/login");
        }
    }
}
