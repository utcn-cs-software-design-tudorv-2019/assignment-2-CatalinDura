package Module;

import Model.DAO.TeacherDAO;
import Model.Repository.TeacherRepository;

public class TeacherGuideModule  extends com.google.inject.AbstractModule{

    @Override
    protected void configure() {
        bind(TeacherRepository.class).to(TeacherDAO.class);
    }

}