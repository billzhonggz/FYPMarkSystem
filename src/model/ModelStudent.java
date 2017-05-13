package model;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ModelStudent {
    private int group_id;
    private int s_id;
    private String name;
    private long mobile;
    private String project_name;
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;

    public ModelStudent() {
    }

    public ModelStudent(int group_id, int s_id, String name, long mobile, String project_name){
        this.group_id = group_id;
        this.s_id = s_id;
        this.name = name;
        this.mobile = mobile;
        this.project_name = project_name;
    }
//    public String toString(){
//        return "ModelStudent[s_id = "+ s_id +", name = "+ name +", mobile = "+ mobile +", group_id = "+ group_id +"]";
//    }
    public int getS_id(){
        return s_id;
    }
    public void setS_id(int s_id){
        this.s_id = s_id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public long getMobile(){
        return mobile;
    }
    public void setMobile(long mobile){
        this.mobile = mobile;
    }
    public String getProject_name(){
        return project_name;
    }
    public void setProject_name(String project_name){
        this.project_name = project_name;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public int getScore4() {
        return score4;
    }

    public void setScore4(int score4) {
        this.score4 = score4;
    }

    public int getScore5() {
        return score5;
    }

    public void setScore5(int score5) {
        this.score5 = score5;
    }
}
