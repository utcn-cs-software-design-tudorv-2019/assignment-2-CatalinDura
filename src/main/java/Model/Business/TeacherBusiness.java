package Model.Business;

import Model.Entity.Teacher;
import Model.Repository.TeacherRepository;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class TeacherBusiness {

    private TeacherRepository teacherRepository;
    private Teacher loggedTeacher;

    @Inject
    public TeacherBusiness(TeacherRepository teacherRepository){
        this.teacherRepository=teacherRepository;
        loggedTeacher=new Teacher();
    }

    public void updateTeacher(){
        teacherRepository.updateTeacher(loggedTeacher);
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.getAllTeachers();
    }

    public String getTeacherPasswordByName(String name){
        return teacherRepository.getTeacherByName(name).getPassword();
    }

    public void setLoggedTeacher(String name){
        loggedTeacher=teacherRepository.getTeacherByName(name);
    }

    public Teacher getLoggedTeacher(){
        return loggedTeacher;
    }

    public List<String> getAvailableCourses(){
        List<String> courses=new ArrayList<String>();
        List<Teacher> teachers=teacherRepository.getAllTeachers();
        for(Teacher aux:teachers){
            courses.add(aux.getName()+"@"+aux.getCourse());
        }
        return courses;
    }

    public Teacher getTeacherByCourse(String course){
        return teacherRepository.getTeacherByCourse(course);
    }

    public void setLoggedTeacherName(String name){
        loggedTeacher.setName(name);
    }

    public void setLoggedTeacherEmail(String email){
        loggedTeacher.setEmail(email);
    }

    public void setLoggedTeacherPassword(String password){
        loggedTeacher.setPassword(password);
    }
}
