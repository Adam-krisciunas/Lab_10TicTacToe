import java.util.Scanner;

class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";
    private static Scanner scanner = new Scanner(System.in);
    private static boolean gameOver;

    public static void main(String[] args) {
        clearBoard();
        display();

        while (!isGameOver()) {
            // Get the inputs
            int row = SafeInput.getRangedInt(scanner,"Enter row (1-3): ", 1, 3) - 1;
            int col = SafeInput.getRangedInt(scanner,"Enter col (1-3): ", 1, 3) - 1;
            // Make sure its valid
            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                display();
                //Win
                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    return;
                    //Tie
                } else if (isTie()) {
                    System.out.println("Tie game!");
                    return;
                }
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }
//Erase the board
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }
// Print out the board
    private static void display() {
        System.out.println("   1   2   3");
        for (int i = 0; i < ROW; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  ---|---|---");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }
    // Win vector
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }
    // Win vector
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
// Win vector
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
                || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }
    private static boolean isTie() {
        // Check if all spaces on the board are filled
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    // There is an empty space on the board, so the game is not tied yet
                    return false;
                }
            }
        }

        // Check if all possible wins are blocked by having both X and O in them
        boolean hasX = false, hasO = false;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals("X")) {
                    hasX = true;
                } else if (board[i][j].equals("O")) {
                    hasO = true;
                }
            }
        }
        if (hasX && hasO) {
            // There is an X and an O in every win vector, so the game is tied
            return true;
        }

        // Otherwise, the game is not tied yet
        return false;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        TicTacToe.gameOver = gameOver;
    }
}
