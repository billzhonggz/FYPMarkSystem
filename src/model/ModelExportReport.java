package model;

import utli.WriteExcel;
import view.ExportReportUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by ZHONG on 2017/5/14.
 */
public class ModelExportReport {
    private ExportReportUI view;
    private ArrayList<ModelStudent> students;
    private ArrayList<Item> items;
    private int currentGroupId;

    public ModelExportReport() {
        students = new ArrayList<ModelStudent>();
        items = new ArrayList<Item>();
        this.currentGroupId = 0;
    }

    public int getCurrentGroupId() {
        return currentGroupId;
    }

    public void setCurrentGroupId(int currentGroupId) {
        this.currentGroupId = currentGroupId;
    }

    public void assignGrades() {
        // Sort student list by total score.
        Collections.sort(students, new SortByScore());
        // Assign grades.
        // TODO: Confirm how to assign grades.

        notifyView();
    }

    public void writeExcel() {
        WriteExcel.writeExcel(students, items);
    }

    public void setView(ExportReportUI view) {
        this.view = view;
        notifyView();
    }

    public ArrayList<ModelStudent> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<ModelStudent> students) {
        this.students = students;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    private void notifyView() {
        this.view.notifyModelListener();
    }

    class SortByScore implements Comparator {
        public int compare(Object o1, Object o2) {
            ModelStudent s1 = (ModelStudent) o1;
            ModelStudent s2 = (ModelStudent) o2;
            if (s1.getTotalScore() > s2.getTotalScore())
                return -1;
            else if (s1.getTotalScore() < s2.getTotalScore())
                return 1;
            else
                return 0;
        }
    }
}
