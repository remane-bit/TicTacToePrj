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

        JTextField rowsField = new JTextField(2);
        JTextField colsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Number of Rows:"));
        myPanel.add(rowsField);

        myPanel.add(new JLabel("Number of Columns:"));
        myPanel.add(colsField);

        myPanel.add(new JLabel("How Many Need to be in a Row to Win?:"));
        myPanel.add(inaRowField);

        myPanel.add(new JLabel("Who Goes First (Please enter X of O):"));
        myPanel.add(whosFirstField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);


        xIcon = new ImageIcon("x.jpg");
        oIcon = new ImageIcon("o.jpg");


        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == quitButton) {
            System.exit(1);
        }
    }
}