package controller;

import model.Item;
import model.ModelItemGroup;
import view.ImportStudentListUI;

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

    public void forwardToAddStudent() {
        try {
            ImportStudentListUI importStudentListUI = new ImportStudentListUI();
            importStudentListUI.setGroup_id(mig.getGroupId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
