package kz.techorda.bitlab.servlet.db;

import com.mysql.cj.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/techorda_database",
                    "root",
                    "root"
            );
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from items i order by i.id desc ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setDescription(resultSet.getString("description"));
                items.add(item);
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    public static boolean checkUser(String email, String password){
        boolean user = false;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from users where email=? and password=?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = true;
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static String getFullName(String email){
        String fullName = "";
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select fullName from users where email=?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                fullName = resultSet.getString("fullName");
            }
            statement.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return fullName;
    }
}
