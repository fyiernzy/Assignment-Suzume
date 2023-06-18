package com.assignment.suzume.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.assignment.suzume.configuration.Configuration;

import java.io.File;

public class DatabaseConnector {
    private static String databaseFilePath = Configuration.getGameFolderURL() + "database.db";
    private static String jdbcURL = "jdbc:sqlite:" + databaseFilePath;
    private static Connection connection;

    private static boolean isDatabaseExists() {
        return new File(databaseFilePath).exists();
    }

    private static void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(jdbcURL);
                    Statement statement = connection.createStatement()) {

                // Create the table
                final String createTableQuery = "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT UNIQUE," +
                        "password TEXT," +
                        "win INTEGER," +
                        "lose INTEGER," +
                        "draw INTEGER," +
                        "score INTEGER" +
                        ")";
                statement.executeUpdate(createTableQuery);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                if(!isDatabaseExists()) {
                    createDatabase();
                }
                connection = DriverManager.getConnection(jdbcURL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
