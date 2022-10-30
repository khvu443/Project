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
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        AccountDAO dao = new AccountDAO();

        Account b = null;
        if (username == "admin") {
            request.setAttribute("RegError", "Username can't be admin");
            request.getRequestDispatcher("HomeServlet").forward(request, response);
            return;
        }
        try {
            b = dao.checkduplicateUsername(username);
        } catch (SQLException ex) {
            System.out.println("Error");
        }
        if (b != null) {
            request.setAttribute("RegError", "username is already exist");
            request.getRequestDispatcher("HomeServlet").forward(request, response);
            return;
        } else {
            try {
                Account newaccount = new Account(dao.getListAccounts().size() + 1, name, phone, email, username, password, 3);
                dao.createAccount(newaccount);
            } catch (SQLException ex) {

            }
            Account a = null;

            try {
                a = dao.getAccount(username, password);
            } catch (SQLException ex) {
            }
            if (a == null) {
                request.setAttribute("RegError", "register fail");
                request.getRequestDispatcher("HomeServlet").forward(request, response);
            } else {
                request.setAttribute("Registersuccess", "ok");
                request.setAttribute("resusername", username);
                request.setAttribute("respassword", password);
                request.getRequestDispatcher("HomeServlet").forward(request, response);
            }
        }
    }

}
