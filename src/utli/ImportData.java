package utli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import utli.ReadWriteExcel;
/**
 * Created by 子楹 on 2017/4/23.
 */
public class ImportData {
    private static final long serialVersionUID = 1L ;
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";
    String s_id;
    String name;
    String moble;
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:fyp.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            /*String sql = "INSERT INTO student (s_id, name, mobile) " +
                    "VALUES (43, 'Baggio', 13988888888 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO student (s_id, name, mobile) " +
                    "VALUES (44, 'Bill', 15988888888 );;";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO student (s_id, name, mobile) " +
                    "VALUES (45, 'Terry', 18988888888 );;";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO student (s_id, name, mobile) " +
            "VALUES (46, 'Bruce', 15888888888 );;";
            stmt.executeUpdate(sql);*/

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}

