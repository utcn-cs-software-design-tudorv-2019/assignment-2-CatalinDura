package Model.Business;

import Model.Entity.Enroll;
import Model.Entity.Student;
import Model.Entity.Teacher;
import Model.Repository.EnrollRepository;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class EnrollBusiness {

    EnrollRepository enrollRepository;
    Enroll enrollTobeInserted;
    Enroll enrollToBeUpdated;

    @Inject
    public EnrollBusiness(EnrollRepository enrollRepository){
        this.enrollRepository=enrollRepository;
    }
    public void createEnroll(){
        enrollRepository.createEnroll(enrollTobeInserted);
    }

    public void deleteEnroll(Enroll enroll){
        enrollRepository.deleteEnroll(enroll);
    }

    public int updateEnroll(){
        enrollRepository.updateEnroll(enrollToBeUpdated);
        return 0;
    }

    public List<Enroll> getAllEnrolls(){
        return enrollRepository.getAllEnrolls();
    }

    public void deleteEnrollsOfStudent(Student student){
        List<Enroll> lista=enrollRepository.getEnrollsByStudent(student);
        for(Enroll aux:lista){
            enrollRepository.deleteEnroll(aux);
        }
    }

    public List<String> getStudentGrades(Student student){
        List<String> list=new ArrayList<String>();
        List<Enroll> listEnrolls=enrollRepository.getEnrollsByStudent(student);
        for(Enroll aux:listEnrolls){
            list.add(aux.getGrade()+"@"+aux.getDateExam());
        }
        return list;
    }

    public List<String> getStudentTeachers(Student student){
        List<String> result=new ArrayList<String>();
        List<Enroll> listEnrolls=enrollRepository.getEnrollsByStudent(student);
        for(Enroll aux:listEnrolls){
            result.add(aux.getTeacher().getName()+"@"+aux.getTeacher().getCourse());
        }

        return result;
    }

    public List<Student> getTeacherStudents(Teacher teacher){
        List<Student> result=new ArrayList<Student>();
        List<Enroll> list=enrollRepository.getEnrollByTeacher(teacher);
        for(Enroll aux:list){
            result.add(aux.getStudent());
        }
        return result;
    }


    public void updateEnrollDate(Teacher teacher,String date){
        List<Enroll> list=enrollRepository.getEnrollByTeacher(teacher);
        for(Enroll aux:list){
            aux.setDateExam(date);
            enrollToBeUpdated=aux;
            this.updateEnroll();
        }
    }
    public List<String> getTeacherStudentsGrades(Teacher teacher){
        List<String> result=new ArrayList<String>();
        List<Enroll> list=enrollRepository.getEnrollByTeacher(teacher);
        for(Enroll aux:list){
            result.add(aux.getGrade()+"@"+aux.getDateExam());
        }
        return result;
    }



    public int checkIfAlreadyEnrolled(Student student, Teacher teacher){
        Enroll result=enrollRepository.getEnrollByStudentTeacher(teacher,student);
        if(result==null) return 0;
        else return 1;

    }

    public void setEnrollTobeInserted(Teacher teacher, Student student){
        enrollTobeInserted=new Enroll(teacher,student);
    }


    public void setEnrollTobeUpdated(Teacher teacher, Student student){
        enrollToBeUpdated=enrollRepository.getEnrollByStudentTeacher(teacher,student);
    }

    public void setEnrollTobeUpdatedGrade(int grade){
        enrollToBeUpdated.setGrade(grade);
    }

    public void setEnrollTobeInsertedDate(String date){
        enrollTobeInserted.setDateExam(date);
    }
}
