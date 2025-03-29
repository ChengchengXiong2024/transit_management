

package com.algonquin.assignment2.transferobjects;

/**
 * File name: Author.java
 * Course: CST8288 – OOP with Design Pattern
 * Assignment: Assignment 2
 * Date: 2025-02-12
 * Professor: Sarah Khan
 * Lab Section: CST8288_031
 * Author: Chengcheng Xiong
 * @version 1.0
 * @see com.algonquin.assignment2.transferobjects
 * @since compiler version 21
 *
 * The Author class represents an author entity with fields corresponding to
 * the Authors table in the database. It provides standard getter/setter methods,
 * constructors, and a formatted toString for display.
 */
public class Author {

    /**
     * Unique identifier for the author.
     */
    private int authorID;

    /**
     * Author's first name.
     */
    private String firstName;

    /**
     * Author's last name.
     */
    private String lastName;

    /**
     * Default no-arg constructor.
     */
    public Author() {}

    /**
     * Constructs an Author with full parameters.
     * @param authorID ID of the author
     * @param firstName Author's first name
     * @param lastName Author's last name
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the Author ID.
     * @return author ID
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Sets the Author ID.
     * @param authorID the new ID to set
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Gets the author's first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the author's first name.
     * @param firstName the new first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the author's last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the author's last name.
     * @param lastName the new last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a formatted string representing the Author.
     * @return string in the format: Author [ID=..., FirstName=..., LastName=...]
     */
    @Override
    public String toString() {
        return String.format("Author [ID=%d, FirstName=%s, LastName=%s]",
                authorID, firstName, lastName);
    }
}
