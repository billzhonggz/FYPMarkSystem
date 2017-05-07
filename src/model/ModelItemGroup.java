package model;

import utli.SQLiteAccess;

import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/7.
 */
public class ModelItemGroup {
    private SQLiteAccess s;
    private ArrayList<Item> items;

    public ModelItemGroup(ArrayList<Item> items) {
        this.items = items;
        this.s = new SQLiteAccess();
    }

}
