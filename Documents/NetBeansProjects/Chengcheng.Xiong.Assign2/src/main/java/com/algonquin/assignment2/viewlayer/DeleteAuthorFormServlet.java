package com.algonquin.assignment2.viewlayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: DeleteAuthorFormServlet.java
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
 * This servlet displays an HTML form to accept an Author ID from the user
 * and submit it for deletion. The form submits to the 'confirmDeleteAuthor' servlet.
 */
public class DeleteAuthorFormServlet extends HttpServlet {

    /**
     * Handles GET requests by displaying the delete-author form.
     *
     * @param request  HttpServletRequest containing client data
     * @param response HttpServletResponse used to render HTML form
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs while writing response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Delete Author</title></head><body style='text-align:center;'>");
        out.println("<h2>Delete Author By ID</h2>");
        out.println("<form action='confirmDeleteAuthor' method='post'>");
        out.println("Author ID: <input type='text' name='authorId'/><br/><br/>");
        out.println("<input type='submit' value='Delete'/>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
