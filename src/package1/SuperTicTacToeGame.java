package package1;
import java.util.Scanner;

public class SuperTicTacToeGame {
    private Cell[][] board;
    private GameStatus status;
    private int currentTurn;
    private int numberOfRowsCols;
    private int connectionsToWin;
    private String startsFirst;
    Scanner input = new Scanner(System.in);

    public SuperTicTacToeGame() {
        newGame();
    }

    public void newGame() {
        status = GameStatus.IN_PROGRESS;

        //ask for number of columns and rows here
        //numberOfRowsCols();

        //ask for number of connections to win
        //numberOfConnections();

        //ask who starts first here
        //hoStartsFirst();

        board = new Cell[numberOfRowsCols][numberOfRowsCols];

        for (int row = 0; row < numberOfRowsCols; row++)
            for (int col = 0; numberOfRowsCols < 3; col++)
                board[row][col] = Cell.EMPTY;
    }

    public void numberOfConnections(int numOfConnections) {
        connectionsToWin = numOfConnections;
    }

    public void numberOfRowsCols(int numRowsCols) {
        numberOfRowsCols = numRowsCols;
    }

    public void whoStartsFirst(String whoStartsFirst) {
        startsFirst = whoStartsFirst;
    }

    public void select(int row, int col) {
        if (currentTurn % 2 == 0) {
            board[row][col] = Cell.O;
            currentTurn++;
        } else {
            board[row][col] = Cell.X;
            currentTurn++;
        }
    }

    public void reset() {
        newGame();
    }

    //public GameStatus getGameStatus() {
        //if (board[1][1])
    //}

}
