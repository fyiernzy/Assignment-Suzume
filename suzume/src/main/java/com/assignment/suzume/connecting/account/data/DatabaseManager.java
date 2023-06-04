package com.assignment.suzume.connecting.account.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.sqlite.SQLiteException;
import com.assignment.suzume.connecting.account.User;

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
                    return storedPassword.equals(password);
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

    public boolean updateUserGameStatus(String username, int win, int lose, int draw, int score) {
        String updateQuery = "UPDATE users SET win = ?, lose = ?, draw = ?, score = ? WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, win);
            preparedStatement.setInt(2, lose);
            preparedStatement.setInt(3, draw);
            preparedStatement.setInt(4, score);
            preparedStatement.setString(5, username);
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
            preparedStatement.setString(2, password);
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

    public User getUser(String username) {
        String selectQuery = "SELECT name, win, lose, draw, score FROM users WHERE name = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("name"),
                        resultSet.getInt("win"),
                        resultSet.getInt("lose"),
                        resultSet.getInt("draw"),
                        resultSet.getInt("score")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // User not found or an error occurred
    }

    public static void main(String[] args) {
        DatabaseManager database = DatabaseManager.getInstance();
        System.out.println(database.checkIfUserExists("Ng"));
    }

}
