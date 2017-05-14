package model;

import utli.GradeMarkTransform;
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
    private ArrayList<Student> students;
    private ArrayList<Item> items;
    private ArrayList<Integer> groupIds;
    private int currentGroupId = 0;
    private SQLiteAccess s;

    public ModelEnterMark() {
        this.students = new ArrayList<Student>();
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

    public void LoadStudents() {
        // Read student list.
        students.clear();
        try {
            ResultSet rs1 = s.execSqlWithReturn("SELECT * FROM student WHERE group_id=" + currentGroupId + ";");
            while (rs1.next()) {
                Student student;
                int s_id = rs1.getInt(rs1.findColumn("s_id"));
                ResultSet rs2 = s.execSqlWithReturn("SELECT * FROM student_score WHERE s_id=" + s_id + " AND eg_id=" + currentGroupId + ";");
                if (rs2.next()) {
                    student = new Student(
                            this.currentGroupId,
                            s_id,
                            rs1.getString(rs1.findColumn("name")),
                            rs1.getLong(rs1.findColumn("mobile")),
                            rs1.getString(rs1.findColumn("supervisor")),
                            rs1.getString(rs1.findColumn("observer")),
                            rs1.getString(rs1.findColumn("examiner")),
                            rs1.getString(rs1.findColumn("project_name")),
                            rs1.getString(rs1.findColumn("g_i")),
                            rs2.getString(rs2.findColumn("item1_grade")),
                            rs2.getString(rs2.findColumn("item2_grade")),
                            rs2.getString(rs2.findColumn("item3_grade")),
                            rs2.getString(rs2.findColumn("item4_grade")),
                            rs2.getString(rs2.findColumn("item5_grade")),
                            rs2.getString(rs2.findColumn("item6_grade")),
                            rs2.getString(rs2.findColumn("item7_grade")),
                            rs2.getString(rs2.findColumn("item8_grade")),
                            rs2.getString(rs2.findColumn("item9_grade")),
                            rs2.getString(rs2.findColumn("item10_grade")),
                            rs2.getString(rs2.findColumn("item11_grade")),
                            rs2.getString(rs2.findColumn("item12_grade")),
                            rs2.getFloat(rs2.findColumn("total_score"))
                    );
                } else {
                    student = new Student(
                            this.currentGroupId,
                            s_id,
                            rs1.getString(rs1.findColumn("name")),
                            rs1.getLong(rs1.findColumn("mobile")),
                            rs1.getString(rs1.findColumn("supervisor")),
                            rs1.getString(rs1.findColumn("observer")),
                            rs1.getString(rs1.findColumn("examiner")),
                            rs1.getString(rs1.findColumn("project_name")),
                            rs1.getString(rs1.findColumn("g_i"))
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
        items.clear();
        int[] itemIds = new int[12];
        try {
            ResultSet rs1 = s.execSqlWithReturn("SELECT * FROM evaluation_groups WHERE id=" + currentGroupId + ";");
            while (rs1.next()) {
                itemIds[0] = rs1.getInt(2);
                itemIds[1] = rs1.getInt(3);
                itemIds[2] = rs1.getInt(4);
                itemIds[3] = rs1.getInt(5);
                itemIds[4] = rs1.getInt(6);
                itemIds[5] = rs1.getInt(7);
                itemIds[6] = rs1.getInt(8);
                itemIds[7] = rs1.getInt(9);
                itemIds[8] = rs1.getInt(10);
                itemIds[9] = rs1.getInt(11);
                itemIds[10] = rs1.getInt(12);
                itemIds[11] = rs1.getInt(13);
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

    public void saveScore(int s_id, String score1, String score2, String score3, String score4, String score5, String score6, String score7, String score8, String score9, String score10, String score11, String score12) {
        // Update student list first.
        Student student = findModelStudentBySid(s_id);
        student.setScore1(score1);
        student.setScore2(score2);
        student.setScore3(score3);
        student.setScore4(score4);
        student.setScore5(score5);
        student.setScore6(score6);
        student.setScore7(score7);
        student.setScore8(score8);
        student.setScore9(score9);
        student.setScore10(score10);
        student.setScore11(score11);
        student.setScore12(score12);

        GradeMarkTransform transform = new GradeMarkTransform();
        // Calculate total score.
        float st = 0.0F;
        st += transform.searchMarkByGrade(score1) * items.get(0).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score2) * items.get(1).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score3) * items.get(2).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score4) * items.get(3).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score5) * items.get(4).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score6) * items.get(5).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score7) * items.get(6).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score8) * items.get(7).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score9) * items.get(8).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score10) * items.get(9).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score11) * items.get(10).getItemPercentage() / 100;
        st += transform.searchMarkByGrade(score12) * items.get(11).getItemPercentage() / 100;
        student.setTotalScore(st);

        // Update database.
        try {
            // Check existence fot this record.
            ResultSet rs = s.execSqlWithReturn("SELECT * FROM student_score WHERE s_id=" + s_id + " AND eg_id=" + currentGroupId + ";");
            // If exist, do update.
            if (rs.next()) {
                String sql = "UPDATE student_score SET " +
                        "item1_grade='" + score1 + "', item2_grade='" + score2 + "', item3_grade='" + score3 + "', " +
                        "item4_grade='" + score4 + "', item5_grade='" + score5 + "', item6_grade='" + score6 + "', " +
                        "item7_grade='" + score7 + "', item8_grade='" + score8 + "', item9_grade='" + score9 + "', " +
                        "item10_grade='" + score10 + "', item11_grade='" + score11 + "', item12_grade='" + score12 + "', " +
                        "total_score=" + st + " WHERE s_id=" + s_id + " AND eg_id=" + currentGroupId + ";";
                s.execSqlUpdate(sql);
            } else {
                String sql = "INSERT INTO student_score(s_id, eg_id, item1_grade, item2_grade, item3_grade, item4_grade, item5_grade, item6_grade, item7_grade, " +
                        "item8_grade, item9_grade, item10_grade, item11_grade, item12_grade, total_score) " +
                        "VALUES(" + s_id + "," + currentGroupId + ",'" + score1 + "','" + score2 + "','" + score3 + "','" + score4 + "','" + score5 + "','" +
                        score6 + "','" + score7 + "','" + score8 + "','" + score9 + "','" + score10 + "','" + score11 + "','" + score12 + "'," + st + ");";
                s.execSqlNoReturn(sql);
            }
        } catch (SQLiteConnectionInvalidException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        notifyView();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student findModelStudentBySid(int s_id) {
        for (Student student : students) {
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

    public void setCurrentGroupId(int currentGroupId) {
        this.currentGroupId = currentGroupId;
        notifyView();
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
