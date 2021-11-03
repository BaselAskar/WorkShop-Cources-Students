package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        Student student = new Student(name,email,address);

        if (!students.add(student)) return null;

        return student;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        for (Student student : students){
            if (student.getEmail().equalsIgnoreCase(email)) return student;
        }

        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        if (name.isEmpty() || name.equals(null)) throw new IllegalArgumentException("It's not allowed to be null or empty");

        List<Student> studentsResult = new ArrayList<>();

        for (Student student : students){
            if (student.getName().equalsIgnoreCase(name)) studentsResult.add(student);
        }

        return studentsResult;
    }

    @Override
    public Student findById(int id) {
        for (Student student : students){
            if (student.getId() == id) return student;
        }

        return null;
    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
