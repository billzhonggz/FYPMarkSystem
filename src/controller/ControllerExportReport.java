package controller;

import model.ModelEnterMark;
import model.ModelExportReport;
import view.EnterMarkUI;

/**
 * Created by ZHONG on 2017/5/14.
 */
public class ControllerExportReport {
    private ModelExportReport mer;

    public ControllerExportReport(ModelExportReport mer) {
        this.mer = mer;
    }

    public void assignGrades() {
        mer.assignGrades();
    }

    public void backToEnterMark() {
        ModelEnterMark mek = new ModelEnterMark();
        ControllerEnterMark cek = new ControllerEnterMark(mek);
        EnterMarkUI enterMarkUI = new EnterMarkUI();
        enterMarkUI.setMVC(mek, cek);
    }

    public void writeExcel() {
        mer.writeExcel();
    }


}
