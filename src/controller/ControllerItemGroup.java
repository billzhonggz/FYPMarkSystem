package controller;

import model.Item;
import model.ModelItemGroup;

/**
 * Created by ZHONG on 2017/5/8.
 */
public class ControllerItemGroup {
    private ModelItemGroup mig;

    public ControllerItemGroup(ModelItemGroup mig) {
        this.mig = mig;
    }

    public void addItemToList(String itemName, int itemPer) {
        mig.addItemToList(new Item(itemName, itemPer));
    }

    public void addItemsToDb() {
        mig.addItemsToDb();
    }
}
