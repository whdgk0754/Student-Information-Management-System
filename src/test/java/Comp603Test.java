/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jonghapark
 */
//header for test
//make sure clear DB then run test
//make sure run project first then run test
import com.mycompany.comp603_assignment1.Student;
import com.mycompany.comp603_assignment1.StudentManagement;
import com.mycompany.comp603_assignment1.CourseManagement;
import com.mycompany.comp603_assignment1.EnrollmentManagement;
import com.mycompany.comp603_assignment1.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Comp603Test {
    private StudentManagement sm;
    private CourseManagement cm;
    private EnrollmentManagement em;

    @BeforeEach
    public void setUp() {
        sm = new StudentManagement();
        cm = new CourseManagement();
        em = new EnrollmentManagement();
    }

    @Test
    public void testAddStudent() {
        Student s = new Student("12345678", "Alice", "CS");
        sm.addStudent(s);
        assertNotNull(sm.searchStudentObject("12345678"));
    }

    @Test
    public void testAddCourse() {
        Course c = new Course("Comp603", "PDC", 15);
        cm.addCourse(c);
        assertNotNull(cm.searchCourse("Comp603"));
    }

    @Test
    public void testEnrollCourse() {
        sm.addStudent(new Student("11111111", "Alice", "CS"));
        cm.addCourse(new Course("Comp604", "PDC", 15));
        em.enrollCourseToStudent("11111111", "Comp604");

        List<Course> enrolled = em.getEnrolledCourses("11111111");
        assertEquals(1, enrolled.size());
        assertEquals("Comp604", enrolled.get(0).getCourseID());
    }

    @Test
    public void testDeleteStudentAlsoDeletesEnrollments() {
        sm.addStudent(new Student("22222222", "Alice", "CS"));
        cm.addCourse(new Course("Comp605", "PDC", 15));
        em.enrollCourseToStudent("22222222", "Comp605");

        sm.deleteStudent("22222222");

        List<Course> courses = em.getEnrolledCourses("22222222");
        assertTrue(courses.isEmpty());
    }

    @Test
    public void testPreventDuplicateEnrollment() {
        sm.addStudent(new Student("33333333", "Bob", "IT"));
        cm.addCourse(new Course("Comp606", "PDC", 15));
        em.enrollCourseToStudent("33333333", "Comp606");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            em.enrollCourseToStudent("33333333", "Comp606");
        });

        assertEquals("Student already enrolled in this course.", ex.getMessage());
    }
}