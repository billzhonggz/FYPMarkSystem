package model;

import utli.SQLiteAccess;
import utli.SQLiteConnectionInvalidException;
import view.AddItemUI;
import view.ErrInfo;
import view.IModelListener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/7.
 */
public class ModelItemGroup {
    private int groupId;
    private SQLiteAccess s;
    private ArrayList<Item> items;
    private IModelListener listener;

    public ModelItemGroup() {
        this.items = new ArrayList<Item>();
        this.s = new SQLiteAccess();
    }

    public void setView(IModelListener listener) {
        this.listener = listener;
    }

    public void addItemToList(Item item) {
        if (items.size() >= 5) {
            ErrInfo dialog = new ErrInfo("This evaluation group is already full.");
            dialog.pack();
            dialog.setVisible(true);
        } else
            items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getGroupId() {
        return groupId;
    }

    public void addItemsToDb() {
        // First: Add items to database.
        // Get return id.
        int[] ids = new int[items.size()];
        // Do insert.
        for (int i = 0; i < items.size(); i++) {
            ids[i] = items.get(i).addItemToDbReturnId();
        }
        // Second: Insert item id to group, return group id.
        // Initialize a String array with empty value.
        String[] strIds = new String[5];
        for (int i = 0; i < 5; i++)
            strIds[i] = "";
        if (ids.length > 0 && ids.length < 5) {
            for (int i = 0; i < ids.length; i++) {
                try {
                    strIds[i] = Integer.toString(ids[i]);
                } catch (Exception e) {
                    break;
                }
            }
        }
        try {
            s.execSqlNoReturn("INSERT INTO evaluation_groups(item1_id,item2_id,item3_id,item4_id,item5_id) VALUES(" +
                    strIds[0] + "," + strIds[1] + "," + strIds[2] + "," + strIds[3] + "," + strIds[4] + "," + strIds[5] + ");");
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        }
        // Return group id.
        try {
            ResultSet rs = s.execSqlWithReturn("SELECT LAST_INSERT_ROWID()");
            while (rs.next()) {
                groupId = rs.getInt(0);
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
