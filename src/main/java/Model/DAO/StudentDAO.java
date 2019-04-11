package Model.DAO;

import Model.Entity.Student;
import Model.Hybernate;
import Model.Repository.StudentRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentDAO implements StudentRepository {

    private SessionFactory sessionFactory= Hybernate.getSessionFactory();

    public void createStudent(Student student){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteStudent(Student student){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    public void updateStudent(Student student){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();
        session.close();
    }

    public List<Student> getAllStudents(){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Student.class);
        List <Student> results = criteria.list();
        session.close();
        return results;
    }

    public Student getStudentByName(String name){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Student.class);
        criteria.add(Restrictions.like("name",name));
        Student result=(Student)criteria.list().get(0);
        session.close();
        return result;
    }

}
