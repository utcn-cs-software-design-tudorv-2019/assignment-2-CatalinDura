package Model.Repository;

import Model.Entity.Teacher;

import java.util.List;

public interface TeacherRepository {
    public void updateTeacher(Teacher teacher);
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherByName(String name);
    public Teacher getTeacherByCourse(String course);
}
