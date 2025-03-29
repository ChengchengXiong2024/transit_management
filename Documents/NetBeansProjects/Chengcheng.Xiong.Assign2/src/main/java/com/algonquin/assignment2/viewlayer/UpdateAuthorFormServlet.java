package com.algonquin.assignment2.viewlayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: UpdateAuthorFormServlet.java
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
 * This servlet displays an HTML form to collect an Author's ID along with updated first name and last name.
 * The form submits the updated info to the AuthorUpdateByIdServlet for processing.
 */
public class UpdateAuthorFormServlet extends HttpServlet {

    /**
     * Handles GET requests to display the update author form.
     *
     * @param request  HttpServletRequest from client
     * @param response HttpServletResponse to render HTML content
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs during writing
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Update Author</title></head><body style='text-align:center;'>");
        out.println("<h2>Update Author By ID</h2>");
        out.println("<form action='updateAuthorById' method='post'>");
        out.println("Author ID: <input type='text' name='authorId'/><br/><br/>");
        out.println("New First Name: <input type='text' name='firstName'/><br/><br/>");
        out.println("New Last Name: <input type='text' name='lastName'/><br/><br/>");
        out.println("<input type='submit' value='Update'/>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
