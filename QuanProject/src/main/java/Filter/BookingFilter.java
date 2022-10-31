/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import entity.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author lenovo
 */
public class BookingFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String url = request.getRequestURI();

        HttpSession session = request.getSession();
        Account acc = null;

        // kiem url la bat dau co phai la 1 trang co Admin khong
        if (url.startsWith("/QuanProject/Booking") || url.startsWith("/QuanProject/booking")) {

            // Kiem tra account co la dang nhap khong neu ko thi cho ve login
            if ((acc = (Account) session.getAttribute("userLogin")) != null) {
                fc.doFilter(req, res);
            } else {
                request.setAttribute("LoginError", "Login First");
                System.out.println("NOT PERMISSION");
                request.getRequestDispatcher("HomeServlet").forward(req, res);
            }
        } else {
            // neu ko phai la trang admin thi se cho phep vao
            fc.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }

}
