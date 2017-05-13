package model;

import utli.SQLiteAccess;
import utli.SQLiteConnectionInvalidException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ZHONG on 2017/5/7.
 */
public class Item {
    private int itemId;
    private String itemName;
    private int itemPercentage;
    private SQLiteAccess s;

    public Item(String itemName, int itemPercentage) {
        this.itemName = itemName;
        this.itemPercentage = itemPercentage;
        this.s = new SQLiteAccess();
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPercentage() {
        return itemPercentage;
    }

    public int addItemToDbReturnId() {
        int id = 0;
        try {
            // Add this item to db.
            String sql = "INSERT INTO evaluation_items(name, percentage) VALUES('" + this.itemName + "'," + this.itemPercentage + ");";
            s.execSqlNoReturn(sql);
            // Get id.
            ResultSet rs = s.execSqlWithReturn("SELECT MAX(id) FROM evaluation_items;");
            while (rs.next()) {
                id = rs.getInt("MAX(id)");
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        s.closeConnection();
        return id;
    }
}
