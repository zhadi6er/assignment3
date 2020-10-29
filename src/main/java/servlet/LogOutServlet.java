package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user") || cookie.getName().equals("lastTime")){
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }
        Cookie cookie1 = new Cookie("lastVisitedTime", DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
        cookie1.setMaxAge(60*10);
        response.addCookie(cookie1);
        response.sendRedirect(request.getContextPath()+"/main");
    }
}
