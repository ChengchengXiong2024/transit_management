package com.algonquin.assignment2.viewlayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * File name: SearchAuthorFormServlet.java
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
 * This servlet displays an HTML form for the user to input an author ID.
 * The form submits a POST request to SearchAuthorByIdServlet for searching the author.
 */
public class SearchAuthorFormServlet extends HttpServlet {

    /**
     * Handles GET requests and displays the author search form.
     *
     * @param request  HttpServletRequest object from the client
     * @param response HttpServletResponse object to render the HTML form
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if I/O error occurs while writing response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Search Author</title></head><body style='text-align:center;'>");
        out.println("<h2>Search Author By ID</h2>");
        out.println("<form action='searchAuthorById' method='post'>");
        out.println("Author ID: <input type='text' name='authorId' /><br/><br/>");
        out.println("<input type='submit' value='Search'/>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
