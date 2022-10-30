/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Tables {
    private int table_id;
    private int type_id;
    private String status;

    public Tables() {
    }

    public Tables(int table_id, int type_id, String status) {
        this.table_id = table_id;
        this.type_id = type_id;
        this.status = status;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tables{" + "table_id=" + table_id + ", type_id=" + type_id + ", status=" + status + '}';
    }
    
    
}
