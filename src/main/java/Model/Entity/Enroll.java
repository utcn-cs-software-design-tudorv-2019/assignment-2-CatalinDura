package Model.Entity;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="enroll")
public class Enroll {
    @Id
    @Column(name="idenroll")
    @GeneratedValue(generator="incrementor")
    @GenericGenerator(name="incrementor",strategy="increment")
    private int idenroll;

    @ManyToOne
    @JoinColumn(name = "teacher")
    @NotNull
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student")
    @NotNull
    private Student student;

    @Column(name="grade")
    private int grade;

    @Column(name="dateExam")
    private String dateExam;

    public Enroll(Teacher teacher, Student student, int grade, String dateExam) {
        this.teacher = teacher;
        this.student = student;
        this.grade = grade;
        this.dateExam = dateExam;
    }

    public Enroll(Teacher teacher, Student student) {
        this.teacher = teacher;
        this.student = student;
        this.grade = 0;
        this.dateExam ="-";
    }


    public Enroll(){}

    public int getIdenroll() {
        return idenroll;
    }

    public void setIdenroll(int idenroll) {
        this.idenroll = idenroll;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDateExam() {
        return dateExam;
    }

    public void setDateExam(String dateExam) {
        this.dateExam = dateExam;
    }

    @Override
    public String toString() {
        return "Enroll{" +
                "idenroll=" + idenroll +
                ", teacher=" + teacher +
                ", student=" + student +
                ", grade=" + grade +
                ", dateExam='" + dateExam + '\'' +
                '}';
    }
}
