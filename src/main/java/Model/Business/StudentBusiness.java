package Model.Business;

import Model.Entity.Student;
import Model.Repository.StudentRepository;
import com.google.inject.Inject;

import java.util.List;

public class StudentBusiness {
    StudentRepository studentRepository;
    Student loggedStudent;
    Student studentToInsert;


    @Inject
    public StudentBusiness(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
        loggedStudent=new Student();
    }

    public int insertStudent(){
        studentRepository.createStudent(studentToInsert);
        return 0;
    }

    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }

    public int updateStudent(){
        studentRepository.updateStudent(loggedStudent);
        return 0;
    }

    public List<Student> getAllStudents(){
        List<Student> studentList=studentRepository.getAllStudents();
        return studentList;
    }

    public int checkPassword(String name,String password){

        Student student=studentRepository.getStudentByName(name);
        if(student.getPassword().equals(password)){
            loggedStudent=student;
            return 0;
        }
        else return 1;
    }

    public Student getStudentByName(String name){
        return studentRepository.getStudentByName(name);
    }
    public void setStudentToInsert(String name,int age,String email,String password){
        studentToInsert=new Student(name,age,email,password);
    }


    public void setNameLoggedStudent(String name){
        loggedStudent.setName(name);
    }

    public String getNameLoggedStudent(){
        return loggedStudent.getName();
    }

    public void setAgeLoggedStudent(int age){
        loggedStudent.setAge(age);
    }

    public int getAgeLoggedStudent(){
        return loggedStudent.getAge();
    }

    public void setEmailLoggedStudent(String email){
        loggedStudent.setEmail(email);
    }

    public String getEmailLoggedStudent(){
        return loggedStudent.getEmail();
    }

    public void setPasswordLoggedStudent(String password){
        loggedStudent.setPassword(password);
    }

    public String getPasswordLoggedStudent(){
        return loggedStudent.getPassword();
    }

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }
}

