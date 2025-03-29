
package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import com.algonquin.assignment2.transferobjects.Author;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: AuthorUpdateServlet.java
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
 * This servlet is responsible for displaying the update form for a given author
 * and forwarding the update request to AuthorUpdateByIdServlet.
 */

public class AuthorUpdateServlet extends HttpServlet {

    /**
     * Business service that handles logic related to authors.
     */
    private AuthorService authorService;

    /**
     * Initializes the AuthorService instance.
     * @throws ServletException if service initialization fails
     */
    @Override
    public void init() throws ServletException {
        authorService = new AuthorService();
    }

    /**
     * Handles GET requests to show the update form with pre-filled author data.
     * @param request HttpServletRequest containing 'id' parameter
     * @param response HttpServletResponse to return the HTML form
     * @throws ServletException if servlet error occurs
     * @throws IOException if response output fails
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int authorId = Integer.parseInt(request.getParameter("id"));
        Author author = authorService.getAuthorById(authorId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Update Author</title></head><body>");
        out.println("<h2>Update Author</h2>");
        out.println("<form method='post' action='updateAuthorById'>");
        out.println("<input type='hidden' name='authorId' value='" + author.getAuthorID() + "'/>");
        out.println("First Name: <input type='text' name='firstName' value='" + author.getFirstName() + "'/><br>");
        out.println("Last Name: <input type='text' name='lastName' value='" + author.getLastName() + "'/><br>");
        out.println("<input type='submit' value='Update Author'/>");
        out.println("</form>");
        out.println("</body></html>");
    }

    /**
     * Optionally forwards POST to GET for flexibility, though real update is handled in AuthorUpdateByIdServlet.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException if servlet error occurs
     * @throws IOException if redirect or output fails
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

        int authorId = Integer.parseInt(request.getParameter("authorId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        Author updatedAuthor = new Author(authorId, firstName, lastName);
        authorService.updateAuthor(updatedAuthor);

        response.sendRedirect("authors");
    }
}
