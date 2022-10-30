/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author LENOVO
 */
public class TablesType {
    private int type_id;
    private String typeName;
    private int capacity;

    public TablesType(int type_id, String typeName, int capacity) {
        this.type_id = type_id;
        this.typeName = typeName;
        this.capacity = capacity;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int table_id) {
        this.type_id = table_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TablesType{" + "type_id=" + type_id + ", typeName=" + typeName + ", capacity=" + capacity + '}';
    }
    
}
