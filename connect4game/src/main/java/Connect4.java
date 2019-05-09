import java.util.ArrayList;
import java.util.List;

/**
 * @author benjaminmartinez
 * Date: 2019-05-04
 */
public class Connect4 {

    protected static final String PLAYER_1 = "Player1";
    protected static final String PLAYER_2 = "Player2";

    private Board board;

    private String currentPlayer;
    private List<Observer> observers;

    public Connect4() {
        board = new Board();
        currentPlayer = PLAYER_1;
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);

    }

    public void insertDisc(final int column) {
        if (!isFinished()) {
            notifyObservers(insertDisc(column, getDiscForCurrentPlayer()));
            changePlayer();
        } else if (isDraw()) {
            notifyObservers(new DrawGameEvent());
        } else if (isWin()) {
            notifyObservers(new GameWinEvent(currentPlayer));
        }
    }

    public boolean isFinished() {
        return isDraw() || isWin();
    }

    public boolean isDraw() {
        return board.isFull() && !isWin();
    }

    private void notifyObservers(GameEvent event) {
        observers.forEach(observer -> observer.on(event));
    }

    private char getDiscForCurrentPlayer() {
        if (currentPlayer == PLAYER_1) {
            return 'G';
        }
        return 'R';
    }

    private DiscAddedEvent insertDisc(final int column, final char disc) {
        final int nextPositionAvailableInColumn = getNextPositionAvailableInColumn(column, 5);
        board.insert(column, nextPositionAvailableInColumn, disc);
        return new DiscAddedEvent(disc, currentPlayer, column, nextPositionAvailableInColumn);
    }

    private int getNextPositionAvailableInColumn(final int column, int startPos) {

        if (startPos >= 0 && board.isEmptyPosition(column, startPos)) {
            return startPos;
        } else if (startPos < 0) {
            throw new RuntimeException(String.format("no available positions for column %s", column));
        } else {
            return getNextPositionAvailableInColumn(column, startPos - 1);
        }
    }

    private void changePlayer() {
        if (currentPlayer == PLAYER_1) {
            currentPlayer = PLAYER_2;
        } else {
            currentPlayer = PLAYER_1;
        }
    }

    public boolean isWin() {
        for (int col = 0; col < board.numberOfColumns(); col++) {
            for (int row = 0; row < board.numberOfRows(); row++) {
                if (checkPosition(col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPosition(int startx, int starty) {
        return (checkDirection(startx, starty, 0, 1) ||
                checkDirection(startx, starty, 1, 0) ||
                checkDirection(startx, starty, 1, 1) ||
                checkDirection(startx, starty, -1, 1));
    }

    private boolean checkDirection(final int i, final int i1, final int i2, final int i3) {
        return checkDirection(i, i1, i2, i3, 1);
    }

    public boolean checkDirection(int startingColumn, int startingRow, int dirx, int diry, int acc) {

        if (acc == 4) {
            return true;
        }
        if (boundaryReach(startingColumn + dirx, startingRow + diry)) {
            return false;
        }
        if (!board.isEmptyPosition(startingColumn, startingRow) && board.getValue(startingColumn, startingRow) ==
                                                                   board.getValue(startingColumn + dirx, startingRow + diry)) {
            return checkDirection(startingColumn + dirx,
                                  startingRow + diry,
                                  dirx,
                                  diry,
                                  acc + 1);
        } else {
            return false;
        }
    }

    private boolean boundaryReach(final int startCol, final int startRow) {
        return ((startCol < 0) || (startCol >= board.numberOfColumns())) || ((startRow < 0) || (startRow >= board.numberOfRows()));
    }

    public int getNextPositionAvailableInColumn(final int column) {
        return getNextPositionAvailableInColumn(column, 5);
    }

    public char getValueAtPosition(final int column, final int row) {
        return board.getValue(column, row);
    }

    public Board getBoard() {
        return board;
    }

}
