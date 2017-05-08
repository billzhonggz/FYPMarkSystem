package utli;

import model.ModelStudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ModelStudent;
/**
 * Created by 子楹 on 2017/5/8.
 */
public class JDBC {
    public static Connection c = null;
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Workshop_test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    public static int InsertStudent(ArrayList<ModelStudent> student){
        connect();
        int s_id;
        String name = "";
        int mobile;
        String project_name = "";
        for(ModelStudent s : student){
            s_id = s.getS_id();
            name = s.getName();
            mobile = s.getMobile();
            project_name = s.getProject_name();
            String sql = "INSERT INTO Student (s_id, name, mobile, project_name) " + "VALUES (" + "'" + s_id + "', " + "'"
                    + name + "', '" + mobile + "', " + project_name + " );";
            System.out.println(sql);
            try {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);

            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                return -1;
            }
        }
        try {
            //stmt.close();
            c.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
}
