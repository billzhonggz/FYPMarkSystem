package model;

/**
 * Created by ZHONG on 2017/5/3.
 */
public class ModelStudent {
    private int s_id;
    private String name;
    private int mobile;
    private int group_id;
    public ModelStudent(){}
    public ModelStudent(int s_id, String name, int mobile){
        this.s_id = s_id;
        this.name = name;
        this.mobile = mobile;
        this.group_id = group_id;
    }
    public String toString(){
        return "ModelStudent[s_id = "+ s_id +", name = "+ name +", mobile = "+ mobile +", group = "+ group_id +"]";
    }
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
    public int getMobile(){
        return mobile;
    }
    public void setMobile(int mobile){
        this.mobile = mobile;
    }

}
