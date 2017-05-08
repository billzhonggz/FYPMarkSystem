package utli;

import java.io.IOException;
import java.util.List;
/**
 * Created by 子楹 on 2017/5/8.
 */
public class TestExcel {
    public static void main(String[] args) {

        try {
            ReadExcel.readExcel("D://Student.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
