

package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: AuthorUpdateByIdServlet.java
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
 * This servlet handles HTTP POST requests to update an author's information
 * by their ID. It delegates update logic to AuthorService and provides
 * user feedback on success or failure via an HTML response.
 */
public class AuthorUpdateByIdServlet extends HttpServlet {

    /**
     * Service layer used to perform author update logic.
     */
    private AuthorService authorService;

    /**
     * Initializes the AuthorService instance.
     */
    @Override
    public void init() {
        authorService = new AuthorService();
    }

    /**
     * Processes POST requests for updating an author by ID.
     * Returns a confirmation message or error feedback based on result.
     *
     * @param request  HttpServletRequest containing form input
     * @param response HttpServletResponse used to show result to user
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs when writing response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int authorId = Integer.parseInt(request.getParameter("authorId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            boolean updated = authorService.updateAuthorById(authorId, firstName, lastName);
            out.println("<html><body style='text-align:center;'>");
            if (updated) {
                out.println("<h3 style='color:green;'>✅ Author updated successfully!</h3>");
            } else {
                out.println("<h3 style='color:red;'>❌ Author not found or no changes made.</h3>");
            }
            out.println("<a href='authors'>Return to Main Menu</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body style='text-align:center;'><h3 style='color:red;'>⚠ Error: " + e.getMessage() + "</h3></body></html>");
        }
    }
}
