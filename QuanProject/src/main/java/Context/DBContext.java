package Context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    private final String servername = "LAPTOP-9FFCD11F";

    private final String dbName = "QuanProject";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String userID = "sa";
    private final String password = "binbo123";

    public Connection getConnection() throws Exception {

        String url = "jdbc:sqlserver://" + servername + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + servername + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);

    }

    public static void main(String[] args) {
        try {
            System.out.println(new DBContext().getConnection());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
