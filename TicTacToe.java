import java.util.InputMismatchException;
import java.util.Random;
import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {
        char[] board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard(board);
            playerInput(board, currentPlayer);
            checkWin(board, currentPlayer);
            checkTie(board);
            currentPlayer = switchPlayer(currentPlayer);
            computerMove(board, currentPlayer);
            checkWin(board, currentPlayer);
            checkTie(board);
        }
    }

    public static void printBoard(char[] board) {
        System.out.println(board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("----------");
        System.out.println(board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("----------");
        System.out.println(board[6] + " | " + board[7] + " | " + board[8]);
    }

    public static void playerInput(char[] board, char currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number from 1-9 for player " + currentPlayer + ": ");
        int position;
        try {
            position = scanner.nextInt() - 1;
            if (position < 0 || position > 8 || board[position] != '-') {
                System.out.println("Invalid input. Please enter an empty position.");
            } else {
                board[position] = currentPlayer;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public static boolean checkWin(char[] board, char currentPlayer) {
        boolean win = false;
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i] == currentPlayer && board[i + 3] == currentPlayer && board[i + 6] == currentPlayer) {
                win = true;
                break;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i] == currentPlayer && board[i + 4] == currentPlayer && board[i + 8] == currentPlayer) {
                win = true;
                break;
            }
        }
        // Check diagonals
        if (board[0] == currentPlayer && board[4] == currentPlayer && board[8] == currentPlayer ||
                board[2] == currentPlayer && board[4] == currentPlayer && board[6] == currentPlayer) {
            win = true;
        }

        if (win) {
            System.out.println("Player " + currentPlayer + " wins!");
        }
        return win;
    }

    public static void checkTie(char[] board) {
        boolean tie = true;
        for (char c : board) {
            if (c == '-') {
                tie = false;
                break;
            }
        }
        if (tie) {
            System.out.println("It's a tie!");
        }
    }

    public static char switchPlayer(char currentPlayer) {
        return currentPlayer == 'X' ? 'O' : 'X';
    }

    public static void computerMove(char[] board, char currentPlayer) {
        Random random = new Random();
        int position;
        do {
            position = random.nextInt(board.length);
        } while (board[position] != '-');
        board[position] = currentPlayer;
        System.out.println("Computer played at position " + (position + 1));
    }
}