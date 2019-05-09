/**
 * @author benjaminmartinez
 * Date: 2019-05-06
 */
public class GameWinEvent extends GameEvent {

    private final String playerWin;

    public GameWinEvent(final String player) {
        super();
        this.playerWin = player;
    }

    @Override
    public String toString(){
        return String.format("Player %s won the Game!", playerWin);
    }
}
