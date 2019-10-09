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
    private int AImoveX;
    private int AImoveY;


    /****************************************************
     * The computers move in the x axis getter
     * @return AImoveX
     *****************************************************/
    public int getAImoveX() {
        return AImoveX;
    }

    /****************************************************
     * The computers move in the x axis setter
     * @param AImoveX
     *****************************************************/
    public void setAImoveX(int AImoveX) {
        this.AImoveX = AImoveX;
    }

    /****************************************************
     * The computers move in the y axis getter
     * @return AImoveY
     *****************************************************/
    public int getAImoveY() {
        return AImoveY;
    }

    /****************************************************
     * The computers move in the x axis setter
     * @param AImoveY
     *****************************************************/
    public void setAImoveY(int AImoveY) {
        this.AImoveY = AImoveY;
    }

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
     * @return connectionsToWin
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
     * @return status
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
     * @return currentTurn
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

    private boolean checkPlayerRowWin(Cell player) {
        for (int row = 0; row < numberOfRowsCols; row++) {
            int col = 0;
            while (col < numberOfRowsCols && board[row][col] == player) {
                col++;
            }
            if (col == numberOfRowsCols) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPlayerColumnWin(Cell player) {
        for (int col = 0; col < numberOfRowsCols; col++) {
            int row = 0;
            while (row < numberOfRowsCols && board[row][col] == player) {
                row++;
            }
            if (row == numberOfRowsCols) {
                return true;
            }
        }

        return false;
    }

    private boolean checkPlayerDiagonalWin(Cell player) {
        int i = 0;

        // check forward diagonal
        while (i < numberOfRowsCols && board[i][i] == player) {
            i++;
        }

        if (i == numberOfRowsCols) {
            return true;
        }

        // check backward diagonal
        i = 0;
        while (i < numberOfRowsCols && board[i][numberOfRowsCols - i - 1] == player) {
            i++;
        }

        if (i == numberOfRowsCols) {
            return true;
        }

        return false;
    }

    boolean checkPlayerWin(Cell player) {
        return checkPlayerRowWin(player) || checkPlayerColumnWin(player) || checkPlayerDiagonalWin(player);
    }

    private boolean areAnyMovesLeft() {
        for (int row = 0; row < numberOfRowsCols; row++) {
            for (int col = 0; col < numberOfRowsCols; col++) {
                if (board[row][col] == Cell.EMPTY)
                    return true;
            }
        }

        return false;
    }

    private Cell getOppositePlayer(Cell player) {
        Cell oppositePlayer;
        if (player == Cell.O) {
            oppositePlayer = Cell.X;
        } else {
            oppositePlayer = Cell.O;
        }
        return oppositePlayer;
    }

    private int minimax(Cell player) {
        if (checkPlayerWin(Cell.O)) {
            return 10;
        } else if (checkPlayerWin(Cell.X)) {
            return -10;
        }

        // Check if game is over.
        if (!areAnyMovesLeft())
            return 0;

        if (player == Cell.X) {
            // Player's turn.
            int bestVal = -1000;
            for(int i = 0; i < numberOfRowsCols; i++) {
                for (int j = 0; j < numberOfRowsCols; j++) {
                    // Skip used.
                    if (board[i][j] != Cell.EMPTY)
                        continue;

                    // Temporarily set cell to emulate move.
                    board[i][j] = player;
                    // Run minimax algorithm recursively.
                    int value = minimax(Cell.O);
                    // Restore to empty cell again.
                    board[i][j] = Cell.EMPTY;

                    if (value > bestVal) {
                        bestVal = value;
                    }
                }
            }
            return bestVal;
        } else {
            // AI's turn.
            int bestVal = 1000;
            for(int i = 0; i < numberOfRowsCols; i++) {
                for (int j = 0; j < numberOfRowsCols; j++) {
                    // Skip used.
                    if (board[i][j] != Cell.EMPTY)
                        continue;
                    // Temporarily set cell to emulate move.
                    board[i][j] = player;
                    // Run minimax algorithm recursively.
                    int value = minimax(Cell.X);
                    // Restore to empty cell again.
                    board[i][j] = Cell.EMPTY;

                    if (value < bestVal) {
                        bestVal = value;
                    }
                }
            }
            return bestVal;
        }
    }

    public Point determineBestMoveForX() {
        int bestVal = -1000;
        Point bestMove = new Point(-1, -1);

        for (int row = 0; row < numberOfRowsCols; row++) {
            for (int col = 0; col < numberOfRowsCols; col++) {
                // Skip used move.
                if (board[row][col] != Cell.EMPTY)
                    continue;

                // Emulate the move.
                board[row][col] = Cell.X;
                int moveVal = minimax(Cell.X);
                board[row][col] = Cell.EMPTY;

                // If the value of the current move is more than the best value, then update best.
                if (moveVal > bestVal) {
                    bestMove.x = row;
                    bestMove.y = col;
                    bestVal = moveVal;
                }
            }
        }

        return bestMove;
    }

    public Point determineBestMoveForO() {
        int bestVal = 1000;
        Point bestMove = new Point(-1, -1);

        for (int row = 0; row < numberOfRowsCols; row++) {
            for (int col = 0; col < numberOfRowsCols; col++) {
                // Skip used move.
                if (board[row][col] != Cell.EMPTY)
                    continue;

                // Emulate the move.
                board[row][col] = Cell.O;
                int moveVal = minimax(Cell.O);
                board[row][col] = Cell.EMPTY;

                // If the value of the current move is less than the best value, then update best.
                if (moveVal < bestVal) {
                    bestMove.x = row;
                    bestMove.y = col;
                    bestVal = moveVal;
                }
            }
        }

        return bestMove;
    }

    /**************************************************************
     * This method checks to see if there is no clear winner. If
     * there isn't, the game status is changed to cats game.
     * @return GameStatus.IN_PROGRESS
     * @return GameStatus.CATS
     *************************************************************/
    public GameStatus checkForCats() {
        if (areAnyMovesLeft())
            return GameStatus.IN_PROGRESS;
        return GameStatus.CATS;
    }

    /**************************************************************
     * Cell[][] getter
     * @return board
     *************************************************************/
    public Cell[][] getBoard() {
        return board;
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
    public void bestAI() {
        // Determine the best move.
        Point bestMove = determineBestMoveForO();
        select(bestMove.x, bestMove.y);
        updatePastMoves(bestMove.x, bestMove.y);
        setAImoveX(bestMove.x);
        setAImoveY(bestMove.y);
    }

    public void randomAI() {
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
        setAImoveX(randVal1);
        setAImoveY(randVal2);
        System.out.println("The AI placed an O at " + randVal1 + " " + randVal2);

    }

}
