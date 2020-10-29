package servlet;

import models.Category;
import models.Item;
import models.MyList;
import util.MyFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("cart")==null){
            Queue<Item> cart = new LinkedList<>();
            request.getSession().setAttribute("cart",cart);
        }

        MyList<Item> items = new MyList<>();
        items.setId(0);
        ResultSet resultSetItems = null;
        // if category button is pressed
        if(request.getParameter("category")!=null){
            String queryItems = "Select * from items where category_id=?";

            try(Connection con = MyFunctions.getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement(queryItems);
                preparedStatement.setInt(1,Integer.parseInt(request.getParameter("category")));
                resultSetItems = preparedStatement.executeQuery();
                while (resultSetItems.next()){
                    Item item = new Item();
                    item.setId(resultSetItems.getInt(1));
                    item.setName(resultSetItems.getString(2));
                    item.setDetails(resultSetItems.getString(3));
                    item.setPrice(resultSetItems.getDouble(4));

                    items.add(item);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("Getting items");
            }
        } else {
            String queryItems = "Select * from items";
            try(Connection con = MyFunctions.getConnection()) {
                PreparedStatement preparedStatement = con.prepareStatement(queryItems);
                resultSetItems = preparedStatement.executeQuery();
                while (resultSetItems.next()){
                    Item item = new Item();
                    item.setId(resultSetItems.getInt(1));
                    item.setName(resultSetItems.getString(2));
                    item.setDetails(resultSetItems.getString(3));
                    item.setPrice(resultSetItems.getDouble(4));
                    items.add(item);
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("Getting items");
            }
        }
        System.out.println(items.toString());
        request.setAttribute("items", items);

        ResultSet resultSet = null;
        String query = "Select * from categories";
        LinkedList<Category> categories = new LinkedList<>();
        try(Connection con = MyFunctions.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Getting categories");
        }
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
