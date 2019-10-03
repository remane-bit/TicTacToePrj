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

    private int numRowsCols;
    private int numToWin;
    private String whosFirst;
    private String numRowsColsString;
    private String numToWinString;


    private SuperTicTacToeGame game;

    public SuperTicTacToePanel() {


        JTextField rowscolsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);

        JPanel myPanel = new JPanel();

        do {
            myPanel.add(new JLabel("Number of Rows and Columns:"));
            myPanel.add(rowscolsField);

            myPanel.add(new JLabel("How Many Need to be in a Row to Win?:"));
            myPanel.add(inaRowField);

            myPanel.add(new JLabel("Who Goes First (Please enter X of O):"));
            myPanel.add(whosFirstField);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                numRowsColsString = rowscolsField.getText();
                numRowsCols = Integer.parseInt(numRowsColsString);

                numToWinString = inaRowField.getText();
                numToWin = Integer.parseInt(numToWinString);

                whosFirst = whosFirstField.getText();

                System.out.println(numRowsCols + " " + numToWin + " " + whosFirst);
            }
        } while();


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