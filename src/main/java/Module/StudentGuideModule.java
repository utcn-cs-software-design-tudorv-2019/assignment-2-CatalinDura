package Module;

import Model.DAO.StudentDAO;
import Model.Repository.StudentRepository;

public class StudentGuideModule  extends com.google.inject.AbstractModule{

    @Override
    protected void configure() {
        bind(StudentRepository.class).to(StudentDAO.class);
    }

}