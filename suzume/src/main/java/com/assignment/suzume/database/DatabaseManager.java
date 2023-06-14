package com.assignment.suzume.database;

import java.sql.*;
import org.sqlite.SQLiteException;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.utils.PasswordEncoder;
import com.sarojaba.prettytable4j.PrettyTable;

public class DatabaseManager {
    private static DatabaseManager instance; // singleton
    private Connection connection;

    private DatabaseManager() {
        this.connection = DatabaseConnector.getConnection();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean checkIfUserExists(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ?")) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Failed to check user. Error: " + e.getMessage());
            return false;
        }
    }

    public boolean checkIfPasswordMatch(String username, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT password FROM users WHERE name = ?")) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    return storedPassword.equals(PasswordEncoder.hashPassword(password));
                } else {
                    System.out.println("User not found.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to check password. Error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateUserGameStatus(User user) {
        String updateQuery = "UPDATE users SET win = ?, lose = ?, draw = ?, score = ? WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, user.getWin());
            preparedStatement.setInt(2, user.getLose());
            preparedStatement.setInt(3, user.getDraw());
            preparedStatement.setInt(4, user.getScore());
            preparedStatement.setString(5, user.getName());
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected > 0 ? "Game status updated successfully." : "User not found.");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to create user. Duplicate name.");
            return false;
        }
    }

    public boolean createNewUser(String username, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (name, password, win, lose, draw, score) VALUES (?, ?, 0, 0, 0, 0)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PasswordEncoder.hashPassword(password));
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected > 0 ? "User created successfully." : "Failed to create user.");
            return rowsAffected > 0;
        } catch (SQLiteException e) {
            System.out.println("Failed to create user. Duplicate name.");
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateUserPassword(String username, String newPassword) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE name = ?")) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected > 0 ? "Password updated successfully." : "User not found.");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE name = ?")) {

            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected > 0 ? "User removed successfully." : "User not found.");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadUser(String username) {
        String selectQuery = "SELECT name, win, lose, draw, score FROM users WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Load user successfully!");
                User user = User.getInstance();
                user.initializeProfile(
                    resultSet.getString("name"),
                    resultSet.getInt("win"),
                    resultSet.getInt("lose"),
                    resultSet.getInt("draw"),
                    resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDatabase() {
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT name, win, lose, draw, score FROM users";
            ResultSet rs = statement.executeQuery(sql);

            // Print the table headers
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String[] headers = new String[numColumns];
            for (int i = 1; i <= numColumns; i++) {
                headers[i - 1] = rsmd.getColumnName(i);
            }

            // Create the table object
            PrettyTable table = PrettyTable.fieldNames(headers);

            // Add rows to the table
            while (rs.next()) {
                String[] row = new String[numColumns];
                for (int i = 1; i <= numColumns; i++) {
                    row[i - 1] = rs.getString(i);
                }
                table.addRow((Object[]) row);
            }

            // Print the table
            table.sortTable("score", true);
            System.out.println(table.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
