package utli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZHONG on 2017/4/22.
 */
public class TestSQLiteAccess {
    public static void main(String[] args) {
        Connection c = null;
        SQLiteAccess s = new SQLiteAccess(c);
        s.connectSQLite();
        try {
            //s.execSqlNoReturn("CREATE TABLE user(id INT PRIMARY KEY, password TEXT NOT NULL)");
            //s.execSqlNoReturn("INSERT INTO user(id,password) VALUES(1,'111111')");
            ResultSet rs = s.execSqlWithReturn("SELECT * FROM user");
            System.out.println("Query result:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String pwd = rs.getString("password");
                System.out.println("id: " + id + " pwd: " + pwd);
                System.out.println();
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        s.closeConnection();
    }
}
