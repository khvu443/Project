/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Context.DBContext;
import entity.Reservation;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class ReservationDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean addFoodType(Reservation re) throws SQLException {
        int rs = 0;
        try {
            String sql = "INSERT INTO reservation values(?,?,?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, re.getReservation_id());
            ps.setInt(2, re.getTable_id());
            ps.setInt(3, re.getCustomer_id());
            ps.setDate(4, (java.sql.Date) re.getDateReservation());
            ps.setInt(5, re.getNoPeople());

            rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            ps.close();
            conn.close();
        }
        return false;
    }

    public ArrayList<Reservation> getAllReservation() {
        String sql = "Select * from Reservation";
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(
                        rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getDate(4),
                        rs.getInt(5)
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Reservation> getCusReservation(int id) {
        String sql = "Select * from Reservation where customer_id = ?";
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(
                        rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getDate(4),
                        rs.getInt(5)
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void DeleteReseravation(int id) {
        String query = "delete from reservation where reservation_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
