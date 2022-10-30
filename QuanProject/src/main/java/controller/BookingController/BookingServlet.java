/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.BookingController;

import DAO.AccountDAO;
import DAO.ReservationDAO;
import DAO.TableDAO;
import entity.Account;
import entity.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        // Lấy Đối tượng userLogin về = session
        Account acc = (Account) session.getAttribute("userLogin");
        
        // Check account có null ko
        // Nếu ko thì sẽ set tên và sdt của người đặt trên form
        if (acc != null) {
            request.setAttribute("name", acc.getName());
            request.setAttribute("phone", acc.getPhone());
        }
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            ReservationDAO dao = new ReservationDAO();
            AccountDAO aDao = new AccountDAO();
            TableDAO tDao = new TableDAO();

            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("userLogin");
            
            // Check tk ko null
            if (acc != null ) 
            {
                // Lấy thông tin trên form
                String name = request.getParameter("name");
                int noPeople = Integer.parseInt(request.getParameter("people"));
                String tableID = request.getParameter("table");
                
                // Chuyển đổi string date có dạng ở dưới thành date
                // Format phải đúng nếu ko sẽ báo lỗi
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String sdate = request.getParameter("date");
                Date date = sdf.parse(sdate);
                
                // Dùng hàm để check xem date mà mình đặt có lớn hơn hiện tại ko
                if (isGreatThanDateNow(date)) {
                    // Chuyển đổi date của java.util.date sang java.sql.Date
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    System.out.println(sqlDate);

                    Reservation res = new Reservation(
                            autoIncreaseID(dao.getAllReservation()), Integer.parseInt(tableID),
                            aDao.getAccountIDBy(name),
                            sqlDate, noPeople
                    );
                    
                    // Check thử việc thêm vào có bị lỗi ko
                    if (dao.addFoodType(res)) {
                        // Sau khi đăt thành công thì sẽ chuyển đổi trạng thái của bàn rồi về lại trang home
                        tDao.updateTable(Integer.parseInt(tableID), "reserved");
                        response.sendRedirect("HomeServlet");
                    } else {
                        System.out.println("Something wrong");
                    }
                }
                else // Nếu nhỏ hơn thì sẽ báo lỗi và về lại trang
                {
                    request.setAttribute("error", "Date Should Greater than Now!");
                    request.getRequestDispatcher("booking.jsp").forward(request, response);
                }

            } else { //Yêu cầu đăng nhập nếu như bấm đặt
                request.setAttribute("LoginError", "Login First!");
                request.getRequestDispatcher("HomeServlet").forward(request, response);
            }

        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isGreatThanDateNow(Date date) {
        // Lấy thời gian hiện tại
        LocalDate now = LocalDate.now();
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = now.atStartOfDay(systemTimeZone);
        Date utilDate = Date.from(zonedDateTime.toInstant());

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(utilDate);
        cal2.setTime(date);
        //check day của thời gian truyền có lớn hơn hôm nay
        return cal2.get(Calendar.DAY_OF_YEAR) > cal1.get(Calendar.DAY_OF_YEAR)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal2.get(Calendar.HOUR_OF_DAY) > cal1.get(Calendar.HOUR_OF_DAY);

    }
    
  
    public int autoIncreaseID(ArrayList<Reservation> rl)
    {
        int id = 1;
        for(int i = 0; i < rl.size(); i++)
        {
            if(id >= rl.get(i).getReservation_id())
            {
                id+=1;
            }
        }
        return id;
    }

}
