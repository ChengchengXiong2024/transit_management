

package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import com.algonquin.assignment2.transferobjects.Author;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: AuthorInsertServlet.java
 * Course: CST8288 – OOP with Design Pattern
 * Assignment: Assignment 2
 * Date: 2025-02-12
 * Professor: Sarah Khan
 * Lab Section: CST8288_031
 * Author: Chengcheng Xiong
 * @version 1.0
 * @see com.algonquin.assignment2.viewlayer
 * @since compiler version 21
 *
 * This servlet is responsible for inserting a new author.
 * It provides a form (via GET) and processes the form input (via POST)
 * by calling the AuthorService to persist the new author.
 */
public class AuthorInsertServlet extends HttpServlet {

    /**
     * AuthorService instance used to insert the author.
     */
    private AuthorService authorService;

    /**
     * Initializes the AuthorService instance.
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        authorService = new AuthorService();
    }

    /**
     * Displays an HTML form for entering a new author's first and last name.
     * @param request HttpServletRequest
     * @param response HttpServletResponse containing the form
     * @throws ServletException if an error occurs during display
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Add Author</title></head><body>");
        out.println("<h2>Add New Author</h2>");
        out.println("<form method='post' action='add-author'>");
        out.println("First Name: <input type='text' name='firstName'><br>");
        out.println("Last Name: <input type='text' name='lastName'><br>");
        out.println("<input type='submit' value='Add Author'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    /**
     * Handles form submission and inserts a new author using the submitted data.
     * Redirects back to the authors list after successful insertion.
     *
     * @param request HttpServletRequest containing form parameters
     * @param response HttpServletResponse for redirection
     * @throws ServletException if servlet error occurs
     * @throws IOException if redirection fails
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Author newAuthor = new Author(0, firstName, lastName);
        authorService.insertAuthor(newAuthor);

        response.sendRedirect("authors");
    }
}
