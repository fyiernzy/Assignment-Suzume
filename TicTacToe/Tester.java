package Assignment.clone.TicTacToe;

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
            String url = "jdbc:mysql://127.0.0.1:3306/tictactoe";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);

            // Do something with the database connection
            System.out.println("Connected to the database!");

            String sql = "INSERT INTO player (name, win, lose, draw, password, score) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            Player player = new Player("Ng", "Suzume?");
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
