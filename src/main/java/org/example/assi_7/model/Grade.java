package org.example.assi_7.model;

public class Grade {
    private final int studentId;
    private final int courseId;
    private final String grade;

    public Grade(int studentId, int courseId, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getGrade() {
        return grade;
    }
}

