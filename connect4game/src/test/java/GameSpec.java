import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author benjaminmartinez
 * Date: 2019-05-05
 */
public class GameSpec {

    /**
     * It is a two-person game, so there is one color for each player.
     * One player uses red (R) and the other one uses green (G).
     * Players alternate turns, inserting one disc every time.
     */

    private Connect4 game;

    @Before
    public void setup(){
     game = new Connect4();
    }

    /**
     * First player starts with (G)reen disc, then player2 plays (R)ed disc
     */
    @Test
    public void verifyThatPlayerTurnAlternates(){
        game.insertDisc(1);
        game.insertDisc(2);
        game.insertDisc(3);
        assertThat("First Player has Green disc", game.getValueAtPosition(1,5), Matchers.equalTo(('G')));
        assertThat("Second player has Red disc", game.getValueAtPosition(2,5), Matchers.equalTo(('R')));
        assertThat("First Player has Green disc", game.getValueAtPosition(3,5), Matchers.equalTo(('G')));

    }

    /**
     * We want feedback when either an event or an error occurs within the game.
     * The output shows the status of the board after every move.
     */

    /**
     * Event game started
     */

    /**
     * Event discAdded
     */

    /**
     * Event player swap
     */

    /**
     * Event Game draw
     */

    /**
     * Event Game won
     */

}
