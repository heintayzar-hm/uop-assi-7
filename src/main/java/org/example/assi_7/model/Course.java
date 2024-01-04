package org.example.assi_7.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private static int totalEnrolledStudents = 0;
    private final List<Student> enrolledStudents = new ArrayList<Student>();
    // Other necessary instance variables

    // Constructors
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
    }

    // Getters and setters
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity >= 0) {
            this.maxCapacity = maxCapacity;
        }
    }

    public List<Student> getEnrolledStudents() {
        // Implement logic to get list of enrolled students
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        // Implement logic to add a student to the list of enrolled students
        this.enrolledStudents.add(student);
        totalEnrolledStudents++;
    }

    public List<String> getEnrolledStudentNames() {
        // Implement logic to get list of enrolled students
        List<String> enrolledStudentNames = new ArrayList<String>();
        for (Student student : enrolledStudents) {
            enrolledStudentNames.add(student.getName());
        }
        return enrolledStudentNames;
    }

    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    public static void setTotalEnrolledStudents(int totalEnrolledStudents) {
        Course.totalEnrolledStudents = totalEnrolledStudents;
    }
}
