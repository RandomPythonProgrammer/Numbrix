/**
 * @author - Mr. Bredemeier
 * @version - August 6, 2011
 * <p>
 * Solves Numbrix puzzles
 * http://www.parade.com/numbrix
 */

import java.io.FileNotFoundException;

public class NumbrixMain {
    private static String version = "3.0";  // 2/10/20

    /**
     * Constructs a Numbrix object and uses it to solve a Numbrix puzzle.
     * @throws FileNotFoundException when fileName file does not exist.
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-> Numbrix project version " + version + " <-\n");

        for (int i = 1; i <= 6; i++) {
            Numbrix puzzle = new Numbrix("Puzzle" + i + ".txt");
            System.out.println("Puzzle #" + i + " **************************");
            System.out.println(puzzle);
            puzzle.solve();
        }
    }
}
