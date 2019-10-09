package package1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


/************************************************
 * A *Super* tic tac toe game! The user chooses how
 * big they want their board to be (Min 3x3, max 15x15)
 * and can play against the computer!
 *
 * HOW TO PLAY:
 * A window will prompt the user to put in the board size,
 * the amount of X's or O's needed to be in a row to win,
 * and if the user or AI will go first.
 *
 * If the user opted to go first, they can click anywhere
 * on the board to make their move. If they don't like it,
 * they may undo.
 *
 * Once the user finalizes their move, the user MUST
 * click on the AI turn button to have the computer make
 * their move.
 *
 * The game will progress until the user wins, computer
 * wins, or there is a draw.
 *
 * @author Max Ziegler
 * @author Remy Merriman
 * @version Fall 2019
 ***************************************************/


public class SuperTicTacToeGame {
    public Cell[][] board;
    private GameStatus status;
    private int currentTurn;
    private int numberOfRowsCols;
    private int connectionsToWin;
    private String startsFirst;

    private ArrayList<Point> pastMoves = new ArrayList<Point>();

    /************************************************************
     * Default constructor for the SuperTicTacToeGame. All it does
     * is start a new game.
     ************************************************************/
    public SuperTicTacToeGame() {
        newGame();
    }

    /**************************************************************
     * This method sets the boad up to have all the cells be empty.
     * The current turn is zero until somebody goes.
     *************************************************************/
    public void newGame() {
        status = GameStatus.IN_PROGRESS;
        System.out.println("New game being made");
        currentTurn = 0;

        System.out.println("Num Rows & Cols: " + numberOfRowsCols);

        board = new Cell[numberOfRowsCols][numberOfRowsCols];

        for (int row = 0; row < numberOfRowsCols; row++) {
            for (int col = 0; col < numberOfRowsCols; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
    }

    /**************************************************************
     * The amount of connections need to win getter
     *************************************************************/
    public int getConnectionsToWin() {
        return connectionsToWin;
    }

    /**************************************************************
     * Game status setter
     * @param status
     *************************************************************/
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**************************************************************
     * Game status getter
     *************************************************************/
    public GameStatus getStatus() {
        return status;
    }

    /**************************************************************
     * The setter for who's current turn it is
     * @param currentTurn
     *************************************************************/
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**************************************************************
     * The getter for who's current turn it is
     *************************************************************/
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**************************************************************
     * The number of connections to win setter
     * @param connectionsToWin
     *************************************************************/
    public void setConnectionsToWin(int connectionsToWin) {
        this.connectionsToWin = connectionsToWin;
    }

    /**************************************************************
     * Who starts first setter
     * @param startsFirst
     *************************************************************/
    public void setStartsFirst(String startsFirst) {
        this.startsFirst = startsFirst;
    }

    /**************************************************************
     * Number of rows and columns setter
     * @param numberOfRowsCols
     *************************************************************/
    public void setNumberOfRowsCols(int numberOfRowsCols) {
        this.numberOfRowsCols = numberOfRowsCols;
    }

    /**************************************************************
     * This method determines who's turn it is.
     * @param row
     * @param col
     *************************************************************/
    public void select(int row, int col) {

        char first = startsFirst.charAt(0);

        if(first == 'X' || first == 'x') {
            if (currentTurn % 2 == 0) {
                board[row][col] = Cell.X;
                currentTurn++;
            } else {
                board[row][col] = Cell.O;
                currentTurn++;
            }
        }

        else if(first == 'O' || first == 'o') {
            if (currentTurn % 2 == 1) {
                board[row][col] = Cell.X;
                currentTurn++;
            } else {
                board[row][col] = Cell.O;
                currentTurn++;
            }
        }
    }

    /**************************************************************
     * This method checks to see if the user has won. This is
     * used after each turn.
     * @param row
     * @param col
     * @return GameStatus.X_WON
     * @return GameStatus.IN_PROGRESS
     *************************************************************/
    public GameStatus checkForX(int row, int col) {
        boolean checkWinner = false;

        // check for row win
        int rowCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[row][i] == Cell.X)
                rowCounter++;
            if(rowCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        //check for col win
        int colCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[i][col] == Cell.X)
                colCounter++;
            if(colCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        //check for a diagonal win
        int diagCounter = 0;
        if(row == col){
            for(int i = 0; i < numberOfRowsCols; i++){
                if(board[i][i] == Cell.X)
                    diagCounter++;
                if(diagCounter == connectionsToWin){
                    checkWinner = true;
                }
            }
        }

        //check for a reverse diagonal win
        int revDiagCounter = 0;
        if(row + col == numberOfRowsCols - 1){
            for(int i = 0; i < numberOfRowsCols; i++){
                if(board[i][(numberOfRowsCols - 1) - i] == Cell.X)
                    revDiagCounter++;
                if(revDiagCounter == connectionsToWin){
                    checkWinner = true;
                }
            }
        }

        if (checkWinner) {
            return GameStatus.X_WON;
        }
        return GameStatus.IN_PROGRESS;
    }

    /**************************************************************
     * This method checks to see if the computer has won. This is
     * used after each turn.
     * @param row
     * @param col
     * @return GameStatus.O_WON
     * @return GameStatus.IN_PROGRESS
     *************************************************************/
    public GameStatus checkForO(int row, int col) {
        boolean checkWinner = false;

        // check for row win
        int colCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[row][i] == Cell.O)
                colCounter++;
            if(colCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        //check for col win
        int rowCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[i][col] == Cell.O)
                rowCounter++;
            if(rowCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        //check for a diagonal win
        int diagCounter = 0;
        if(row == col){
            for(int i = 0; i < numberOfRowsCols; i++){
                if(board[i][i] == Cell.O)
                    diagCounter++;
                if(diagCounter == connectionsToWin){
                    checkWinner = true;
                }
            }
        }

        //check for a reverse diagonal win
        int revDiagCounter = 0;
        if(row + col == numberOfRowsCols - 1){
            for(int i = 0; i < numberOfRowsCols; i++){
                if(board[i][(numberOfRowsCols - 1) - i] == Cell.O)
                    revDiagCounter++;
                if(revDiagCounter == connectionsToWin){
                    checkWinner = true;
                }
            }
        }

        if (checkWinner) {
            return GameStatus.O_WON;
        }
        return GameStatus.IN_PROGRESS;
    }

    /**************************************************************
     * Cell[][] getter
     * @return board
     *************************************************************/
    public Cell[][] getBoard() {
        return board;
    }

    /**************************************************************
     * This method checks to see if there is no clear winner. If
     * there isn't, the game status is changed to cats game.
     * @param row
     * @param col
     * @return GameStatus.IN_PROGRESS
     * @return GameStatus.CATS
     *************************************************************/
    public GameStatus checkForCats(int row, int col) {
        for (row = 0; row < numberOfRowsCols; row++)
            for (col = 0; col < numberOfRowsCols; col++)
                if (board[row][col] == Cell.EMPTY) {
                    return GameStatus.IN_PROGRESS;
                }
        return GameStatus.CATS;
    }
    /**************************************************************
     * This methodd takes most recent move and adds it to
     * the arrayList of points
     * @param row
     * @param col
     *************************************************************/
    public void updatePastMoves(int row, int col) {
        Point lastMovePoint = new Point(row,col);
        pastMoves.add(lastMovePoint);
    }

    /**************************************************************
     * This method removes the latest move from the board
     *************************************************************/
    public void undoPastMove() {
        //checks if array is empty before removing any of the elements
        if (pastMoves.size() != 0) {
            //gets last element in array and makes a point of it
            Point latestMove = pastMoves.get(pastMoves.size() - 1);

            //sets board position of point to empty
            board[latestMove.x][latestMove.y] = Cell.EMPTY;

            //removes the element from the array
            pastMoves.remove(pastMoves.size() - 1);
        }
    }


    /**************************************************************
     *
     *************************************************************/
    public void randomAI() {

        for (int row = 0; row < numberOfRowsCols; row++) {
            for (int col = 0; col < numberOfRowsCols; col++) {

                int colCounter = 0;
                for(int i = 0; i < numberOfRowsCols; i++){
                    if(board[row][i] == Cell.O)
                        colCounter++;
                    if(colCounter == connectionsToWin){
                        //checkWinner = true;
                    }
                }
            }
        }

        Random rand = new Random();
        int randVal1 = rand.nextInt(numberOfRowsCols);
        int randVal2 = rand.nextInt(numberOfRowsCols);
        boolean failureFlag;

        /** Loop will iterate until it finds an empty cell **/
        do {
            failureFlag = false;

            if (board[randVal1][randVal2] == Cell.EMPTY) {
                select(randVal1, randVal2);
                break;
            }

            else {
                failureFlag = true;
                randVal1 = rand.nextInt(numberOfRowsCols);
                randVal2 = rand.nextInt(numberOfRowsCols);
            }

        } while(failureFlag);
        updatePastMoves(randVal1, randVal2);
        System.out.println("The AI placed an O at " + randVal1 + " " + randVal2);

    }

}
