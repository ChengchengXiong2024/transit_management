

package com.algonquin.assignment2.viewlayer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * File name: IndexServlet.java
 * Course: CST8288 – OOP with Design Pattern
 * Assignment: Assignment 2
 * Date: 2025-02-12
 * Professor: Sarah Khan
 * Lab Section: CST8288_031
 * @author Chengcheng Xiong
 * @version 1.0
 * @see com.algonquin.assignment2.viewlayer
 * @since compiler version 21
 *
 * This servlet serves as the entry point (index page) of the web application.
 * It provides a login form (mock login without validation) and buttons to access
 * Author-related operations: view all, search by ID, add, update, and delete.
 */
public class IndexServlet extends HttpServlet {

    /**
     * Handles GET requests to display the index page with login form and navigation buttons.
     * 
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        renderPage(response, null);
    }

    /**
     * Handles POST requests (after user submits login form).
     * Displays a welcome message with the entered username.
     * 
     * @param request the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input/output error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        renderPage(response, username);
    }

    /**
     * Renders the HTML page with login form and action buttons.
     * If a username is provided, a welcome message is shown.
     * 
     * @param response the HttpServletResponse object
     * @param username the username input by user (can be null if not logged in)
     * @throws IOException if an input/output error occurs
     */
    private void renderPage(HttpServletResponse response, String username) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Enter DBMS Credentials</title>");
        out.println("<style>");
        out.println("body { background-color:#fff3e6; text-align:center; font-family: Arial; }");
        out.println(".button-row form { display:inline-block; margin: 5px; }");
        out.println("input, button { font-size: 14px; padding: 6px 10px; }");
        out.println("</style></head><body>");

        out.println("<h1><b>Enter DBMS Credentials</b></h1>");

        // Login form
        out.println("<form method='post'>");
        out.println("<p>Username: <input type='text' name='username'/></p>");
        out.println("<p>Password: <input type='password' name='password'/></p>");
        out.println("<input type='submit' value='Login'/>");
        out.println("</form>");

        // Welcome message
        if (username != null && !username.trim().isEmpty()) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        }

        // Navigation buttons
        out.println("<div class='button-row' style='margin-top:20px;'>");
        out.println("<form action='authors' method='get'><button>GetAllAuthors</button></form>");
        out.println("<form action='searchAuthorForm' method='get'><button>GetAuthorByAuthorId</button></form>");
        out.println("<form action='add-author' method='get'><button>AddAuthor</button></form>");
        out.println("<form action='updateAuthor' method='get'><button>UpdateAuthorById</button></form>");
        out.println("<form action='deleteAuthorForm' method='get'><button>DeleteAuthorById</button></form>");
        out.println("</div>");
        out.println("<h2>Programmed by Chengcheng Xiong 041136085!</h2>");
        out.println("</body></html>");
    }
}
