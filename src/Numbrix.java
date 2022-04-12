/**
 * @author -
 * @version -
 * <p>
 * Solves Numbrix puzzles
 * http://www.parade.com/numbrix
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;

/**
 * Represents a Numbrix puzzle.
 */
public class Numbrix {
    /**
     * The puzzle data
     */
    private int[][] grid;

    /**
     * Indicates whether numbers are used in the original puzzle
     * If the number n is used, then used[n] is true;  Otherwise it's false
     */
    private boolean[] used;

    /**
     * Constructs a Numbrix puzzle object.
     *
     * @param fileName the name of the file containing the puzzle data.
     * @throws FileNotFoundException when fileName file does not exist.
     */
    public Numbrix(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        grid = new int[rows][cols];
        used = new boolean[100];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();
                used[grid[i][j]] = true;
            }
        }
    }

    /**
     * Finds all solutions to the Numbrix puzzle stored in grid by
     * calling recursiveSolve for every possible cell in grid that
     * originally contains a 0 or a 1.  Each of these calls should
     * attempt to solve the puzzle beginning with the number 1.
     */
    public void solve() {
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 || grid[i][j] == 1) {
                    recursiveSolve(i, j, 1);
                }
            }
        }
    }

    /**
     * Attempts to solve the Numbrix puzzle by placing n in grid[r][c]
     * and then making recursive calls (up, down, left, and right) to
     * place n+1 and higher numbers.
     *
     * @param r the row of the cell in which to place n.
     * @param c the column of the cell in which to place n.
     * @param n the number to place in grid[r][c].
     */
    private void recursiveSolve(int r, int c, int n) {
        try {
            int number = grid[r][c];
            if ((number == 0 && !used[n]) || number == n) {
                grid[r][c] = n;

                if (n != (grid.length * grid[0].length)) {
                    recursiveSolve(r, c + 1, n + 1);
                    recursiveSolve(r, c - 1, n + 1);
                    recursiveSolve(r + 1, c, n + 1);
                    recursiveSolve(r - 1, c, n + 1);

                    if (number == 0) {
                        grid[r][c] = 0;
                    }
                } else {
                    System.out.println(this);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }
    }


    /**
     * Returns a String which represents the puzzle.
     *
     * @return the puzzle numbers with a tab after each number in a row
     * and a new line character after each row.
     * '-' characters should replace 0s in the output.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] array: grid){
            for (int number: array) {
                builder.append(number == 0 ? "-" : number);
                builder.append("\t");
            }
            builder.replace(builder.length()-1, builder.length(), "\n");
        }
        return builder.toString();
    }
}