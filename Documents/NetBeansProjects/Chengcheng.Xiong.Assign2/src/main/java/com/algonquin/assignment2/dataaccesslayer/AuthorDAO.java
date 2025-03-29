
package com.algonquin.assignment2.dataaccesslayer;

import com.algonquin.assignment2.transferobjects.Author;
import java.sql.SQLException;
import java.util.List;
/**
 * File name: AuthorDAO.java
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
 * The AuthorDAO interface defines data access operations related to the Authors table.
 * It provides method signatures for querying, inserting, updating, deleting authors,
 * and generating summary data for display.
 */
public interface AuthorDAO {

    /**
     * Retrieves all authors from the database.
     * @return List of Author objects.
     */
    List<Author> getAllAuthors();

    /**
     * Retrieves an Author by their ID.
     * @param id The ID of the author to retrieve.
     * @return Author object if found, otherwise null.
     */
    Author getAuthorById(int id);

    /**
     * Adds a new Author to the database.
     * @param author Author object to add.
     * @return true if the insertion was successful.
     */
    boolean addAuthor(Author author);

    /**
     * Updates an existing Author record in the database.
     * @param author Author object with updated values.
     * @return true if the update was successful.
     */
    boolean updateAuthor(Author author);

    /**
     * Deletes an Author by ID.
     * @param id ID of the author to delete.
     * @return true if the deletion was successful.
     */
    boolean deleteAuthor(int id);

    /**
     * Retrieves a formatted summary of Authors and their Titles.
     * @return List of summary strings.
     */
    List<String> getAuthorTitleSummary(); // 新增方法

    /**
     * Updates only the author's name fields using their ID.
     * @param authorId The ID of the author to update.
     * @param newFirstName The new first name.
     * @param newLastName The new last name.
     * @return true if update was successful.
     * @throws SQLException if a database error occurs.
     */
    boolean updateAuthorById(int authorId, String newFirstName, String newLastName) throws SQLException;

    /**
     * Deletes an author by ID, with support for SQL exception handling.
     * @param authorId The ID of the author to delete.
     * @return true if deletion was successful.
     * @throws SQLException if a foreign key or DB error occurs.
     */
    boolean deleteAuthorById(int authorId) throws SQLException;
}
