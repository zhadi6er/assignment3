package servlet;

import kz.aitu.assignment2.models.UrlFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmServlet")
public class ConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("counter", UrlFilter.counter);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lastTime")){
                request.setAttribute("lastTime", cookie.getValue());
            }
            if (cookie.getName().equals("lastVisitedTime")){
                request.setAttribute("lastVisitedTime", cookie.getValue());
            }
        }
        System.out.println("Hello");
        request.getRequestDispatcher("confirm.jsp").forward(request,response);
    }
}
