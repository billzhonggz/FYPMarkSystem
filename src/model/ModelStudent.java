package model;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ModelStudent {
    private int s_id;
    private String name;
    private long mobile;
    private String project_name;

    public ModelStudent() {
    }

    public ModelStudent(int s_id, String name, long mobile, String project_name){
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
}
