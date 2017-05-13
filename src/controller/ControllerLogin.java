package controller;

import model.ModelEnterMark;
import model.ModelItemGroup;
import model.ModelLogin;
import view.AddItemUI;
import view.EnterMarkUI;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ControllerLogin {
    private ModelLogin ml;

    public ControllerLogin(ModelLogin ml) {
        this.ml = ml;
    }

    public boolean checkPwd(String pwd) {
        boolean modelCheckResult = ml.checkPwd(pwd);
        return modelCheckResult;
    }

    public void forwardToNext() {
        // Check evaluation group.
        boolean existence = ml.checkGroupExistence();
        if (existence) {
            ModelEnterMark modelEnterMark = new ModelEnterMark();
            ControllerEnterMark controllerEnterMark = new ControllerEnterMark(modelEnterMark);
            EnterMarkUI enterMarkUI = new EnterMarkUI();
            enterMarkUI.setMVC(modelEnterMark,controllerEnterMark);
            ml.closeDB();
        } else {
            // TODO: Forward to AddItemUI.
            ModelItemGroup mig = new ModelItemGroup();
            ControllerItemGroup cig = new ControllerItemGroup(mig);
            AddItemUI addItemUI = new AddItemUI();
            addItemUI.setMVC(mig, cig);
            ml.closeDB();
        }
    }

}
