/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.*;

public class EnrollmentManagement {

    private Map<String, Enrollment> enrollmentMap = new HashMap<>();

    private EnrollmentDatabaseImpl enrollmentDatabase = new EnrollmentDatabaseImpl(new FileHandler());

    // 🔵 생성자 - 시작 시 파일에서 수강 데이터 읽기
    public EnrollmentManagement() {
        List<Enrollment> loadedEnrollments = enrollmentDatabase.readEnrollmentsFromFile("enrollments.txt");
        for (Enrollment enrollment : loadedEnrollments) {
            enrollmentMap.put(enrollment.getStudentID(), enrollment);
        }
    }

    // 🔵 메뉴 메소드
    public void enrollmentManagementMenu(StudentManagement studentManagement, CourseManagement courseManagement) {
        Scanner scanner = new Scanner(System.in);
        Validator validator = new Validator();
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n=== Enrollment Management Menu ===");
            System.out.println("1. Enroll Course to Student");
            System.out.println("2. View Student Courses");
            System.out.println("3. Cancel Student Course Enrollment");
            System.out.println("4. List All Enrollments");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer

                switch (choice) {
                    case 1:
                        enrollCourseToStudent(studentManagement, courseManagement);
                        break;
                    case 2:
                        viewStudentCourses();
                        break;
                    case 3:
                        cancelEnrollment();
                        break;
                    case 4:
                        listAllEnrollments();
                        break;
                    case 5:
                        System.out.println("Exiting Enrollment Management Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    // 🔵 수강신청
    public void enrollCourseToStudent(StudentManagement studentManagement, CourseManagement courseManagement) {
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        if (studentManagement.searchStudentObject(studentID) == null) {
            System.out.println("Student not found.");
            return;
        }

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            enrollment = new Enrollment(studentID);
            enrollmentMap.put(studentID, enrollment);
        }

        System.out.println("Enter Course ID to enroll:");
        String courseID = validator.getValidCourseCode();

        Course course = courseManagement.searchCourse(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        enrollment.addCourseToStudent(course);

        // 파일 저장
        enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");

        System.out.println("Course successfully enrolled for student " + studentID + ".");
    }

    // 🔵 학생 수강 목록 조회
    public void viewStudentCourses() {
        Validator validator = new Validator();
        //System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }

        List<Course> courses = enrollment.getCourseList();
        if (courses.isEmpty()) {
            System.out.println("No courses enrolled for this student.");
        } else {
            System.out.println("Courses enrolled by student " + studentID + ":");
            for (Course course : courses) {
                System.out.println("- " + course.getCourseName() + " (" + course.getCourseID() + "), Credit: " + course.getCredit());
            }
        }
    }

    // 🔵 수강 취소
    public void cancelEnrollment() {
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }

        System.out.println("Enter Course ID to cancel:");
        String courseID = validator.getValidCourseCode();

        boolean removed = false;
        Iterator<Course> iterator = enrollment.getCourseList().iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseID().equals(courseID)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");
            System.out.println("Course enrollment cancelled for student " + studentID + ".");
        } else {
            System.out.println("Course not found in student's enrollment.");
        }
    }

    // 🔵 전체 수강 목록 조회
    public void listAllEnrollments() {
        if (enrollmentMap.isEmpty()) {
            System.out.println("No enrollment records found.");
            return;
        }

        System.out.println("All Enrollments:");
        for (Enrollment enrollment : enrollmentMap.values()) {
            System.out.println("----------------------------");
            System.out.println("Student ID: " + enrollment.getStudentID());
            for (Course course : enrollment.getCourseList()) {
                System.out.println("- " + course.getCourseName() + " (" + course.getCourseID() + "), Credit: " + course.getCredit());
            }
        }
    }
}