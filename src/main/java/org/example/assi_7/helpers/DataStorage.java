package org.example.assi_7.helpers;

import org.example.assi_7.model.Course;
import org.example.assi_7.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static DataStorage instance = null;
    private final List<Student> students;

    private final List<Course> courses;

    private DataStorage() {
        students = new ArrayList<>();
        // dummy data
        courses = new ArrayList<>();
        courses.add(new Course("CSE 111", "Intro to Computer Science"));
        courses.add(new Course("CSE 222", "Data Structures"));
        courses.add(new Course("CSE 333", "Algorithms"));
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    // You can add more methods as needed

    // For example, to enroll a student in a course
    public void enrollStudent(Student student, Course course) {
        // Implement logic to enroll student in a course
    }


    public Student getStudentByCode(String studentCode) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentCode)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(student.getStudentID())) {
                students.set(i, student);
                break;
            }
        }
    }


    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }
}
