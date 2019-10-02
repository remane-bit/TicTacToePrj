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
        if (connectionsToWin > numberOfRowsCols) {
            System.out.println("Connections to win cannot be greater than the size of the board!");
            numberOfConnections(numOfConnections);
        }

        if(numberOfRowsCols > 3 && connectionsToWin < 3) {
            System.out.println("If the size of the board is greater than 3 the number of connections must be greater than 3!");
            numberOfConnections(numOfConnections);
        }

        if(numberOfRowsCols == 3 && connectionsToWin != 3) {
            System.out.println("If the size of the board is 3 the number of the connections must be 3!");
            numberOfConnections(numOfConnections);
        }
    }

    public void numberOfRowsCols(int numRowsCols) {
        numberOfRowsCols = numRowsCols;
    }

    public void whoStartsFirst(String whoStartsFirst) {
        startsFirst = whoStartsFirst;
        if (startsFirst.equals("X") || startsFirst.equals("x")) {
            currentTurn = 1;
        } else if (startsFirst.equals("O") || startsFirst.equals("o")) {
            currentTurn = 0;
        } else {
            System.out.println("Please enter either X or O for this value");
            whoStartsFirst(whoStartsFirst);
        }
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
