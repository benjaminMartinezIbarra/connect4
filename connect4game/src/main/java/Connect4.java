/**
 * @author benjaminmartinez
 * Date: 2019-05-04
 */
public class Connect4 {

    public static final String DRAW_STATUS = "DRAW";
    public static final String STARTED_STATUS = "STARTED";
    protected static final String PLAYER_1 = "Player1";
    protected static final String PLAYER_2 = "Player2";
    private char[][] columns;
    private String currentPlayer;
    private String status;

    public Connect4() {

        columns = new char[7][6];
        currentPlayer = PLAYER_1;
        status = STARTED_STATUS;
    }

    public char[][] getColumns() {
        return columns;
    }

    private void insertDisc(final int column, final char disc) {
        insert(column, getNextPositionAvailableInColumn(column, 5), disc);
    }

    private void insert(final int column, final int position, final char x) {
        columns[column][position] = x;
    }

    public int getNextPositionAvailableInColumn(final int column) {
        return getNextPositionAvailableInColumn(column, 5);
    }

    private int getNextPositionAvailableInColumn(final int column, int startPos) {

        if (startPos >= 0 && isEmptyPosition(column, startPos)) {
            return startPos;
        } else if (startPos < 0) {
            throw new RuntimeException(String.format("no available positions for column %s", column));
        } else {
            return getNextPositionAvailableInColumn(column, startPos - 1);
        }
    }

    public char getValueAtPosition(final int column, final int row) {
        return columns[column][row];
    }

    public void insertDisc(final int column) {
        if (!isDraw()){
            insertDisc(column, getDiscForCurrentPlayer());
            changePlayer();
        }else{
            throw new RuntimeException("game is Draw, restart a new one to play again");
        }


    }

    private char getDiscForCurrentPlayer() {
        if (currentPlayer == PLAYER_1) {
            return 'G';
        }
        return 'R';
    }

    private void changePlayer() {
        if (currentPlayer == PLAYER_1) {
            currentPlayer = PLAYER_2;
        } else {
            currentPlayer = PLAYER_1;
        }
    }

    public boolean isDraw() {
        for (int col = 0; col < columns.length; col++) {
            for (int row = 0; row < columns[col].length; row++) {
                if (isEmptyPosition(col, row)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isEmptyPosition(int column, int row) {
        return columns[column][row] == Character.MIN_VALUE;
    }
}
