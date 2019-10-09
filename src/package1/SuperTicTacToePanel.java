package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SuperTicTacToePanel extends JPanel {

    /** Game object **/
    private SuperTicTacToeGame game;

    private GameStatus Status;
    private JButton[][] Jboard = new JButton[15][15];
    private Cell[][] iBoard;
    private JButton quitButton , undoButton, AIturnButton, resetButton;
    private JLabel spacing;
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

        game.setNumberOfRowsCols(numRowsCols);
        game.setConnectionsToWin(numToWin);
        game.setStartsFirst(whosFirst);
        game.newGame();
        displayBoard();
       }


    /***********************************************************************
     * This method will set up all the buttons on the GUI
     **********************************************************************/

    private void displayBoard() {
        System.out.println("New game created");

        /** Sets the manager for how components are going to be displayed **/
        setLayout(new GridBagLayout());

        /** Sets the components to be laid out like a grid **/
        GridBagConstraints position = new GridBagConstraints();

        /** Makes all components fill the entire cell in a GridBagLayout **/
        position.fill = GridBagConstraints.HORIZONTAL;

        ButtonListener set = new ButtonListener();

        ButtonGroup group = new ButtonGroup();

        /** Add comments **/
        quitButton = new JButton("Quit");
        undoButton = new JButton("Undo");
        AIturnButton = new JButton("AI Turn");
        resetButton = new JButton("Reset");
        spacing = new JLabel("           ");

        group.add(quitButton);
        group.add(undoButton);
        group.add(AIturnButton);
        group.add(resetButton);

        quitButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 0;
        add(quitButton, position);
        quitButton.addActionListener(set);

        undoButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 1;
        add(undoButton, position);
        undoButton.addActionListener(set);

        AIturnButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 2;
        add(AIturnButton, position);
        AIturnButton.addActionListener(set);

        resetButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 3;
        add(resetButton, position);
        resetButton.addActionListener(set);

        /** Board buttons. Letters rep. column position, numbers rep. row position **/

        for(int row = 0; row < numRowsCols; row++) {
            for(int col = 0; col < numRowsCols; col++) {
                //System.out.println(row + " " + col);

                int size = 72; // Make if statements to adjust as size increases
                int sizeOfCell = 100;
                Font f = new Font("Dialog", Font.PLAIN, size);
                Jboard[row][col] = new JButton("");
                Jboard[row][col].setPreferredSize(new Dimension(sizeOfCell,sizeOfCell));
                group.add(Jboard[row][col]);
                position.gridx = row;
                position.gridy = col + 4;
                add(Jboard[row][col], position);
                Jboard[row][col].addActionListener(set);
                Jboard[row][col].setFont(f);

            }
        }
    }


    /**********************
     * Action Listener
     *********************/
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if(source == quitButton) {
                System.exit(1);
            }

            if(source == undoButton) {
                game.undoPastMove();
            }

            if(source == AIturnButton) {
                game.randomAI();
                updateBoard();
                reEnableEmpty();
                AIturnButton.setEnabled(false);
            }

            if(source == resetButton) {
                game.newGame();
                reEnableEmpty();
                AIturnButton.setEnabled(false);
            }

            /** If any of the tic tac toe buttons are selected, do the following **/
            for (int Row = 0; Row < numRowsCols; Row++) {
                for(int Col = 0; Col < numRowsCols; Col++) {
                    if (Jboard[Row][Col] == source) {
                        System.out.println("You clicked at " + Row + "," + Col);
                        game.select(Row,Col);
                        game.updatePastMoves(Row, Col);

                        checkGameState(Row, Col);

                        updateBoard();
                        System.out.println("There is now an X at " + Row + "," + Col);
                        Jboard[Row][Col].setEnabled(false);
                        AIturnButton.setEnabled(true);
                    }
                }
            }


            /** If there are no more moves in the game, disable the AI's turn button **/
            if(game.getCurrentTurn() == (numRowsCols * numRowsCols)) {
                AIturnButton.setEnabled(false);
                //Prompt a window stating a cats game
            }
            // if(source == board[row][col]) {            }
            //wholeBoardCheck();
        }

    }

    /***********************************************
     * Conditionals for the iniitial parameters.
     */
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






    private void updateBoard() {
        iBoard = game.getBoard();
        for (int Row = 0; Row < numRowsCols; Row++) {
            for (int Col = 0; Col < numRowsCols; Col++) {
                if( iBoard[Row][Col] == Cell.O) {
                    Jboard[Row][Col].setText("O");
                    Jboard[Row][Col].setEnabled(false);
                }

                if( iBoard[Row][Col] == Cell.X) {
                    Jboard[Row][Col].setText("X");
                    Jboard[Row][Col].setEnabled(false);
                }

                if( iBoard[Row][Col] == Cell.EMPTY) {
                    Jboard[Row][Col].setText("");
                    Jboard[Row][Col].setEnabled(false);
                }

            }
        }
    }

    private void reEnableEmpty() {
        iBoard = game.getBoard();
        for (int Row = 0; Row < numRowsCols; Row++) {
            for (int Col = 0; Col < numRowsCols; Col++) {

                if( iBoard[Row][Col] == Cell.EMPTY) {
                    Jboard[Row][Col].setText("");
                    Jboard[Row][Col].setEnabled(true);
                }

            }
        }
    }

    private void checkGameState(int row, int col) {
       if(game.checkForX(row, col) == GameStatus.X_WON) System.out.println("Sicko mode");
       if(game.checkForO(row, col) == GameStatus.O_WON) System.out.println("You lose");
       if(game.checkForCats(row,col) == GameStatus.CATS) System.out.println("Tie Game");
    }



    /** This method is merely a test. Can be deleted out of the final code **/
    private void wholeBoardCheck() {
        for (int Row = 0; Row < numRowsCols; Row++) {
            for (int Col = 0; Col < numRowsCols; Col++) {
                checkGameState(Row,Col);
            }
        }
    }

}

