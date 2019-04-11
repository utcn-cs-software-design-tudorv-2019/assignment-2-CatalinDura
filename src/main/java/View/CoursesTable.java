package View;

import javax.swing.*;
import java.util.List;

public class CoursesTable extends JFrame {


    public JTable creare(List<String> materii) {
        int col = 2;
        String[] coloane = new String[col];
        String[][] matrice = new String[materii.size()][col];
        coloane[0] = "Profesor";
        coloane[1]="Materie";
        int j = 0;
        for (int i = 0; i < materii.size(); i++){
            String[] aux=materii.get(i).split("@");
            matrice[i][0]=aux[0];
            matrice[i][1]=aux[1];
        }


        JTable tabela=new JTable(matrice,coloane);
        return tabela;
    }
}

