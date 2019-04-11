package Controller;

import Model.Business.EnrollBusiness;
import Model.Business.StudentBusiness;
import Model.Business.TeacherBusiness;
import Module.EnrollGuideModule;
import Module.StudentGuideModule;
import Module.TeacherGuideModule;
import View.CreateView;
import View.LoginView;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView login;
    private StudentBusiness studentBusiness;
    private TeacherBusiness teacherBusiness;
    private EnrollBusiness enrollBusiness;
    private CreateView createView;
    public LoginController(){
        Injector studentGuice = Guice.createInjector(new StudentGuideModule());
        studentBusiness = studentGuice.getInstance(StudentBusiness.class);

        Injector teacherGuice = Guice.createInjector(new TeacherGuideModule());
        teacherBusiness = teacherGuice.getInstance(TeacherBusiness.class);

        Injector enrollGuice = Guice.createInjector(new EnrollGuideModule());
        enrollBusiness = enrollGuice.getInstance(EnrollBusiness.class);

        login=new LoginView();
        login.loginActionListener(new LoginListener());
        login.createActionListener(new CreateListener());
    }

    class LoginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String nume = login.getNume();
            String parola = login.getParola();
            String optiune = login.getOptiune();
            if (optiune.equals("")) {
                login.showMessage("nu ati selectat Profesor sau Student!");
            } else {
                if (optiune.equals("Student")) {
                    int verificare = studentBusiness.checkPassword(nume,parola);
                    if (verificare==0) {
                        login.showMessage("Login ca student!");
                        login.setVisible(false);
                      StudentController studentController=new StudentController(studentBusiness,enrollBusiness,teacherBusiness,login);
                    } else {
                        login.showMessage("Eroare la autentificare");
                        login.setTextParola();
                    }
                } else {
                    String verificare = teacherBusiness.getTeacherPasswordByName(nume);
                    if (verificare.equals(parola)) {
                        teacherBusiness.setLoggedTeacher(nume);
                        login.showMessage("Login ca profesor!");
                        TeacherController teacherView=new TeacherController(teacherBusiness,studentBusiness,enrollBusiness,login);
                        login.setVisible(false);
                    } else {
                        login.showMessage("Eroare la autentificare");
                        login.setTextParola();
                    }
                }
            }
        }
    }
    class CreateListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {
            createView=new CreateView();
            createView.creareListener(new CreareStudentListener());
        }
    }

    class CreareStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String i=createView.getId();
            int id=Integer.parseInt(i);
            String nume = createView.getNume();
            int varsta = Integer.parseInt(createView.getVarsta());
            String email = createView.getEmail();
            String parola1 = createView.getParola1();
            String parola2 = createView.getParola2();
            if (parola1.equals(parola2)) {
                studentBusiness.setStudentToInsert(nume,varsta,email,parola1);
                int verificare = studentBusiness.insertStudent();
                if (verificare == 0){
                    createView.showMessage("Cont creat cu succes!");
                    createView.setVisible(false);
                }
                else createView.showMessage("Eroare creare cont!");

            }
        }
    }

    public static void main(String args[]){
        LoginController x=new LoginController();
    }
}
