package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * File name: DeleteAuthorByIdServlet.java
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
 * This servlet handles deleting an author by ID.
 * It receives POST requests with an 'authorId' parameter and delegates deletion to AuthorService.
 * It provides HTML feedback to the user about the result.
 */
public class DeleteAuthorByIdServlet extends HttpServlet {

    /**
     * AuthorService instance to handle business logic for deletion.
     */
    private AuthorService authorService;

    /**
     * Initializes the AuthorService object.
     */
    @Override
    public void init() {
        authorService = new AuthorService();
    }

    /**
     * Handles POST request to delete an author by their ID.
     * Shows success or error messages in HTML format.
     *
     * @param request  HttpServletRequest containing the 'authorId' parameter
     * @param response HttpServletResponse to display result
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs while writing response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            boolean deleted = authorService.deleteAuthorById(authorId);

            out.println("<html><body style='text-align:center;'>");
            if (deleted) {
                out.println("<h3 style='color:green;'>✅ Author deleted successfully!</h3>");
            } else {
                out.println("<h3 style='color:red;'>❌ Author not found or already deleted.</h3>");
            }
            out.println("<a href='authors'>Return to Home</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            out.println("<h3 style='color:red;'>❌ Invalid author ID format!</h3>");
        } catch (SQLException e) {
            out.println("<h3 style='color:red;'>❌ Database error: " + e.getMessage() + "</h3>");
        }
    }
}
