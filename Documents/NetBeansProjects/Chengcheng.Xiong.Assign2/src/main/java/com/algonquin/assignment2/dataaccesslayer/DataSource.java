

package com.algonquin.assignment2.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

/**
 * File name: DataSource.java
 * Course: CST8288 – OOP with Design Pattern
 * Assignment: Assignment 2
 * Date: 2025-02-12
 * Professor: Sarah Khan
 * Lab Section: CST8288_031
 * Author: Chengcheng Xiong
 * @version 1.0
 * @see com.algonquin.assignment2.dataaccesslayer
 * @since compiler version 21
 *
 * The DataSource class is a singleton used to manage the database connection.
 * It reads database configuration from a properties file and ensures only one
 * connection instance is used throughout the application.
 */
public class DataSource {

    /**
     * The singleton instance of DataSource.
     */
    private static DataSource instance;

    /**
     * The active database connection.
     */
    private Connection connection;

    /**
     * JDBC URL used for establishing connection.
     */
    private String url;

    /**
     * Database username.
     */
    private String username;

    /**
     * Database password.
     */
    private String password;

    /**
     * Private constructor: loads database config and initializes connection.
     * It reads from a properties file bundled in the classpath.
     */
    private DataSource() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("❌ Could not find database.properties in classpath.");
            }

            Properties properties = new Properties();
            properties.load(input);

            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            System.out.println("✅ Loaded DB config:");
            System.out.println("  URL: " + url);
            System.out.println("  Username: " + username);

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database connection established.");

        } catch (IOException | SQLException e) {
            System.err.println("❌ Failed to load database config or connect to DB");
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of DataSource.
     * Creates a new instance if one does not already exist.
     *
     * @return the singleton DataSource instance
     */
    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    /**
     * Returns a valid database connection.
     * If the existing connection is closed, it reconnects automatically.
     *
     * @return a valid Connection object
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to reconnect to database");
            e.printStackTrace();
        }
        return connection;
    }
}
