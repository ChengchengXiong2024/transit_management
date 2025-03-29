

package com.algonquin.assignment2.businesslayer;

import com.algonquin.assignment2.dataaccesslayer.*;
import com.algonquin.assignment2.transferobjects.Author;
import java.sql.SQLException;
import java.util.List;
/**
 * File name: AuthorService.java
 * Course: CST8288 – OOP with Design Pattern
 * Assignment: Assignment 2
 * Date: 2025-02-12
 * Professor: Sarah Khan
 * Lab Section: CST8288_031
 * @author Chengcheng Xiong
 * @version 1.0
 * @see com.algonquin.assignment2.businesslayer
 * @since compiler version 21
 *
 * The AuthorService class belongs to the business layer and acts as a bridge
 * between the presentation layer (Servlets) and the data access layer (DAO).
 * It contains logic for author-related operations such as querying, inserting,
 * updating, and deleting author records, as well as retrieving a summary of authors and titles.
 */
public class AuthorService {

    /**
     * DAO interface for author-related database operations.
     */
    private AuthorDAO authorDAO = new AuthorDAOImpl();

    /**
     * Retrieves a list of all authors from the database.
     * @return A list of Author objects.
     */
    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    /**
     * Retrieves an author by their ID.
     * @param id The ID of the author.
     * @return An Author object if found, otherwise null.
     */
    public Author getAuthorById(int id) {
        return authorDAO.getAuthorById(id);
    }

    /**
     * Inserts a new author into the database.
     * Performs validation on the first name field before inserting.
     * @param author The Author object to be inserted.
     * @throws IllegalArgumentException if the first name is missing or empty.
     */
    public void insertAuthor(Author author) {
        if (author.getFirstName() == null || author.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name required");
        }
        authorDAO.addAuthor(author);
    }

    /**
     * Updates an existing author's details.
     * @param author The Author object containing updated information.
     */
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    /**
     * Deletes an author from the database using their ID.
     * @param id The ID of the author to be deleted.
     */
    public void deleteAuthor(int id) {
        authorDAO.deleteAuthor(id);
    }

    /**
     * Retrieves a combined summary of authors and their titles.
     * @return A list of formatted string summaries.
     */
    public List<String> getAuthorTitleSummary() {
        return authorDAO.getAuthorTitleSummary();
    }

    /**
     * Updates an author's name based on their ID.
     * @param authorId The ID of the author to update.
     * @param newFirstName The new first name.
     * @param newLastName The new last name.
     * @return true if update was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean updateAuthorById(int authorId, String newFirstName, String newLastName) throws SQLException {
        return authorDAO.updateAuthorById(authorId, newFirstName, newLastName);
    }

    /**
     * Deletes an author by ID with exception handling.
     * @param authorId The ID of the author to delete.
     * @return true if the author was successfully deleted.
     * @throws SQLException if a foreign key constraint fails or other DB error occurs.
     */
    public boolean deleteAuthorById(int authorId) throws SQLException {
        return authorDAO.deleteAuthorById(authorId);
    }
}
