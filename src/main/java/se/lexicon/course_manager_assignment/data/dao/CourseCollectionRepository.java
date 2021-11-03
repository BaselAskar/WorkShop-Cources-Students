package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        if (courses == null) throw new IllegalArgumentException("There is no courses");

        Course course = new Course("Java",LocalDate.parse("2021-11-25"),5,null);

        if (!courses.add(course)) return null;

        return course;
    }

    @Override
    public Course findById(int id) {
        for(Course course : courses){
            if (course.getId() == id) return course;
        }

        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        if (name.equals("") || name.equals(null)) throw new IllegalArgumentException("It's not allowed to be null or empty");

        List<Course> founded = new ArrayList<>();

        for (Course course : courses){
            if (course.getCourseName().equals(name)) founded.add(course);
        }

        return founded;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        if (end.equals(null)) throw new IllegalArgumentException("This date is not allowed to be null");

        List<Course> coursesResult = new ArrayList<>();

        for (Course course : courses){
            if (course.getStartDate().isBefore(end)) coursesResult.add(course);
        }

        return coursesResult;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        if (start.equals(null)) throw new IllegalArgumentException("This date is not allowed to be null");

        List<Course> coursesResult = new ArrayList<>();

        for (Course course : courses){
            if (course.getStartDate().isAfter(start)) coursesResult.add(course);
        }

        return coursesResult;
    }

    @Override
    public Collection<Course> findAll() {
        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {

        List<Course> coursesResult = new ArrayList<>();

        for (Course course : courses){
            for (Student student : course.getStudents()){
                if (student.getId() == studentId) coursesResult.add(course);
            }
        }

        return coursesResult;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
