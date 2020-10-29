package servlet;

import models.Item;
import models.PriceCompartor;
import util.MyFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Queue<Item> cart = (Queue<Item>) request.getSession().getAttribute("cart");
        //One servlet a lot of functionalities
        switch (Integer.parseInt(request.getParameter("action"))){
            case 1: //ADD
                ResultSet resultSetItems = null;
                String queryItems = "Select * from items where id=?";
                try(Connection con = MyFunctions.getConnection()) {
                    PreparedStatement preparedStatement = con.prepareStatement(queryItems);
                    preparedStatement.setInt(1,Integer.parseInt(request.getParameter("id")));
                    resultSetItems = preparedStatement.executeQuery();
                    while (resultSetItems.next()){
                        Item item = new Item();
                        item.setId(resultSetItems.getInt(1));
                        item.setName(resultSetItems.getString(2));
                        item.setDetails(resultSetItems.getString(3));
                        item.setPrice(resultSetItems.getDouble(4));
                        item.setOwn_id((new Random()).nextInt(10000));
                        cart.add(item);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    System.out.println("Getting items");
                }
                response.sendRedirect(request.getContextPath()+"/main");
                break;
            case 2: //DELETE
                cart.removeIf(item -> item.getId() == Integer.parseInt(request.getParameter("id")));
                response.sendRedirect(request.getContextPath()+"/cart?action=3");
                break;
            case 3: //SHOW
                request.setAttribute("cart",cart);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
            case 4: //ORDER
                List<Item> itemList = new ArrayList<>();
                cart.forEach(item -> itemList.add(item));
                Collections.sort(itemList, new PriceCompartor());
                request.setAttribute("cartSorted",itemList);
                request.setAttribute("cart",cart);
                request.setAttribute("sorted",true);
                request.getRequestDispatcher("cart.jsp").forward(request,response);
                break;
        }
    }
}
