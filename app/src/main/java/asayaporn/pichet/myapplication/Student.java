package asayaporn.pichet.myapplication;

import java.util.ArrayList;

/**
 * Created by IMSICK on 5/11/2017.
 */

public class Student {
    float score;
    String name;

    public Student(String name,float score) {
        this.score = score;
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Student> getSampleStudentData(int size) {

        ArrayList<Student> student = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            student.add(new Student("Android v."+i, (float) Math.random() * 100));
        }
        return student;
    }
}
