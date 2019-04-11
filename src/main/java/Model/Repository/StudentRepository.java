package Model.Repository;

import Model.Entity.Student;

import java.util.List;

public interface StudentRepository {
    public void createStudent(Student student);
    public void deleteStudent(Student student);
    public void updateStudent(Student student);
    public List<Student> getAllStudents();
    public Student getStudentByName(String name);
}
