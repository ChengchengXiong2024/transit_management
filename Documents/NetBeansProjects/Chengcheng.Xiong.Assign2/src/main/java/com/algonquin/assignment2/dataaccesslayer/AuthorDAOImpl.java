
package com.algonquin.assignment2.dataaccesslayer;

import com.algonquin.assignment2.transferobjects.Author;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * File name: AuthorDAOImpl.java
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
 * This class implements the AuthorDAO interface and provides
 * JDBC-based data access logic for the Authors table, including
 * CRUD operations and summary queries.
 */
public class AuthorDAOImpl implements AuthorDAO {

    /**
     * Database connection object.
     */
    private final Connection conn;

    /**
     * Constructor initializes connection using DataSource singleton.
     */
    public AuthorDAOImpl() {
        this.conn = DataSource.getInstance().getConnection();
    }

    /**
     * Retrieves all authors from the database.
     * @return List of Author objects.
     */
    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM Authors";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("AuthorID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName")
                );
                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    /**
     * Retrieves an author by ID.
     * @param id Author ID.
     * @return Author object or null if not found.
     */
    @Override
    public Author getAuthorById(int id) {
        String sql = "SELECT * FROM Authors WHERE AuthorID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Author(
                            rs.getInt("AuthorID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Inserts a new author into the database.
     * @param author Author object.
     * @return true if insertion successful.
     */
    @Override
    public boolean addAuthor(Author author) {
        String sql = "INSERT INTO Authors (FirstName, LastName) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Updates an existing author's information.
     * @param author Author object with updated values.
     * @return true if update was successful.
     */
    @Override
    public boolean updateAuthor(Author author) {
        String sql = "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setInt(3, author.getAuthorID());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Deletes an author by ID.
     * @param id Author ID.
     * @return true if deletion was successful.
     */
    @Override
    public boolean deleteAuthor(int id) {
        String sql = "DELETE FROM Authors WHERE AuthorID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Retrieves a summary of authors and their titles.
     * @return List of strings combining author names with book titles.
     */
    @Override
    public List<String> getAuthorTitleSummary() {
        List<String> summaries = new ArrayList<>();
        String query = "SELECT a.FirstName, a.LastName, t.Title " +
                       "FROM Authors a " +
                       "JOIN AuthorISBN ai ON a.AuthorID = ai.AuthorID " +
                       "JOIN Titles t ON ai.ISBN = t.ISBN " +
                       "ORDER BY a.AuthorID";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fullName = rs.getString("FirstName") + " " + rs.getString("LastName");
                String bookTitle = rs.getString("Title");
                summaries.add(fullName + " - " + bookTitle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summaries;
    }

    /**
     * Updates an author's name fields using their ID.
     * @param authorId Author ID.
     * @param newFirstName New first name.
     * @param newLastName New last name.
     * @return true if update was successful.
     * @throws SQLException if database error occurs.
     */
    @Override
    public boolean updateAuthorById(int authorId, String newFirstName, String newLastName) throws SQLException {
        String sql = "UPDATE Authors SET FirstName = ?, LastName = ? WHERE AuthorID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setInt(3, authorId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Deletes an author by ID, with SQL exception handling.
     * @param authorId Author ID.
     * @return true if deletion was successful.
     * @throws SQLException if a foreign key or constraint violation occurs.
     */
    @Override
    public boolean deleteAuthorById(int authorId) throws SQLException {
        String sql = "DELETE FROM Authors WHERE AuthorID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, authorId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
