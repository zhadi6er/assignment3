package servlet;

import util.MyFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("newusername");
        String password = request.getParameter("newpassword");
        String password2 = request.getParameter("newpassword2");
        int id = -1;
        if (password.equals(password2) && password.length()<12 && username.length()<12){
            try(Connection con = MyFunctions.getConnection()) {
                String query = "Select id from users where username=?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,username);
                if (preparedStatement.execute()){
                    ResultSet rs = preparedStatement.getResultSet();
                    if (rs.next())
                        throw new Exception("this username is already taken");
                }
                preparedStatement.close();

                String query1 = "INSERT INTO users(username, password) VALUES (?, ?)";
                PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                preparedStatement1.setString(1,username);
                preparedStatement1.setString(2,password);
                preparedStatement1.execute();

//                try (ResultSet generatedKeys = preparedStatement1.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        id = generatedKeys.getInt(1);
//                    }
//                    else {
//                        throw new Exception("Creating user failed, no ID obtained.");
//                    }
//                }

                String query2 = "Select id from users where username='"+username+"' and password='"+password+"'";
                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
                preparedStatement2.executeQuery();
//                PreparedStatement preparedStatement2 = con.prepareStatement(query2);
//                preparedStatement2.setString(1,username);
//                preparedStatement2.setString(2,password);
                ResultSet resultSet = preparedStatement2.getResultSet();


                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
                preparedStatement2.close();
                HttpSession session =  request.getSession();
                session.setAttribute("user_id",id);
                System.out.println("User with username " + username + " and password " + password + " has registered");
//                request.getRequestDispatcher("/main").forward(request, response);
                response.sendRedirect(request.getContextPath()+"/main");
            } catch (Exception e){
                if (e.getMessage().equals("this username is already taken")) {
                    request.setAttribute("error", 3);
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error",1);
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
