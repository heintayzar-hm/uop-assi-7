### Programming Assignment 7

#### Description

- This assignment is a simple implementation of a javafx application that allows the user to enter a number of students and their respective scores and then displays the average score.
- This application is a simple implementation of the MVC design pattern.
- The model includes the `Student` class, the `Course` class and the `Grades` class.
- The view includes all the files in the `resources` folder. (css, fxml, images). The main App is `App.xml`, which will help navigate to the other views.(Student.fxml, Course.fxml, Grades.fxml, and other views to help crud)
- The controller includes the `AppController` class, which is the controller for the `App.fxml` view. It also includes the `StudentController` class, which is the controller for other views. The `StudentController` class is the controller for the `Student.fxml` view, the `Course.fxml` view, and the `Grades.fxml` view.
- We have a side bar but it is just basic implementation, it does not do anything.
- All required functionality is implemented inside Student View.
- Users can be added, and updated. Other functionality is implemented through the view functionality, including the ability to enroll courses, and add grades.
- Note: Courses are implemented using dummmy data, and are not saved to the database. 

Helpers classes:
-  we will connect to all data classes, we have named to `DataStorage`, with the idea of storing data in application.
- `Validator` class is used to validate user input.

#### How the logic works

- we have added small video demo to show how the application works.
- As for logic, our application started with `App.java` class, which will link to all main screens.
- Currently we have only one main screen, which is the `Student.fxml` screen.
- The `Student.fxml` screen will allow the user to add a student, update a student and view all students. All functionalities are implemented in the new stage screen. We use `Validator` class to validate user input. Furthermore, we use `DataStorage` class to store data in application.
- The view functionality further allows the user to enroll courses, and add grades. They are also done in new stage screens.
- All data are refreshed after each action, and the user can see the changes in the table view.

#### How to run

- please install the respective ide, I used IntelliJ IDEA (not tested on other IDEs)
- simply run the main method in the `App.java` class


#### Video Demo
-
[![Watch the vid](https://www.loom.com/share/87233e1c1a82463a87d0e30946b79724?sid=c17030a0-f301-4cc7-b607-d2eadc21a443)

Link: https://www.loom.com/share/87233e1c1a82463a87d0e30946b79724?sid=c17030a0-f301-4cc7-b607-d2eadc21a443