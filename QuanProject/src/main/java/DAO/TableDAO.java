package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Context.DBContext;
import entity.*;

public class TableDAO {

    public ArrayList<Tables> getTables() {
        ArrayList<Tables> tables = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM tables";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int table_id = rs.getInt(1);
                String status = rs.getString(3);
                int type_id = rs.getInt(2);

                tables.add(new Tables(table_id, type_id, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    public void createTable(int table_id, int type_id, String status) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO tables VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, table_id);
            ps.setString(3, status);
            ps.setInt(2, type_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTable(int table_id, String status) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE tables SET status = ? WHERE table_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, table_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tables> getTablesBy(String type_ID) {
        String query = "  select * from tables where type_id = ?;";
        ArrayList<Tables> list = new ArrayList<Tables>();
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, type_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Tables(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

}
