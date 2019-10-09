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

        if (first == 'X' || first == 'x') {
            if (currentTurn % 2 == 0) {
                board[row][col] = Cell.X;
                currentTurn++;
            } else {
                board[row][col] = Cell.O;
                currentTurn++;
            }
        } else if (first == 'O' || first == 'o') {
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
        for (int i = 0; i < numberOfRowsCols; i++) {
            if (board[row][i] == Cell.X)
                rowCounter++;
            if (rowCounter == connectionsToWin) {
                checkWinner = true;
            }
        }

        //check for col win
        int colCounter = 0;
        for (int i = 0; i < numberOfRowsCols; i++) {
            if (board[i][col] == Cell.X)
                colCounter++;
            if (colCounter == connectionsToWin) {
                checkWinner = true;
            }
        }

        //check for a diagonal win
        int diagCounter = 0;
        if (row == col) {
            for (int i = 0; i < numberOfRowsCols; i++) {
                if (board[i][i] == Cell.X)
                    diagCounter++;
                if (diagCounter == connectionsToWin) {
                    checkWinner = true;
                }
            }
        }

        //check for a reverse diagonal win
        int revDiagCounter = 0;
        if (row + col == numberOfRowsCols - 1) {
            for (int i = 0; i < numberOfRowsCols; i++) {
                if (board[i][(numberOfRowsCols - 1) - i] == Cell.X)
                    revDiagCounter++;
                if (revDiagCounter == connectionsToWin) {
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

        boolean CheckWinner = false;

        // check for row win
        int RowCounter = 0;
        for (int i = 0; i < numberOfRowsCols; i++) {
            if (board[row][i] == Cell.O)
                RowCounter++;
            if (RowCounter == connectionsToWin) {
                CheckWinner = true;
            }
        }

        //check for col win
        int ColCounter = 0;
        for (int i = 0; i < numberOfRowsCols; i++) {
            if (board[i][col] == Cell.O)
                ColCounter++;
            if (ColCounter == connectionsToWin) {
                CheckWinner = true;
            }
        }

        //check for a diagonal win
        int DiagCounter = 0;
        if (row == col) {
            for (int i = 0; i < numberOfRowsCols; i++) {
                if (board[i][i] == Cell.O)
                    DiagCounter++;
                if (DiagCounter == connectionsToWin) {
                    CheckWinner = true;
                }
            }
        }

        //check for a reverse diagonal win
        int RevDiagCounter = 0;
        if (row + col == numberOfRowsCols - 1) {
            for (int i = 0; i < numberOfRowsCols; i++) {
                if (board[i][(numberOfRowsCols - 1) - i] == Cell.O)
                    RevDiagCounter++;
                if (RevDiagCounter == connectionsToWin) {
                    CheckWinner = true;
                }
            }
        }

        if (CheckWinner) {
            System.out.println("O WINS");
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
        Point lastMovePoint = new Point(row, col);
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
                for (int i = 0; i < numberOfRowsCols; i++) {
                    if (board[row][i] == Cell.O)
                        colCounter++;
                    if (colCounter == connectionsToWin) {
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
            } else {
                failureFlag = true;
                randVal1 = rand.nextInt(numberOfRowsCols);
                randVal2 = rand.nextInt(numberOfRowsCols);
            }

        } while (failureFlag);

        updatePastMoves(randVal1, randVal2);
        setAImoveX(randVal1);
        setAImoveY(randVal2);
        System.out.println("The AI placed an O at " + randVal1 + " " + randVal2);

    }


    public void smarterAI() {

        boolean AttackMode = false;
        boolean DefenedMode = false;
        int AIxMove = 0;
        int AIyMove = 0;
        int xCounter = 0;
        int yCounter = 0;
        int OinaRow = 0;
        int i = 0;

        /** If its the AI's first move, go for a corner **/
        if ((getCurrentTurn() == 0) || (getCurrentTurn() == 1)) {
            //If the top left corner is empty, place an O there
            if (board[0][0] == Cell.EMPTY) {
                select(0, 0);
                setAImoveX(0);
                setAImoveY(0);
                AIxMove = 0;
                AIyMove = 0;
            }
            //If top right corner is taken, go for the bottom right instead
            else if (board[numberOfRowsCols - 1][numberOfRowsCols - 1] == Cell.EMPTY) {
                select(numberOfRowsCols - 1, numberOfRowsCols - 1);
                setAImoveX(numberOfRowsCols - 1);
                setAImoveY(numberOfRowsCols - 1);
                AIxMove = numberOfRowsCols;
                AIyMove = numberOfRowsCols;
            }
        }

        //Now it should scan the board to see if the user can win in anyway. If the user can win,
        // go into defend mode. Else attack mode.

        else {

            AttackMode = false;
            DefenedMode = false;

            System.out.println("AI is searching for spot");

            for (int Row = 0; Row < numberOfRowsCols; Row++) {
                for (int Col = 0; Col < numberOfRowsCols; Col++) {

                    /** IF A CELL HAS AN X **/
                    if ((board[Row][Col] == Cell.X) && (!DefenedMode)) {
                        //Check the rows to around it, set the col = 0 then scan it. If xcounter == numtowin - 1, place O there
                        i = Row;

                        /** Checks the row for a streak of X's  **/
                        for (int j = 0; j < numberOfRowsCols; j++) {
                            if (board[i][j] == Cell.X) {
                                xCounter++;
                            } else {
                                xCounter = 0;
                            }

                            if (xCounter == connectionsToWin - 1) {
                                if (board[i][j + 1] == Cell.EMPTY) {
                                    DefenedMode = true;
                                    AIxMove = i;
                                    AIyMove = j + 1;
                                    break;
                                } else if (board[i][j + 1] == Cell.O) {
                                    xCounter = 0;
                                }
                            }
                        }
                        //Check the cols around it, set row = 0, then scan it. If xcounter == numtowin - 1, place O there

                        i = Col;

                        for (int j = 0; j < numberOfRowsCols; j++) {
                            if (board[j][i] == Cell.X) {
                                yCounter++;
                            } else {
                                yCounter = 0;
                            }

                            if (yCounter == connectionsToWin - 1) {
                                if (board[j + 1][i] == Cell.EMPTY) {
                                    DefenedMode = true;
                                    AIxMove = i;
                                    AIyMove = j + 1;
                                    break;
                                } else if (board[j + 1][i] == Cell.O) {
                                    yCounter = 0;
                                }
                            }
                        }
                        //Check the diags if its in the diagonal range. Manually go from [0][0], [1][1], using i until i == numRowsCOls - 1
                    }

                    /** IF A CELL HAS AN O **/
                    if ((board[Row][Col] == Cell.O) && (!DefenedMode)) {

                        i = Row;

                        /** Checks the row for a streak of X's  **/
                        for (int j = 0; j < numberOfRowsCols; j++) {
                            if (board[i][j] == Cell.O) {
                                //If theres another O in the row, do this
                                OinaRow++;

                            } else if (board[i][j] == Cell.EMPTY) {
                                System.out.println("Hi?");
                                AttackMode = true;
                                AIxMove = i;
                                AIyMove = j;
                                break;
                            }

                            if (OinaRow == connectionsToWin - 1) {
                                AttackMode = true;
                                AIxMove = i;
                                AIyMove = j + 1;
                            }


                        }


                    }


                }
            }


                if (AttackMode) {
                    System.out.println("Attacking");
                    select(AIxMove, AIyMove);
                }
                else if (DefenedMode) {
                    System.out.println("Defending");
                    select(AIxMove, AIyMove);
                }

                /** If neither flag is activated, randomly put an O down **/
                else {


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
                        } else {
                            failureFlag = true;
                            randVal1 = rand.nextInt(numberOfRowsCols);
                            randVal2 = rand.nextInt(numberOfRowsCols);
                        }

                    } while (failureFlag);


                    updatePastMoves(randVal1, randVal2);
                    setAImoveX(randVal1);
                    setAImoveY(randVal2);
                    select(randVal1, randVal2);
                    System.out.println("The AI placed an O randomly at " + randVal1 + " " + randVal2);

                }
                AttackMode = false;
                DefenedMode = false;


            }

            System.out.println("The AI placed an O at " + AIxMove + " " + AIyMove);
        }



    /* nested for loop that checks whats contained in all the cells
     for (int Row = 0; Row < numberOfRowsCols; Row++) {
                for (int Col = 0; Col < numberOfRowsCols; Col++) {
                    if(board[Row][Col] == Cell.X) {
                        //Check the entire board for Cells with X's
                        ;
                        }

                    else if(board[Row][Col] == Cell.EMPTY) {
                        //Check the entire board for Cells that are empty
                        ;
                    }

                    else if(board[Row][Col] == Cell.O) {
                        //Check the entire board for Cells with O's
                        ;
                    }
                }
            }
     */

    }

