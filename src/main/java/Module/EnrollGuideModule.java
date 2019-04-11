package Module;

import Model.DAO.EnrollDAO;
import Model.Repository.EnrollRepository;

public class EnrollGuideModule  extends com.google.inject.AbstractModule{

    @Override
    protected void configure() {
        bind(EnrollRepository.class).to(EnrollDAO.class);
    }

}