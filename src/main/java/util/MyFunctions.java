package util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MyFunctions {

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/shop";
        String user = "postgres";
        String password = "facebook1409";
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            Logger lgr = Logger.getLogger(MyFunctions.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return con;
    }
}
