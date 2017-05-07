package model;

/**
 * Created by ZHONG on 2017/5/7.
 */
public class Item {
    private int itemId;
    private String itemName;
    private int itemPercentage;

    public Item(int itemId, String itemName, int itemPercentage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPercentage = itemPercentage;
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
}
