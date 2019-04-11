package View;
import javax.swing.*;

public class ProfileTable extends JFrame {

    public JTable creare(String nume,int varsta,String email,String parola) {
        int col = 4;
        String[] coloane = new String[col];
        String[][] matrice = new String[1][col];
        coloane[0]="nume";
        coloane[1]="varsta";
        coloane[2]="email";
        coloane[3]="parola";
        matrice[0][0]=nume;
        matrice[0][1]=""+varsta;
        matrice[0][2]=email;
        matrice[0][3]=parola;


        JTable tabela=new JTable(matrice,coloane);
        return tabela;
    }
}

