package utli;


import java.sql.*;

/**
 * Created by ZHONG on 2017/4/22.
 */
public class SQLiteAccess {
    private Connection c;
    private Statement stmt = null;

    public SQLiteAccess() {
        this.connectSQLite();
    }

    public boolean connectSQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:fyp.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("SQLite connected successfully.");
        return true;
    }

    public void execSqlNoReturn(String sql) throws SQLiteConnectionInvalidException {
        // Check connectivity.
        if (c == null)
            throw new SQLiteConnectionInvalidException("SQLite connection invalid.");
        else {
            // Do query.
            try {
                stmt = c.createStatement();
                stmt.execute(sql);
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet execSqlWithReturn(String sql) throws SQLiteConnectionInvalidException {
        // Check connectivity.
        ResultSet rs = null;
        if (c == null)
            throw new SQLiteConnectionInvalidException("SQLite connection invalid.");
        else {
            try {
                stmt = c.createStatement();
                rs = stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    public void execSqlUpdate(String sqlUpdate) throws SQLiteConnectionInvalidException {
        // Check connectivity.
        ResultSet rs = null;
        if (c == null)
            throw new SQLiteConnectionInvalidException("SQLite connection invalid.");
        else {
            try {
                stmt = c.createStatement();
                stmt.executeUpdate(sqlUpdate);
                c.commit();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
