package package1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;

public class SuperTicTacToePanel extends JPanel implements ActionListener{

    private JButton[][] board;
    private Cell[][] iBoard;
    private JButton quitButton;
    private ImageIcon xIcon;
    private ImageIcon oIcon;
    private ImageIcon emptyIcon;

    private SuperTicTacToeGame game;

    public SuperTicTacToePanel() {
        xIcon = new ImageIcon("x.jpg");
        oIcon = new ImageIcon("o.jpg");

        String startsFirst = JOptionPane.showInputDialog(this, "Who starts first? (Insert X or O)");
        game.whoStartsFirst(startsFirst);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == quitButton) {
            System.exit(1);
        }
    }
}
