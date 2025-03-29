

package com.algonquin.assignment2.viewlayer;

import com.algonquin.assignment2.businesslayer.AuthorService;
import com.algonquin.assignment2.transferobjects.Author;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * File name: AuthorListServlet.java
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
 * This servlet displays all authors in an HTML table format.
 * It uses AuthorService to retrieve the data and generates a web page
 * with options to update or delete each author.
 */
public class AuthorListServlet extends HttpServlet {

    /**
     * Author service layer to handle author-related business logic.
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
     * Handles GET requests by retrieving all authors and displaying them
     * in a structured HTML table. Includes action links for update and delete.
     *
     * @param request  HttpServletRequest containing client data
     * @param response HttpServletResponse to send the HTML content
     * @throws ServletException if servlet-related error occurs
     * @throws IOException      if I/O error occurs while writing HTML
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Author> authors = authorService.getAllAuthors();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Authors</title></head><body>");
        out.println("<h2>Author List</h2>");
        out.println("<a href='add-author'>➕ Add New Author</a><br><br>");

        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Actions</th></tr>");
        for (Author author : authors) {
            out.println("<tr>");
            out.println("<td>" + author.getAuthorID() + "</td>");
            out.println("<td>" + author.getFirstName() + "</td>");
            out.println("<td>" + author.getLastName() + "</td>");
            out.println("<td>"
                + "<a href='update-author?id=" + author.getAuthorID() + "'>Update</a> | "
                + "<a href='delete-author?id=" + author.getAuthorID() + "' onclick=\"return confirm('Are you sure?')\">Delete</a>"
                + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<a href='index'><button>Back to main page</button></a>");
        out.println("</body></html>");
    }
}
