package model;

import utli.SQLiteAccess;
import utli.SQLiteConnectionInvalidException;
import view.EnterMarkUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ZHONG on 2017/5/13.
 */
public class ModelEnterMark {
    private EnterMarkUI view;
    private ArrayList<ModelStudent> students;
    private ArrayList<Item> items;
    private ArrayList<Integer> groupIds;
    private int currentGroupId = 0;
    private SQLiteAccess s;

    public ModelEnterMark() {
        this.students = new ArrayList<ModelStudent>();
        this.groupIds = new ArrayList<Integer>();
        this.items = new ArrayList<Item>();
        s = new SQLiteAccess();
    }

    public void searchGroupIds() {
        try {
            ResultSet rs = s.execSqlWithReturn("SELECT id FROM evaluation_groups;");
            while (rs.next()) {
                groupIds.add(rs.getInt(1));
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyView();
    }

    public void setCurrentGroupId(int currentGroupId) {
        this.currentGroupId = currentGroupId;
        //notifyView();
    }

    public void LoadStudents() {
        // Read student list.
        try {
            ResultSet rs1 = s.execSqlWithReturn("SELECT * FROM student WHERE group_id=" + currentGroupId + ";");
            while (rs1.next()) {
                ModelStudent student;
                int s_id = rs1.getInt(rs1.findColumn("s_id"));
                ResultSet rs2 = s.execSqlWithReturn("SELECT * FROM student_score WHERE s_id=" + s_id + ";");
                if (rs2.next()) {
                    student = new ModelStudent(
                            this.currentGroupId,
                            s_id,
                            rs1.getString(rs1.findColumn("name")),
                            rs1.getLong(rs1.findColumn("mobile")),
                            rs1.getString(rs1.findColumn("project_name")),
                            rs2.getInt(rs2.findColumn("item1_score")),
                            rs2.getInt(rs2.findColumn("item2_score")),
                            rs2.getInt(rs2.findColumn("item3_score")),
                            rs2.getInt(rs2.findColumn("item4_score")),
                            rs2.getInt(rs2.findColumn("item5_score")),
                            rs2.getInt(rs2.findColumn("total_score"))
                            );
                }
                else {
                    student = new ModelStudent(
                            this.currentGroupId,
                            s_id,
                            rs1.getString(rs1.findColumn("name")),
                            rs1.getLong(rs1.findColumn("mobile")),
                            rs1.getString(rs1.findColumn("project_name"))
                    );
                }
                students.add(student);
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyView();
    }

    public void LoadItems() {
        int[] itemIds = new int[5];
        try {
            ResultSet rs1 = s.execSqlWithReturn("SELECT * FROM evaluation_groups WHERE id=" + currentGroupId + ";");
            while (rs1.next()) {
                itemIds[0] = rs1.getInt(2);
                itemIds[1] = rs1.getInt(3);
                itemIds[2] = rs1.getInt(4);
                itemIds[3] = rs1.getInt(5);
                itemIds[4] = rs1.getInt(6);
            }
            for (int i = 0; i < itemIds.length; i++) {
                ResultSet rs2 = s.execSqlWithReturn("SELECT * FROM evaluation_items WHERE id=" + itemIds[i]);
                Item item = new Item(
                        rs2.getString(rs2.findColumn("name")),
                        rs2.getInt(rs2.findColumn("percentage"))
                );
                items.add(item);
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyView();
    }

    public void saveScore(int s_id, int score1, int score2, int score3, int score4, int score5, int st) {
        // Update student list first.
        ModelStudent student = findModelStudentBySid(s_id);
        student.setScore1(score1);
        student.setScore2(score2);
        student.setScore3(score3);
        student.setScore4(score4);
        student.setScore5(score5);
        // Update database.
        try {
            // Check existence fot this record.
            ResultSet rs = s.execSqlWithReturn("SELECT * FROM student_score WHERE s_id=" + s_id + " AND eg_id=" + currentGroupId +";");
            // If exist, do update.
            if (rs.next()) {
                s.execSqlUpdate("UPDATE student_score SET " +
                        "item1_score=" + score1 +", item2_score=" + score2 + ", item3_score=" + score3 + ", " +
                        "item4_score=" + score4 + ", item5_score=" + score5 + ", total_score=" + st + " WHERE s_id=" + s_id +";");
            }
            else {
                s.execSqlNoReturn("INSERT INTO student_score(s_id, eg_id, item1_score, item2_score, item3_score, item4_score, item5_score, total_score) " +
                        "VALUES(" + s_id + "," + currentGroupId + "," + score1 + "," + score2 + "," + score3 + "," + score4 + "," + score5 + "," + st + ");");
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        notifyView();
    }

    public ArrayList<ModelStudent> getStudents() {
        return students;
    }

    public ModelStudent findModelStudentBySid(int s_id) {
        for (ModelStudent student : students) {
            if (student.getS_id() == s_id)
                return student;
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Integer> getGroupIds() {
        return groupIds;
    }

    public int getCurrentGroupId() {
        return currentGroupId;
    }

    public void closeDB() {
        s.closeConnection();
    }

    public void setView(EnterMarkUI view) {
        this.view = view;
    }

    private void notifyView() {
        view.notifyModelListener();
    }
}
