package Model.Repository;

import Model.Entity.Enroll;
import Model.Entity.Student;
import Model.Entity.Teacher;

import java.util.List;

public interface EnrollRepository {
    public void updateEnroll(Enroll enroll);
    public void deleteEnroll(Enroll enroll);
    public void createEnroll(Enroll enroll);
    public List<Enroll> getAllEnrolls();
    public Enroll getEnrollByStudentTeacher(Teacher teacher, Student student);
    public List<Enroll> getEnrollsByStudent(Student student);
    public List<Enroll> getEnrollByTeacher(Teacher teacher);

}
