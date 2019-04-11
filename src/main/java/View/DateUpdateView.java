package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DateUpdateView extends JFrame {
    private JButton update=new JButton("update");
    private JTextField data=new JTextField(10);

    public DateUpdateView(){
        JPanel panel=new JPanel();
        panel. setLayout (new BoxLayout (panel, BoxLayout . Y_AXIS ));
        JLabel l1=new JLabel("Data:");
        JPanel p1=new JPanel();
        p1.add(l1);
        p1.add(data);
        panel.add(p1);
        panel.add(update);
        this.add(panel);
        this.setSize(100,150);
        this.setVisible(true);
    }

    public String getDate(){
        return data.getText();
    }
    public void updateDateListener(ActionListener mal){update.addActionListener(mal);}
}
