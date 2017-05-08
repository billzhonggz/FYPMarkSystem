package model;

import utli.SQLiteAccess;
import utli.SQLiteConnectionInvalidException;
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
        try {
            String sql = "INSERT INTO evaluation_groups(item1_id,item2_id,item3_id,item4_id,item5_id) VALUES(" +
                    ids[0] + "," + ids[1] + "," + ids[2] + "," + ids[3] + "," + ids[4]  + ");";
            s.execSqlNoReturn(sql);
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        }
        // Return group id.
        try {
            ResultSet rs = s.execSqlWithReturn("SELECT MAX(id) FROM evaluation_groups;");
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
