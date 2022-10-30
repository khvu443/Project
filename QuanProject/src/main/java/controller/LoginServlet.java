/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.AccountDAO;
import entity.Account;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameters from jsp
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        System.out.println(userName + " - " + password);

        /// test account
        if (userName.equals("admin") && password.equals("admin")) {
            request.getRequestDispatcher("AdminPage/index.jsp").forward(request, response);
            return;
        }
        ///
        Account a = null;
        try {
            a = new AccountDAO().getAccount(userName, password);
        } catch (SQLException ex) {
            // Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("LoginError", "Login failed");
            request.getRequestDispatcher("HomeServlet").forward(request, response);
        }
        
        if (a != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", a);
            request.getRequestDispatcher("HomeServlet").forward(request, response);
        } else {
            request.setAttribute("LoginError", "Login failed");
            request.getRequestDispatcher("HomeServlet").forward(request, response);
        }

    }

}
