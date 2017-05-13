package utli;

/**
 * Created by 子楹 on 2017/5/8.
 */
public class TestExcel {
    public static void main(String[] args) {
        try {
            ReadExcel.readExcel("excel","student.xlsx",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
