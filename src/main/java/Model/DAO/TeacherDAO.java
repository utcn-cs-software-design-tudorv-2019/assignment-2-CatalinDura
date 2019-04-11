package Model.DAO;

import Model.Entity.Teacher;
import Model.Hybernate;
import Model.Repository.TeacherRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TeacherDAO implements TeacherRepository {

    private SessionFactory sessionFactory= Hybernate.getSessionFactory();

    public void updateTeacher(Teacher teacher){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.merge(teacher);
        session.beginTransaction().commit();
        session.close();
    }

    public List<Teacher> getAllTeachers(){
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Teacher.class);
        List <Teacher> results = criteria.list();
        session.close();
        return results;
    }

    public Teacher getTeacherByName(String name){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Teacher.class);
        criteria.add(Restrictions.like("name",name));
        Teacher result=(Teacher)criteria.list().get(0);
        session.close();
        return result;
    }

    public Teacher getTeacherByCourse(String course){
        Session session=sessionFactory.openSession();
        Criteria criteria=session.createCriteria(Teacher.class);
        criteria.add(Restrictions.like("course",course));
        Teacher result=(Teacher)criteria.list().get(0);
        session.close();
        return result;
    }
}
