package controller;

import model.ModelEnterMark;
import model.ModelExportReport;
import model.ModelItemGroup;
import model.ModelLogin;
import view.AddItemUI;
import view.ExportReportUI;
import view.LoginUI;

/**
 * Created by ZHONG on 2017/5/13.
 */
public class ControllerEnterMark {
    private ModelEnterMark mek;

    public ControllerEnterMark(ModelEnterMark mek) {
        this.mek = mek;
    }

    public void searchGroupIds() {
        mek.searchGroupIds();
    }

    public void setCurrentGroupId(int currentGroupId) {
        mek.setCurrentGroupId(currentGroupId);
        mek.LoadStudents();
        mek.LoadItems();
    }

    public void setScore(int s_id, int s1, int s2, int s3, int s4, int s5, int st) {
        mek.saveScore(s_id, s1, s2, s3, s4, s5, st);
    }

    public void backToLogin() {
        ModelLogin ml = new ModelLogin();
        ControllerLogin cl = new ControllerLogin(ml);
        LoginUI loginUI = new LoginUI();
        loginUI.setMVC(ml, cl);
        mek.closeDB();
    }

    public void addNewGroup() {
        ModelItemGroup modelItemGroup = new ModelItemGroup();
        ControllerItemGroup controllerItemGroup = new ControllerItemGroup(modelItemGroup);
        AddItemUI addItemUI = new AddItemUI();
        addItemUI.setMVC(modelItemGroup, controllerItemGroup);
        mek.closeDB();
    }

    public void forwardToExport() {
        //TODO: Forward to export.
        ModelExportReport mer = new ModelExportReport();
        mer.setStudents(mek.getStudents());
        mer.setItems(mek.getItems());
        mer.setCurrentGroupId(mek.getCurrentGroupId());
        ControllerExportReport cer = new ControllerExportReport(mer);
        ExportReportUI exportReportUI = new ExportReportUI();
        exportReportUI.setMVC(mer, cer);
        mek.closeDB();
    }
}
