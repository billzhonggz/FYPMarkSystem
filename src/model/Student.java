package model;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class Student {
    private int group_id;
    private int s_id;
    private String name;
    private long mobile;
    private String supervisor;
    private String observer;
    private String examiner;
    private String project_name;
    private String g_i;
    private String score1;
    private String score2;
    private String score3;
    private String score4;
    private String score5;
    private String score6;
    private String score7;
    private String score8;
    private String score9;
    private String score10;
    private String score11;
    private String score12;
    private float totalScore;

    public Student() {
    }

    public Student(int group_id, int s_id, String name, long mobile, String supervisor, String observer, String examiner, String project_name, String g_i) {
        this.group_id = group_id;
        this.s_id = s_id;
        this.name = name;
        this.mobile = mobile;
        this.supervisor = supervisor;
        this.observer = observer;
        this.examiner = examiner;
        this.project_name = project_name;
        this.g_i = g_i;
    }

    public Student(int group_id, int s_id, String name, long mobile, String supervisor, String observer, String examiner, String project_name, String g_i, String score1, String score2, String score3, String score4, String score5, String score6, String score7, String score8, String score9, String score10, String score11, String score12, float totalScore) {
        this.group_id = group_id;
        this.s_id = s_id;
        this.name = name;
        this.mobile = mobile;
        this.supervisor = supervisor;
        this.observer = observer;
        this.examiner = examiner;
        this.project_name = project_name;
        this.g_i = g_i;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
        this.score6 = score6;
        this.score7 = score7;
        this.score8 = score8;
        this.score9 = score9;
        this.score10 = score10;
        this.score11 = score11;
        this.score12 = score12;
        this.totalScore = totalScore;
    }

    //    public String toString(){
//        return "Student[s_id = "+ s_id +", name = "+ name +", mobile = "+ mobile +", group_id = "+ group_id +"]";
//    }
    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public String getG_i() {
        return g_i;
    }

    public void setG_i(String g_i) {
        this.g_i = g_i;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getScore3() {
        return score3;
    }

    public void setScore3(String score3) {
        this.score3 = score3;
    }

    public String getScore4() {
        return score4;
    }

    public void setScore4(String score4) {
        this.score4 = score4;
    }

    public String getScore5() {
        return score5;
    }

    public void setScore5(String score5) {
        this.score5 = score5;
    }

    public String getScore6() {
        return score6;
    }

    public void setScore6(String score6) {
        this.score6 = score6;
    }

    public String getScore7() {
        return score7;
    }

    public void setScore7(String score7) {
        this.score7 = score7;
    }

    public String getScore8() {
        return score8;
    }

    public void setScore8(String score8) {
        this.score8 = score8;
    }

    public String getScore9() {
        return score9;
    }

    public void setScore9(String score9) {
        this.score9 = score9;
    }

    public String getScore10() {
        return score10;
    }

    public void setScore10(String score10) {
        this.score10 = score10;
    }

    public String getScore11() {
        return score11;
    }

    public void setScore11(String score11) {
        this.score11 = score11;
    }

    public String getScore12() {
        return score12;
    }

    public void setScore12(String score12) {
        this.score12 = score12;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
}

