package package1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class SuperTicTacToeGame {
    public Cell[][] board;
    private GameStatus status;
    private int currentTurn;
    private int numberOfRowsCols;
    private int connectionsToWin;
    private String startsFirst;

    private ArrayList<Point> pastMoves = new ArrayList<Point>();

    public SuperTicTacToeGame() {
        newGame();
    }

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

    public int getConnectionsToWin() {
        return connectionsToWin;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setConnectionsToWin(int connectionsToWin) {
        this.connectionsToWin = connectionsToWin;
    }

    public void setStartsFirst(String startsFirst) {
        this.startsFirst = startsFirst;
    }

    public void setNumberOfRowsCols(int numberOfRowsCols) {
        this.numberOfRowsCols = numberOfRowsCols;
    }

    public void select(int row, int col) {
        if (currentTurn % 2 == 0) {
            board[row][col] = Cell.X;
            currentTurn++;
        } else {
            board[row][col] = Cell.O;
            currentTurn++;
        }
    }

    public void reset() {
        newGame();
    }

    public GameStatus checkForX(int row, int col) {
        boolean checkWinner = false;

        //check for row win
        int rowCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[i][col] == Cell.X)
                rowCounter++;
            if(rowCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        // check for col win
        int colCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[row][i] == Cell.X)
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

    public GameStatus checkForO(int row, int col) {
        boolean checkWinner = false;

        //check for row win
        int rowCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[i][col] == Cell.O)
                rowCounter++;
            if(rowCounter == connectionsToWin){
                checkWinner = true;
            }
        }

        // check for col win
        int colCounter = 0;
        for(int i = 0; i < numberOfRowsCols; i++){
            if(board[row][i] == Cell.O)
                colCounter++;
            if(colCounter == connectionsToWin){
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

    public Cell[][] getBoard() {
        return board;
    }

    public GameStatus checkForCats(int row, int col) {
        for (row = 0; row < numberOfRowsCols; row++)
            for (col = 0; col < numberOfRowsCols; col++)
                if (board[row][col] == Cell.EMPTY) {
                    return GameStatus.IN_PROGRESS;
                }
        return GameStatus.CATS;
    }

    // takes most recent move and adds it to the arrayList of points
    public void updatePastMoves(int row, int col) {
        Point lastMovePoint = new Point(row,col);
        pastMoves.add(lastMovePoint);
    }

    //removes the latest move from the board
    public void undoPastMove() {
        //gets last element in array and makes a point of it
        Point latestMove = pastMoves.get(pastMoves.size() - 1);

        //sets board position of point to empty
        board[latestMove.x][latestMove.y] = Cell.EMPTY;

        //removes the element from the array
        pastMoves.remove(pastMoves.size() - 1);
    }


    public void randomAI() {

        int row = 0;
        int col = 0;

        //checks for opponent about to win through connections in a row and stops it
        int rowCounter = 0;
        int maxRowCounter = 0;
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[row][i] == Cell.X) {
                rowCounter++;
            } else {
                if (rowCounter > maxRowCounter) {
                    maxRowCounter = rowCounter;
                }
                rowCounter = 0;
            }
            if (maxRowCounter + 1 == getConnectionsToWin() && board[row][i - maxRowCounter] == Cell.EMPTY) {
                select(row, i - rowCounter);
                break;
            }
            if (maxRowCounter + 1 == getConnectionsToWin() && board[row][i + 1] == Cell.EMPTY) {
                select(row, i + 1);
                break;
            }
        }

        //checks for opponent about to win through connections in a col and stops it
        int colCounter = 0;
        int maxColCounter = 0;
        //checking for winner in column
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][col] == Cell.O) {
                colCounter++;
            } else {
                if (colCounter > maxColCounter) {
                    maxColCounter = colCounter;
                }
                colCounter = 0;
            }
            if (maxColCounter + 1 == getConnectionsToWin() && board[i - maxColCounter][col] == Cell.EMPTY) {
                select(i - colCounter, col);
                break;
            }
            if (maxColCounter + 1 == getConnectionsToWin() && board[i + 1][col] == Cell.EMPTY) {
                select(i + 1, col);
                break;
            }
        }

        //checks for opponent about to win through connections in a diag and stops it
        int diagCounter = 0;
        int maxDiagCounter = 0;
        //checking for winner in diagonal
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][numberOfRowsCols - i - 1] == Cell.O) {
                diagCounter++;
            } else {
                if (diagCounter > maxDiagCounter) {
                    maxDiagCounter = diagCounter;
                }
                diagCounter = 0;
            }
            if (maxDiagCounter + 1 == getConnectionsToWin() && board[i - maxDiagCounter][numberOfRowsCols - i - maxDiagCounter - 1] == Cell.EMPTY) {
                select(i - maxDiagCounter,numberOfRowsCols - i - maxDiagCounter - 1 );
                break;
            }
            if (maxDiagCounter + 1 == getConnectionsToWin() && board[i + 1][numberOfRowsCols - i] == Cell.EMPTY) {
                select(i + 1,numberOfRowsCols - i);
                break;
            }
        }

        //checks for opponent about to win through connections in a reverse diag and stops it
        int revCounter = 0;
        int maxRevCounter = 0;
        //checking for winner in diagonal
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][i] == Cell.O) {
                revCounter++;
            } else {
                if (revCounter > maxRevCounter) {
                    maxRevCounter = revCounter;
                }
                revCounter = 0;
            }
            if (maxRevCounter + 1 == getConnectionsToWin() && board[i - maxRevCounter][i - maxRevCounter] == Cell.EMPTY) {
                select(i - maxRevCounter, i - maxRevCounter);
                break;
            }
            if (maxRevCounter + 1 == getConnectionsToWin() && board[i + 1][i + 1] == Cell.EMPTY) {
                select(i + 1, i + 1);
                break;
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

        System.out.println("The AI placed an O at " + randVal1 + " " + randVal2);

    }

}
