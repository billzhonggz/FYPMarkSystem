package controller;

import model.ModelEnterMark;
import model.ModelLogin;
import view.LoginUI;

/**
 * Created by ZHONG on 2017/5/13.
 */
public class ControllerEnterMark {
    private ModelEnterMark mek;

    public ControllerEnterMark(ModelEnterMark mek) {
        this.mek = mek;
    }

    public void LoadStudents() {
        mek.LoadStudents();
    }

    public void LoadItems() {
        mek.LoadItems();
    }

    public void searchGroupIds() {
        mek.searchGroupIds();
    }

    public void setCurrentGroupId(int currentGroupId) {
        mek.setCurrentGroupId(currentGroupId);
        mek.LoadStudents();
        mek.LoadItems();
    }

    public void backToLogin() {
        ModelLogin ml = new ModelLogin();
        ControllerLogin cl = new ControllerLogin(ml);
        LoginUI loginUI = new LoginUI();
        loginUI.setMVC(ml, cl);
        mek.closeDB();
    }

    public void forwardToExport() {
        //TODO: Forward to export.
    }
}
