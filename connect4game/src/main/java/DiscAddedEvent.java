/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public class DiscAddedEvent extends GameEvent {

    private final char disc;
    private final String currentPlayer;
    private final int column;
    private final int row;

    public DiscAddedEvent(final char disc, final String currentPlayer, final int column, final int row) {
        super();
        this.disc = disc;
        this.currentPlayer = currentPlayer;
        this.column = column;
        this.row = row;
    }



    @Override
    public String toString() {
        return String.format("(%s) was inserted by %s in position (%s,%s)", disc, currentPlayer, column, row);
    }

}
