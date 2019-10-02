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

    private SuperTicTacToeGame game;

    public SuperTicTacToePanel() {

    }

}
