import java.util.HashMap;
import java.util.Scanner;

public class TicTacToe {

    static char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

    static int count = 0; //global count of all the pieces on the board
    static boolean[][] taken = new boolean[3][3];

    public static void main (String[] args) {
        System.out.println("Welcome to my Tic Tac Toe Game!");
        System.out.println("BY: Anand Krishnan");
        System.out.println();
        System.out.println();

        Scanner scan = new Scanner(System.in);

        //Game Loop with sentinel value:
        System.out.println("Type x if you want to leave. Press any key if you want to play: ");
        String sentinel = scan.nextLine();
        while (!sentinel.equalsIgnoreCase("X")) {
            if (count == 9) {
                System.out.println("It is a tie.");
                break;
            }
            if (getInput('X') || getInput('O')) {
               break;
            }
        }

        System.out.println("Game over. Thanks for playing. Godspeed.");

    }

    private static boolean getInput(char player) {
        //Current Game Board:
        System.out.println();
        System.out.println("Current Game Board: ");
        printGameBoard();

        switch(player) {
            case 'X':
                System.out.println("Player 1's Turn: ");
                break;
            case 'O':
                System.out.println("Player 2's Turn: ");
                break;
        }

        System.out.println("Give a VALID ROW to place your " + player + ": (1, 2 or 3)");

        Scanner scan = new Scanner(System.in);

        int row = scan.nextInt();
        while (row < 1 || row > 3) {
            System.out.println("The inputted value is invalid. Please try again: ");
            row = scan.nextInt();
        }

        System.out.println("Give a VALID COLUMN to place your " + player + ": (1, 2 or 3)");
        int col = scan.nextInt();
        while (col < 1 || col > 3) {
            System.out.println("The inputted value is invalid. Please try again: ");
            col = scan.nextInt();
        }

        while (taken[row - 1][col - 1] == true) { //If the chosen row - col pair are filled
            System.out.println();
            System.out.println("The chosen row/column pair is already taken. Try again: ");

            System.out.println("Give a VALID ROW to place your " + player + ": (1, 2 or 3)");
            row = scan.nextInt();

            while (row < 1 || row > 3) {
                System.out.println("The inputted value is invalid. Please try again: ");
                row = scan.nextInt();
            }

            System.out.println("Give a VALID COLUMN to place your " + player + ": (1, 2 or 3)");
            col = scan.nextInt();
            while (col < 1 || col > 3) {
                System.out.println("The inputted value is invalid. Please try again: ");
                col = scan.nextInt();
            }
        }

        turn(row, col, player);
        if (check()) {
            return true;
        }
        return false;
    }

    public static boolean check() {
        int counterX = 0;
        int counterY = 0;

        for (int i = 0; i < gameBoard[0].length; i += 2) { //Checking the top row
            if (gameBoard[0][i] == 'X') {
                counterX++;
            } else if (gameBoard[i][0] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------

        for (int i = 0; i < gameBoard.length; i += 2) { //Checking the main diagonal
            if (gameBoard[i][i] == 'X') {
                counterX++;
            } else if (gameBoard[i][i] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------

        for (int i = 0; i < gameBoard.length; i += 2) { //Checking the first column
            if (gameBoard[i][0] == 'X') {
                counterX++;
            } else if (gameBoard[i][0] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------

        for (int i = 0; i < gameBoard.length; i += 2) { //Checking the middle column
            if (gameBoard[i][2] == 'X') {
                counterX++;
            } else if (gameBoard[i][2] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------

        for (int i = 0; i < gameBoard.length; i += 2) { //Checking the last column
            if (gameBoard[i][4] == 'X') {
                counterX++;
            } else if (gameBoard[i][4] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------


        for (int i = 4; i >= 0; i -= 2) { //Checking the counter diagonal
            if (gameBoard[i][i] == 'X') {
                counterX++;
            } else if (gameBoard[i][i] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------


        for (int i = 0; i < gameBoard[0].length; i += 2) { //Checking the middle row
            if (gameBoard[2][i] == 'X') {
                counterX++;
            } else if (gameBoard[2][i] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        counterX = 0;
        counterY = 0;

        //--------------------------------------------------------------------------


        for (int i = 0; i < gameBoard[0].length; i += 2) { //Checking the last row
            if (gameBoard[4][i] == 'X') {
                counterX++;
            } else if (gameBoard[4][i] == 'Y') {
                counterY++;
            }
        }

        if (checkHelper(counterX, counterY)) {
            return true; //One of the players has won.
        }

        return false;
    }

    public static boolean checkHelper(int counterX, int counterY) {
        //System.out.println(counterX);
        //System.out.println(counterY);
        printGameBoard();
        if (counterX == 3) {
            System.out.println("Player 1 wins!");
            return true;
        } else if (counterY == 3) {
            System.out.println("Player 2 wins!");
            return true;
        }
        return false; //No one has win yet.
    }

    private static void printGameBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    //Adds the symbol to the appropriate location on the board
    private static void turn(int row, int col, char player) {
        HashMap<Integer, Integer> map = new HashMap<>();

        //Keys are the given rows / columns
        //Values are the real rows / real columns

        map.put(1, 0);
        map.put(2, 2);
        map.put(3, 4);

        int realRow = map.get(row);
        int realCol = map.get(col);

        gameBoard[realRow][realCol] = player;
        taken[row - 1][col - 1] = true;
        count++;
    }

}
