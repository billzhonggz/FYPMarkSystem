package model;

import utli.SQLiteAccess;
import utli.SQLiteConnectionInvalidException;
import view.IModelListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ModelLogin {
    private IModelListener view;
    private Connection conn;
    private SQLiteAccess s;

    public ModelLogin() {
        // Load database.
        s = new SQLiteAccess(conn);
    }

    public void setView(IModelListener view) {
        this.view = view;
    }

    public boolean checkPwd(String pwd) {
        try {
            ResultSet rs = s.execSqlWithReturn("SELECT password FROM user WHERE id=1;");
            while (rs.next()) {
                String pwdDB = rs.getString("password");
                // Determine password is correct or not.
                if (pwd.equals(pwdDB)) {
                    this.closeDB();
                    this.notifyAllListeners();
                    return true;
                } else {
                    this.closeDB();
                    this.notifyAllListeners();
                    return false;
                }
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
            this.closeDB();
            this.notifyAllListeners();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            this.closeDB();
            this.notifyAllListeners();
            return false;
        }
        this.closeDB();
        return false;
    }

    public void closeDB() {
        s.closeConnection();
    }

    public void notifyAllListeners() {
        view.notifyModelListener();
    }
}
