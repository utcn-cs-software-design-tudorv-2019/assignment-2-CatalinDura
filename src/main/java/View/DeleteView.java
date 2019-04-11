package View;
import javax.swing.*;
import java.awt.event.ActionListener;

public class DeleteView extends JFrame {
    private JButton delete=new JButton("OK");
    private JTextField name=new JTextField(5);
    public DeleteView(){
        JPanel panel=new JPanel();
        JLabel l1=new JLabel("Introduceti nume student");
        panel.add(l1);
        panel.add(name);
        panel.add(delete);
        this.setTitle("Delete student");
        this.add(panel);
        this.setSize(200,120);
        this.setVisible(true);
    }

    public String getStudentName(){
        return name.getText();
    }

    public void deleteListener(ActionListener mal){
        delete.addActionListener(mal);
    }
    public void showMessage(String s){
        JOptionPane.showMessageDialog(this,s);
    }

}
