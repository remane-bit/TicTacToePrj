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

    /** The tic tac toe board. Max size is 15 x 15 **/
    private JButton[][] Jboard = new JButton[15][15];

    /** The cells that make up the board. Will have X,O, or EMPTY states **/
    private Cell[][] iBoard;

    /** Action Buttons for the GUI **/
    private JButton quitButton , undoButton, AIturnButton, resetButton;

    /** Data received from user input from the initial popup screen **/
    private int numRowsCols;
    private int numToWin;
    private String whosFirst;

    /** User input converted to a string **/
    private String numRowsColsString;
    private String numToWinString;

    /** Flag that determines if parameters are correct **/
    private boolean failureFlag;


    public SuperTicTacToePanel() {
        /** Instantiation of the game **/
        game = new SuperTicTacToeGame();

        /** Popup window the user will see, prompting them to fill data in **/
        promptWindow();

        /** This sends the data received to the game object, which sends it to the Game.java **/
        game.setNumberOfRowsCols(numRowsCols);
        game.setConnectionsToWin(numToWin);
        game.setStartsFirst(whosFirst);

        /** Creates the new game **/
        game.newGame();
        System.out.println("New game created");

        /** Displays the board on the GUI **/
        displayBoard();
       }


    /***********************************************************************
     * This method will set up all the buttons on the GUI
     **********************************************************************/

    private void displayBoard() {
        /** Sets the manager for how components are going to be displayed **/
        setLayout(new GridBagLayout());

        /** Sets the components to be laid out like a grid **/
        GridBagConstraints position = new GridBagConstraints();

        /** Makes all components fill the entire cell in a GridBagLayout **/
        position.fill = GridBagConstraints.HORIZONTAL;

        /** Creates a new ButtonListener that extends actionListener **/
        ButtonListener set = new ButtonListener();

        /** Instantiataion of buttons **/
        ButtonGroup group = new ButtonGroup();

        /** Declaration of the buttons that do not make up the board **/
        quitButton = new JButton("Quit");
        undoButton = new JButton("Undo");
        AIturnButton = new JButton("AI Turn");
        resetButton = new JButton("New Game");

        /** Declaration of the buttons **/
        group.add(quitButton);
        group.add(undoButton);
        group.add(AIturnButton);
        group.add(resetButton);

        /** Quit Button **/
        quitButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 0;
        add(quitButton, position);
        quitButton.addActionListener(set);

        /** Undo Button **/
        undoButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 1;
        add(undoButton, position);
        undoButton.addActionListener(set);

        /** AI's Turn Button **/
        AIturnButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 2;
        add(AIturnButton, position);
        AIturnButton.addActionListener(set);

        /** Reset Button **/
        resetButton.setPreferredSize(new Dimension(100,30));
        position.gridx = 0;
        position.gridy = 3;
        add(resetButton, position);
        resetButton.addActionListener(set);

        int size = 72; // Make if statements to adjust as size increases
        int sizeOfCell = 100;

        /** Creation, Instantiation, and Declaration of all the buttons that make the tic tac toe board **/
        for(int row = 0; row < numRowsCols; row++) {
            for(int col = 0; col < numRowsCols; col++) {

                /** If the grid is 5x5 of less, have large font and cells **/
                if(numRowsCols < 6) {
                    size = 80;
                    sizeOfCell = 120;
                }

                /** IF the grid is between 5x5 and 10x10, have medium font and cells **/
                else if(numRowsCols >= 6 && numRowsCols < 11) {
                    size = 40;
                    sizeOfCell = 70;
                }

                /** IF the grid is between 10x10 and 15x15, have small font and cells **/
                else if(numRowsCols >= 11 && numRowsCols < 16) {
                    size = 15;
                    sizeOfCell = 50;
                }

                Font f = new Font("Dialog", Font.PLAIN, size);
                Jboard[row][col] = new JButton("");
                Jboard[row][col].setPreferredSize(new Dimension(sizeOfCell,sizeOfCell));
                group.add(Jboard[row][col]);
                position.gridx = row + 1;
                position.gridy = col + 4;
                add(Jboard[row][col], position);
                Jboard[row][col].addActionListener(set);
                Jboard[row][col].setFont(f);

            }
        }

        /** If AI is going first, lock the board so the user doesn't make their choice for them! **/
        char First = whosFirst.charAt(0);
        if(First == 'O' || First == 'o') {
            setBoardLocked();
        }

        if(First == 'X' || First == 'x') {
            AIturnButton.setEnabled(false);
        }

    }


    private class ButtonListener implements ActionListener {


        /**********************************************************************
         * This method will execute the actions if the button is pressed
         * @param e
         ********************************************************************/
        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            /** If quit button is pressed, quit the program **/
            if(source == quitButton) {
                System.exit(1);
            }

            /** If undo button is pressed, undo the past move and update the board accordingly **/
            if(source == undoButton) {
                //if users turn, do this:

                game.undoPastMove();
                game.setCurrentTurn(game.getCurrentTurn() - 1);
                updateBoard();

                AIturnButton.setEnabled(true);
                reEnableEmpty();

                // if AI's turn disable the GUI buttons and reenable the AI button
            }

            /** Once the user selects a position on the board, press the AI's turn button to continue the game at your
             * own pace **/
            if(source == AIturnButton) {
                game.randomAI();
                updateBoard();
                reEnableEmpty();
                AIturnButton.setEnabled(false);
                //To have AI win, we need to get the randVal1 and 2 from the AI method
                checkGameState(game.getAImoveX(), game.getAImoveY());
            }

            /** If the reset button is pressed, start a new game **/
            if(source == resetButton) {
                char First = whosFirst.charAt(0);

               // promptWindow();
               // game.setNumberOfRowsCols(numRowsCols);
               // game.setConnectionsToWin(numToWin);
               // game.setStartsFirst(whosFirst);

                /** If the user chose to go first, they will go first if they chose it inititally **/
                if(First == 'X' || First == 'x'){
                    game.newGame();
                    //displayBoard();
                    reEnableEmpty();
                    AIturnButton.setEnabled(false);
                }

                /** If the AI went first, they will continue to go as the game progresses **/
                else if (First == 'O' || First == 'o') {
                    game.newGame();
                    //displayBoard();
                    reEnableEmpty();
                    AIturnButton.setEnabled(true);
                    setBoardLocked();
                }
            }

            /** If any of the tic tac toe buttons are selected, do the following **/
            for (int Row = 0; Row < numRowsCols; Row++) {
                for(int Col = 0; Col < numRowsCols; Col++) {
                    if (Jboard[Row][Col] == source) {
                        game.select(Row,Col);
                        game.updatePastMoves(Row, Col);
                        updateBoard();
                        AIturnButton.setEnabled(true);
                        checkGameState(Row, Col);
                        setBoardLocked();
                        Jboard[Row][Col].setEnabled(false);
                    }
                }
            }


            /** If there are no more moves in the game, disable the AI's turn button **/
            if(game.getCurrentTurn() == (numRowsCols * numRowsCols)) {
                AIturnButton.setEnabled(false);
            }
        }

    }

    /**************************************************************************
     * This method contains the conditionals for the iniitial parameters.
     **************************************************************************/
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

    /**************************************************************************
     * This method updates the board, by checking every single cell, and outputting
     * the output (X, O, nothing)
     **************************************************************************/
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

    /**************************************************************************
     * This method contains the conditionals for the iniitial parameters.
     **************************************************************************/
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

    /**************************************************************************
     * Disables any buttons from being pressed on the tic tac toe board. Board
     * is locked when somebody wins a game, or if it is no longer the user's
     * turn
     **************************************************************************/
    private void setBoardLocked() {
        iBoard = game.getBoard();
        for (int Row = 0; Row < numRowsCols; Row++) {
            for (int Col = 0; Col < numRowsCols; Col++) {
                Jboard[Row][Col].setEnabled(false);
            }
        }
    }

    /**************************************************************************
     * Checks to see if any Game Statuses were met. If so, display a window
     * with the according message.
     **************************************************************************/
    private void checkGameState(int row, int col) {
        System.out.println("Checking for X winner");
        if(game.checkForX(row, col) == GameStatus.X_WON) {
            JOptionPane.showMessageDialog(null, "X won the game!");
            AIturnButton.setEnabled(false);
            return;
        }

        System.out.println("Checking for O winner");
        if(game.checkForO(row, col) == GameStatus.O_WON) {
            JOptionPane.showMessageDialog(null, "The AI won the game!");
            setBoardLocked();
            return;
        }

        System.out.println("Checking for tie game");
        if(game.checkForCats(row,col) == GameStatus.CATS) {
            JOptionPane.showMessageDialog(null, "Tie game!");
            return;
        }
    }

    /*************************************************************
     * Popup window the user will see, prompting them to fill
     * data in
     *************************************************************/
    private void promptWindow() {
        JTextField rowscolsField = new JTextField(2);
        JTextField inaRowField = new JTextField(2);
        JTextField whosFirstField = new JTextField(1);
        JPanel myPanel = new JPanel();

        /** This do-while loop will have the window popup over and over again if the user does not put the
         * proper input in **/
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
    }
}

