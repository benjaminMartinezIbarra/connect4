import java.util.function.Predicate;

/**
 * @author benjaminmartinez
 * Date: 2019-05-09
 */
public class Board {

    private char[][] matrix;

    public Board() {
        matrix = new char[7][6];
    }

    public char getValue(final int column, final int row) {
        return matrix[column][row];
    }

    public boolean isEmpty() {
        return testAllPositionsWith(character -> character.equals(Character.MIN_VALUE));
    }

    public boolean isFull() {
        return testAllPositionsWith(character -> !character.equals(Character.MIN_VALUE));
    }

    private boolean testAllPositionsWith(Predicate<Character> predicate) {
        for (int col = 0; col < matrix.length; col++) {
            for (int row = 0; row < matrix[col].length; row++) {
                if (!predicate.test(getValue(col, row))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmptyPosition(int column, int row) {
        return matrix[column][row] == Character.MIN_VALUE;
    }

    public void insert(final int column, final int position, final char x) {
        matrix[column][position] = x;
    }

    public int numberOfColumns() {
        return matrix.length;
    }

    public int numberOfRows() {
        return matrix[0].length;
    }
}
