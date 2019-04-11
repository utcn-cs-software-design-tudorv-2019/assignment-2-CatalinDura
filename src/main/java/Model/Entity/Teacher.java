package Model.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @Column(name="idteacher")
    @GeneratedValue(generator="incrementor")
    @GenericGenerator(name="incrementor",strategy="increment")
    private int idteacher;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="course")
    private String course;

    @Column(name="password")
    private String password;

    public Teacher(String name, String email, String course, String password) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.password = password;
    }

    public Teacher(){}

    public int getIdteacher() {
        return idteacher;
    }

    public void setIdteacher(int idteacher) {
        this.idteacher = idteacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "idteacher=" + idteacher +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", course='" + course + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
