package servlet;


import util.MyFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String query = "Select id from users where username=? and password=?";
        ResultSet resultSet = null;
        int id = -1;
        try(Connection con = MyFunctions.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Id is "+id);
        }
        if (id!=-1){
            System.out.println("User with id " + id + " has logged");
            Cookie cookie = new Cookie("user",username);
            cookie.setMaxAge(60*10);
            response.addCookie(cookie);
            Cookie cookie1 = new Cookie("lastTime", DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()));
            response.addCookie(cookie1);
            cookie.setMaxAge(60*10);
            request.getSession().setAttribute(String.valueOf(id),1);
            response.sendRedirect(request.getContextPath()+"/main");
        } else {
            request.setAttribute("error", 1);
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    // localhost/login GET method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = getServletContext().getInitParameter("message");
        request.setAttribute("message",message);
        request.getRequestDispatcher("login.jsp").forward(request,response);

    }
}
