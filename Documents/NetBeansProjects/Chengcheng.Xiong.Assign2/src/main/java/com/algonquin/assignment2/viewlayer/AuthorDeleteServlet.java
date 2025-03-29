

package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * File name: AuthorDeleteServlet.java
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
 * This servlet handles deletion of authors by their ID.
 * It interacts with the AuthorService to perform the deletion logic
 * and redirects back to the author list after successful removal.
 */
public class AuthorDeleteServlet extends HttpServlet {

    /**
     * Author service used for business logic.
     */
    private AuthorService authorService;

    /**
     * Initializes the servlet and instantiates AuthorService.
     * @throws ServletException if initialization fails
     */
    @Override
    public void init() throws ServletException {
        authorService = new AuthorService();
    }

    /**
     * Handles GET requests to delete an author by ID.
     * Expects a query parameter 'id', and redirects to the authors list.
     *
     * Example usage: /deleteAuthor?id=3
     *
     * @param request HttpServletRequest containing the ID
     * @param response HttpServletResponse to redirect after deletion
     * @throws ServletException if servlet fails
     * @throws IOException if redirect fails
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int authorId = Integer.parseInt(request.getParameter("id"));
        authorService.deleteAuthor(authorId);
        response.sendRedirect("authors");
    }
}
