package package1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;

public class SuperTicTacToePanel extends JPanel {

    private JButton[][] board;
    private Cell[][] iBoard;
    private JButton quitButton;
    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private ImageIcon emptyIcon;

    JTextField rowsField = new JTextField(2);
    JTextField colsField = new JTextField(2);
    JTextField matchField = new JTextField(2);
    JTextField whosFirstField = new JTextField(1);



    private SuperTicTacToeGame game;


    public SuperTicTacToePanel() {
        //String startsFirst = JOptionPane.showInputDialog(this, "Who starts first? (Insert X or O)");
        //game.whoStartsFirst(startsFirst);

       // int rows = JOptionPane;
    }

}
