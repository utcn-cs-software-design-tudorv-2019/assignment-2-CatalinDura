package Controller;

import Model.Business.*;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private LoginView login;
    private CreateView updateProfil;
    private EnrollBusiness enrollBusiness;
    private StudentBusiness studentBusiness;
    private TeacherBusiness teacherBusiness;
    private StudentView studentView;
    private EnrollView enrollView;

    public StudentController(StudentBusiness x,EnrollBusiness e,TeacherBusiness t,LoginView l){
        studentBusiness=x;
        login=l;
        enrollBusiness=e;
        teacherBusiness=t;
        studentView=new StudentView();
        studentView.profilListener(new profilListener());
        studentView.stergeProfilListener(new stergeProfilListener());
        studentView.updateProfilListener((new updateProfilListener()));
        studentView.veziNoteListener(new veziNoteListener());
        studentView.enrollListener(new enrollListener());
        studentView.logoutListener(new logoutStudentListener());
    }

    class logoutStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            studentView.setVisible(false);
            login.setVisible(true);
            login.setTextNume();
            login.setTextParola();
            login.setRadio();
        }
    }

    class enrollListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            List<String> materii=new ArrayList<String>();
            materii.addAll(teacherBusiness.getAvailableCourses());
            CoursesTable tabelMaterii=new CoursesTable();
            enrollView=new EnrollView(tabelMaterii.creare(materii));
            enrollView.enrollListener(new enrollMaterieListener());
        }
    }

    class enrollMaterieListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String materie=enrollView.getMaterie();

            if(enrollBusiness.checkIfAlreadyEnrolled(studentBusiness.getLoggedStudent(),
                    teacherBusiness.getTeacherByCourse(materie))==0) {
                enrollBusiness.setEnrollTobeInserted(teacherBusiness.getTeacherByCourse(materie),
                        studentBusiness.getLoggedStudent());
                enrollBusiness.createEnroll();
                enrollView.showMessage("Enroll reusit la materie" + materie);
                enrollView.setVisible(false);
            }
            else{
                enrollView.showMessage("Sunteti deja inscris la aceasta materie");
            }
        }
    }

    class veziNoteListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            List<String> note=new ArrayList<String>();
            note.addAll(enrollBusiness.getStudentGrades(studentBusiness.getLoggedStudent()));
            List<String> profesori=new ArrayList<String>();
            System.out.println(note.size());
            profesori.addAll(enrollBusiness.getStudentTeachers(studentBusiness.getLoggedStudent()));
            System.out.println(profesori.size());
            GradesTable rezultat=new GradesTable();
            StudentInfo afisare=new StudentInfo(rezultat.creare(note,profesori));
        }
    }

    class updateProfilListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            updateProfil=new CreateView();
            updateProfil.creareListener(new updateProfil());

        }
    }

    class updateProfil implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String nume = updateProfil.getNume();
            String varsta=updateProfil.getVarsta();
            String email = updateProfil.getEmail();
            String parola1 = updateProfil.getParola1();
            String parola2 = updateProfil.getParola2();
            if(!varsta.equals(""))
                studentBusiness.setAgeLoggedStudent(Integer.parseInt(varsta));
            if(!nume.equals(""))
                studentBusiness.setNameLoggedStudent(nume);
            if(!email.equals(""))
                studentBusiness.setEmailLoggedStudent(email);
            if(!parola1.equals("")&& parola1.equals(parola2))
                studentBusiness.setPasswordLoggedStudent(parola1);
            int verificare=studentBusiness.updateStudent();
            if(verificare==0) {
                updateProfil.showMessage("Profil actualizat!");
                updateProfil.setVisible(false);
            }
            else updateProfil.showMessage("Date de actualizare incorecte");
        }

    }

    class stergeProfilListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            enrollBusiness.deleteEnrollsOfStudent(studentBusiness.getLoggedStudent());
            studentBusiness.deleteStudent(studentBusiness.getLoggedStudent());
            studentView.showMessage("Cont sters cu succes!");
            studentView.setVisible(false);
            login.setTextParola();
            login.setVisible(true);

        }
    }

    class profilListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            ProfileTable jtabela=new ProfileTable();
            StudentInfo t=new StudentInfo(jtabela.creare(studentBusiness.getNameLoggedStudent(),
                    studentBusiness.getAgeLoggedStudent(),studentBusiness.getEmailLoggedStudent(),
                    studentBusiness.getPasswordLoggedStudent()));

        }
    }
}
