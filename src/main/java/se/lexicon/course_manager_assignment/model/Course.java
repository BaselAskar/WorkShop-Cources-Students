package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Course {
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Collection<Student> students;

    public Course(String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean rollStudent(Student student){
        if (student == null) throw new IllegalArgumentException("Student is null");

        if (students == null){
            students = new ArrayList<>();
        }

        return students.add(student);
    }

    public boolean unrollStudent(Student student){
        if (student == null) throw new IllegalArgumentException("Student is null");

        if (students == null) throw new IllegalArgumentException("There is no students");

        return students.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && courseName.equals(course.courseName) && startDate.equals(course.startDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}
