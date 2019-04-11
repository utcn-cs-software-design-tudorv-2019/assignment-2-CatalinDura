package Controller;

import Model.Business.EnrollBusiness;
import Model.Business.StudentBusiness;
import Model.Business.TeacherBusiness;
import View.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TeacherController {
    private TeacherView teacherView;
    private LoginView login;
    private GradeDateExamView updateGradeDate;
    private EnrollBusiness enrollBusiness;
    private StudentBusiness studentBusiness;
    private TeacherBusiness teacherBusiness;
    private DeleteView deleteView;
    private TeacherUpdateView teacherUpdateView;
    private DateUpdateView dateUpdateView;

    public TeacherController(TeacherBusiness t,StudentBusiness s,EnrollBusiness e,LoginView l){
        teacherBusiness=t;
        studentBusiness=s;
        enrollBusiness=e;
        login=l;
        teacherView=new TeacherView();
        teacherView.listListener(new ListListener());
        teacherView.deleteNota(new deleteNota());
        teacherView.creareNota(new creareNota());
        teacherView.listaStudentiMaterieListener(new ListaStudentiMaterie());
        teacherView.logoutTeacher(new logoutTeacherListener());
        teacherView.teacherUpdate(new updateTeacherListener());
        teacherView.updateDateListener(new updateDateListener());
    }

    class updateDateListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            dateUpdateView=new DateUpdateView();
            dateUpdateView.updateDateListener(new UpdateDate());
        }

    }

    class UpdateDate implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String date=dateUpdateView.getDate();
            enrollBusiness.updateEnrollDate(teacherBusiness.getLoggedTeacher(),date);
            dateUpdateView.setVisible(false);
        }
    }



    class updateTeacherListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            teacherUpdateView=new TeacherUpdateView();
            teacherUpdateView.creareListener(new UpdateTeacher());
        }

    }

    class UpdateTeacher implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String nume = teacherUpdateView.getNume();
            String varsta=teacherUpdateView.getVarsta();
            String email = teacherUpdateView.getEmail();
            String parola1 = teacherUpdateView.getParola1();
            String parola2 = teacherUpdateView.getParola2();
            if(!nume.equals(""))
                teacherBusiness.setLoggedTeacherName(nume);
            if(!email.equals(""))
                teacherBusiness.setLoggedTeacherEmail(email);
            if(!parola1.equals("")&& parola1.equals(parola2))
                teacherBusiness.setLoggedTeacherPassword(parola1);
            teacherBusiness.updateTeacher();
            teacherUpdateView.setVisible(false);
        }
    }


    class logoutTeacherListener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            teacherView.setVisible(false);
            login.setTextParola();
            login.setTextNume();
            login.setRadio();
            login.setVisible(true);
        }
    }


    class ListaStudentiMaterie implements ActionListener{

        public void actionPerformed(ActionEvent e){
            List<Object> listaStudenti=new ArrayList<Object>();
            listaStudenti.addAll(enrollBusiness.getTeacherStudents(teacherBusiness.getLoggedTeacher()));
            List<String> note=new ArrayList<String>();
            note.addAll(enrollBusiness.getTeacherStudentsGrades(teacherBusiness.getLoggedTeacher()));
            StudentsCourseTable jtabela=new StudentsCourseTable();
            StudentInfo t=new StudentInfo(jtabela.creare(listaStudenti,note));

        }
    }


    class creareNota implements ActionListener{

        public void actionPerformed(ActionEvent e){
            updateGradeDate=new GradeDateExamView();
            updateGradeDate.updateListener(new updateNotaListener());
        }
    }


    class updateNotaListener implements ActionListener{


        public void actionPerformed(ActionEvent e){
            String nameStudent=updateGradeDate.getNameStudent();
            int nota=Integer.parseInt(updateGradeDate.getNota());
            enrollBusiness.setEnrollTobeUpdated(teacherBusiness.getLoggedTeacher(),
                    studentBusiness.getStudentByName(nameStudent));
            enrollBusiness.setEnrollTobeUpdatedGrade(nota);
            int verificare=enrollBusiness.updateEnroll();
            if (verificare==0) {
                updateGradeDate.setVisible(false);
                updateGradeDate.showMessage("Nota actualizata");
            }
            else updateGradeDate.showMessage("Nota incorecta");
        }
    }


    class deleteNota implements ActionListener{

        public void actionPerformed(ActionEvent e){
            deleteView=new DeleteView();
            deleteView.deleteListener(new deleteListener());
        }
    }
    class deleteListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String name=deleteView.getStudentName();
            enrollBusiness.setEnrollTobeUpdated(teacherBusiness.getLoggedTeacher(),studentBusiness.getStudentByName(name));
            enrollBusiness.setEnrollTobeUpdatedGrade(0);
            int verificare=(enrollBusiness.updateEnroll()) ;
            if (verificare==0){
                deleteView.showMessage("Stergere realizata cu succes!");
                deleteView.setVisible(false);
            }
            else{
                deleteView.showMessage("studentul nu a fost gasit");
                deleteView.setVisible(false);
            }
        }
    }

    class ListListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            List<Object> listaStudenti=new ArrayList<Object>();
            listaStudenti.addAll(studentBusiness.getAllStudents());
            StudentsTable jtabela=new StudentsTable();
            StudentInfo t=new StudentInfo(jtabela.creare(listaStudenti));
        }
    }
}
