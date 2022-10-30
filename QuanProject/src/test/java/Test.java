
import DAO.*;
import entity.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws SQLException {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.getAccountIDBy("Lee AAA16"));
    }
}
