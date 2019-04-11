package View;
import javax.swing.*;

public class StudentInfo extends JFrame{
    private JTable tabel;
    public StudentInfo(JTable t){
        tabel=t;
        JScrollPane scroll=new JScrollPane(tabel);
        this.setTitle("Students table");
        this.add(scroll);
        this.setSize(450,150);
        this.setVisible(true);
    }
}

