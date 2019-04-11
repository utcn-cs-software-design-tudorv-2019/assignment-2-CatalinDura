import Model.Business.EnrollBusiness;
import Model.Business.StudentBusiness;
import Model.Business.TeacherBusiness;
import Model.DAO.EnrollDAO;
import Model.DAO.StudentDAO;
import Model.DAO.TeacherDAO;
import Model.Entity.Student;
import Model.Repository.EnrollRepository;
import Model.Repository.StudentRepository;
import Model.Repository.TeacherRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;


public class EnrollTest {
    EnrollBusiness enrollBusiness=null;
    EnrollRepository enrollRepository=mock(EnrollDAO.class);

    StudentBusiness studentBusiness=null;
    StudentRepository studentRepository=new StudentDAO();

    TeacherBusiness teacherBusiness=null;


    TeacherRepository teacherRepository=new TeacherDAO();

    @Before
    public void setUp(){
        enrollBusiness=new EnrollBusiness(enrollRepository);
        studentBusiness=new StudentBusiness(studentRepository);
        teacherBusiness=new TeacherBusiness(teacherRepository);
        studentBusiness.setStudentToInsert("Alin",33,"alin@yahoo.com","333");
    }

    @Test

    public void testAccountStudent(){
        String name="cata";
        String password="123";
        Student student=studentBusiness.getStudentByName("cata");
        String passwordDataBase=student.getPassword();
        assertEquals(password,passwordDataBase);
    }

    @Test
    public void testAccountTeacher(){
        String name="mihai";
        String password="111";
        String passwordDataBase=teacherBusiness.getTeacherPasswordByName("mihai");
        assertEquals(password,passwordDataBase);
    }

    @Test

    public void testCreateStudent(){
        int length=studentBusiness.getAllStudents().size();
        studentBusiness.insertStudent();
        assertEquals(length+1,studentBusiness.getAllStudents().size());
    }


    @Test

    public void testUpdateStudent(){
        studentBusiness.setLoggedStudent(studentBusiness.getStudentByName("Alin"));
        studentBusiness.setAgeLoggedStudent(30);
        studentBusiness.updateStudent();
        Student student=studentBusiness.getStudentByName("Alin");
        assertEquals(student.getAge(),studentBusiness.getLoggedStudent().getAge());
    }

    @Test
    public void testdeleteStudent(){
        Student delete= studentBusiness.getStudentByName("Alin");
        int length=studentBusiness.getAllStudents().size();
        studentBusiness.deleteStudent(delete);
        assertEquals(length-1,studentBusiness.getAllStudents().size());
    }



}
