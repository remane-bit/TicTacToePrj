package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SuperTicTacToePanel extends JPanel {

    /** Game object **/
    private SuperTicTacToeGame game;

    private JButton[][] board = new JButton[15][15];
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


    public SuperTicTacToePanel() {

        game = new SuperTicTacToeGame();

        JTextField rowscolsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);
        xIcon = new ImageIcon("x.jpg");
        oIcon = new ImageIcon("o.jpg");

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

            inputParameters();

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


        setupGUI();
    }


    /***********************************************************************
     * This method will set up all the buttons on the GUI
     **********************************************************************/
    public void setupGUI() {

        /** Sets the manager for how components are going to be displayed **/
        setLayout(new GridBagLayout());

        /** Sets the components to be laid out like a grid **/
        GridBagConstraints position = new GridBagConstraints();

        /** Makes all components fill the entire cell in a GridBagLayout **/
        position.fill = GridBagConstraints.HORIZONTAL;

        ButtonListener set = new ButtonListener();

        ButtonGroup group = new ButtonGroup();

        /** Board buttons. Letters rep. column position, numbers rep. row position **/

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                System.out.println(row + " " + col);

                board[row][col] = new JButton("");
                board[row][col].setPreferredSize(new Dimension(100,100));
                group.add(board[row][col]);
                position.gridx = row;
                position.gridy = col;
                add(board[row][col], position);
                board[row][col].addActionListener(set);

            }
        }

       // quitButton = new JButton("Quit");
       //quitButton.addActionListener(this);

    }

    public void inputParameters() {
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
    }


    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if(source == quitButton) {
                System.exit(1);
            }

           // if(source == board[row][col]) {            }
        }

    }

}

