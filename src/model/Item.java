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

    public Item(int itemId, String itemName, int itemPercentage) {
        this.itemId = itemId;
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
            s.execSqlNoReturn("INSERT INTO evaluation_items(name, percentage) VALUES(" + this.itemName + "," + this.itemPercentage + ");");
            // Get id.
            ResultSet rs = s.execSqlWithReturn("SELECT LAST_INSERT_ROWID()");
            while (rs.next()) {
                id = rs.getInt(0);
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
