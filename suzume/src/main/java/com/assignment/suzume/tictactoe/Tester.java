package com.assignment.suzume.tictactoe;

import com.assignment.suzume.tictactoe.player.Gamer;

import java.sql.*;

/**
 * Tester
 */
public class Tester {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("MySQL JDBC driver loaded ok.");
            // Connect to the local MySQL database
            String url = "jdbc:mysql://127.0.0.1:3306/suzume_database";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);

            // Do something with the database connection
            System.out.println("Connected to the database!");

            String sql = "INSERT INTO gamer (id, username, win, lose, draw, password, score) VALUES (NULL, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            Gamer player = new Gamer("Ng", "password");
            statement.setString(1, player.getName());
            statement.setInt(2, player.getWin());
            statement.setInt(3, player.getLose());
            statement.setInt(4, player.getDraw());
            statement.setString(5, player.getPassword());
            statement.setInt(6, player.getScore());

            statement.executeUpdate();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the MySQL JDBC driver: " + e.getMessage());
        } finally {
            // Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected from database");
                }
            } catch (SQLException e) {
                System.out.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}
