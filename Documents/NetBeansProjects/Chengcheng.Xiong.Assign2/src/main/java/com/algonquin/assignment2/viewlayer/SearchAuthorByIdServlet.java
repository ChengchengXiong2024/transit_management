
package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import com.algonquin.assignment2.transferobjects.Author;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: SearchAuthorByIdServlet.java
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
 * This servlet handles searching for an author by ID.
 * It receives the authorId from a POST request and displays author information if found.
 */

public class SearchAuthorByIdServlet extends HttpServlet {

    /**
     * Business logic layer for accessing author information.
     */
    private AuthorService authorService;

    /**
     * Initializes the AuthorService.
     */
    @Override
    public void init() {
        authorService = new AuthorService();
    }

    /**
     * Handles POST requests to search for an author by ID.
     * Outputs the result (author found or not) in an HTML page.
     *
     * @param request  HttpServletRequest containing 'authorId' parameter
     * @param response HttpServletResponse used to return HTML result
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs when writing the response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            Author author = authorService.getAuthorById(authorId);

            out.println("<html><body style='text-align:center;'>");
            if (author != null) {
                out.println("<h3>✅ Author Found:</h3>");
                out.println("<p>ID: " + author.getAuthorID() + "</p>");
                out.println("<p>Name: " + author.getFirstName() + " " + author.getLastName() + "</p>");
            } else {
                out.println("<h3 style='color:red;'>❌ Author not found.</h3>");
            }
            out.println("<br/><a href='index'>Return to Home</a>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            out.println("<h3 style='color:red;'>❌ Invalid Author ID format!</h3>");
        }
    }
}
