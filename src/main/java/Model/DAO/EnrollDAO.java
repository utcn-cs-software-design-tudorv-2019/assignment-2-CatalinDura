package Model.DAO;

import Model.Entity.Enroll;
import Model.Entity.Student;
import Model.Entity.Teacher;
import Model.Hybernate;
import Model.Repository.EnrollRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class EnrollDAO implements EnrollRepository {

    private SessionFactory sessionFactory= Hybernate.getSessionFactory();


    public EnrollDAO(){}

    public void createEnroll(Enroll enroll){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(enroll);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteEnroll(Enroll enroll){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(enroll);
        session.getTransaction().commit();
        session.close();
    }

    public void updateEnroll(Enroll enroll){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.merge(enroll);
        session.getTransaction().commit();
        session.close();
    }

    public List<Enroll> getAllEnrolls(){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Enroll.class);
        List <Enroll> results = criteria.list();
        session.close();
        return results;
    }

    public Enroll getEnrollByStudentTeacher(Teacher teacher, Student student){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Enroll.class);
        criteria.add(Restrictions.eq("student",student))
                .add(Restrictions.eq("teacher",teacher));
        if(criteria.list().size()>0) {
            Enroll result = (Enroll) criteria.list().get(0);
            session.close();
            return result;
        }
        else{
            session.close();
            return null;
        }
    }


    public List<Enroll> getEnrollsByStudent(Student student){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Enroll.class);
        criteria.add(Restrictions.eq("student",student));
        List<Enroll> result=criteria.list();
        session.close();
        return result;
    }

    public List<Enroll> getEnrollByTeacher(Teacher teacher){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Enroll.class);
        criteria.add(Restrictions.eq("teacher",teacher));
        List<Enroll> result=criteria.list();
        session.close();
        return result;
    }

}
