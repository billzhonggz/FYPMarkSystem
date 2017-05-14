package utli;

import java.util.HashMap;

/**
 * Created by ZHONG on 2017/5/14.
 */
public class GradeMarkTransform {
    private HashMap<String, Float> transformTable;

    public GradeMarkTransform() {
        transformTable = new HashMap<String, Float>();
        // Set up table.
        transformTable.put("A", 4.0F);
        transformTable.put("A-", 3.67F);
        transformTable.put("B+", 3.33F);
        transformTable.put("B", 3.0F);
        transformTable.put("B-", 2.67F);
        transformTable.put("C+", 2.33F);
        transformTable.put("C", 2.0F);
        transformTable.put("C-", 1.67F);
        transformTable.put("D", 1.0F);
        transformTable.put("F", 0.0F);
    }

    public float searchMarkByGrade(String grade) {
        return transformTable.get(grade);
    }
}
