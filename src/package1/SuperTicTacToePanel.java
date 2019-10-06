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
    private boolean failureFlag;


    private SuperTicTacToeGame game;

    public SuperTicTacToePanel() {


        JTextField rowscolsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);

        JPanel myPanel = new JPanel();

        do {
            failureFlag = false;

            myPanel.add(new JLabel("Number of Rows and Columns:"));
            myPanel.add(rowscolsField);

            myPanel.add(new JLabel("How Many Need to be in a Row to Win?:"));
            myPanel.add(inaRowField);

            myPanel.add(new JLabel("Who Goes First (Please enter X of O):"));
            myPanel.add(whosFirstField);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Tic Tac Toe - Please enter the following parameters to play!", JOptionPane.OK_CANCEL_OPTION);

            //closes the game if cancel is selected
            if (result == JOptionPane.CANCEL_OPTION) {
                System.exit(1);
            }

            // sets variables to inputs and checks for exceptions

            numRowsColsString = rowscolsField.getText();
            // catches the exception if the input doesn't match up
            try{
                numRowsCols = Integer.parseInt(numRowsColsString);
            }catch(NumberFormatException ex){ // handle your exception
            }

            numToWinString = inaRowField.getText();
            // catches the exception if the input doesn't match up
            try{
                numToWin = Integer.parseInt(numToWinString);
            }catch(NumberFormatException ex){ // handle your exception
            }

            whosFirst = whosFirstField.getText();

            if (numRowsCols > 3 && numToWin < 3) {
                failureFlag = true;
            }

            if (numRowsCols == 3 && numToWin != 3) {
                failureFlag = true;
            }

            if (numRowsCols > 15 || numRowsCols < 2) {
                failureFlag = true;
            }

            if (!(whosFirst.equals("X") || whosFirst.equals("x") || whosFirst.equals("O") || whosFirst.equals("o"))) {
                failureFlag = true;
            }

            if (numToWin > numRowsCols) {
                failureFlag = true;
            }

            if (result == JOptionPane.OK_OPTION) {

                // Clears panel after each iteration of the loop so it doesn't overflow text
                myPanel.removeAll();
                myPanel.updateUI();

                // if inputs aren't legitimate prompt user to inform them
                if (failureFlag) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid set of inputs and " +
                            "try again!");
                }
            }

        } while(failureFlag);


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