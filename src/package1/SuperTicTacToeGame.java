package package1;

import java.awt.*;
import java.util.ArrayList;

public class SuperTicTacToeGame {
    private Cell[][] board;
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

        currentTurn = 0;

        board = new Cell[numberOfRowsCols][numberOfRowsCols];

        for (int row = 0; row < numberOfRowsCols; row++)
            for (int col = 0; col < numberOfRowsCols; col++)
                board[row][col] = Cell.EMPTY;
    }

    public void setNumberOfConnections(int connectionsToWin) {
        this.connectionsToWin = connectionsToWin;
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

    public int getNumberOfRowsCols() {
        return numberOfRowsCols;
    }

    public void numberOfRowsCols(int numRowsCols) {
        numberOfRowsCols = numRowsCols;
    }

    public void whoStartsFirst(String whoStartsFirst) {
        startsFirst = whoStartsFirst;
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
        boolean checkWinner = true;

        int rowCounter = 0;
        int maxRowCounter = 0;
        //checking for winner in row
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[row][i] == Cell.X) {
                rowCounter++;
            } else {
                if (rowCounter > maxRowCounter) {
                    maxRowCounter = rowCounter;
                }
                rowCounter = 0;
            }
            if (rowCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

        int colCounter = 0;
        int maxColCounter = 0;
        //checking for winner in column
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][col] == Cell.X) {
                colCounter++;
            } else {
                if (colCounter > maxColCounter) {
                    maxColCounter = colCounter;
                }
                colCounter = 0;
            }
            if (colCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

        int diagCounter = 0;
        int maxDiagCounter = 0;
        //checking for winner in diagonal
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][numberOfRowsCols - i - 1] == Cell.X) {
                diagCounter++;
            } else {
                if (diagCounter > maxDiagCounter) {
                    maxDiagCounter = diagCounter;
                }
                diagCounter = 0;
            }
            if (diagCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

        int revCounter = 0;
        int maxRevCounter = 0;
        //checking for winner in diagonal
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[i][i] == Cell.X) {
                revCounter++;
            } else {
                if (revCounter > maxRevCounter) {
                    maxRevCounter = revCounter;
                }
                revCounter = 0;
            }
            if (revCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }
        if (checkWinner) {
            return GameStatus.X_WON;
        }
        return GameStatus.IN_PROGRESS;
    }

    public GameStatus checkForO(int row, int col) {
        boolean checkWinner = true;

        int rowCounter = 0;
        int maxRowCounter = 0;
        //checking for winner in row
        for (int i = 0; i < numberOfRowsCols - 1; i++) {
            if (board[row][i] == Cell.O) {
                rowCounter++;
            } else {
                if (rowCounter > maxRowCounter) {
                    maxRowCounter = rowCounter;
                }
                rowCounter = 0;
            }
            if (rowCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

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
            if (colCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

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
            if (diagCounter < getConnectionsToWin()) {
                checkWinner = false;
            }
        }

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
            if (revCounter < getConnectionsToWin()) {
                checkWinner = false;
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

}
