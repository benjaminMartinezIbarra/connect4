import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        game = new Connect4();
    }

    /**
     * First player starts with (G)reen disc, then player2 plays (R)ed disc
     */
    @Test
    public void verifyThatPlayerTurnAlternates() {
        game.insertDisc(1);
        game.insertDisc(2);
        game.insertDisc(3);
        assertThat("First Player has Green disc", game.getValueAtPosition(1, 5), equalTo(('G')));
        assertThat("Second player has Red disc", game.getValueAtPosition(2, 5), equalTo(('R')));
        assertThat("First Player has Green disc", game.getValueAtPosition(3, 5), equalTo(('G')));

    }

    /**
     * When no more discs can be inserted, the game finishes, and it is considered a draw.
     */

    @Test
    public void whenAllDiscsAreInsertedThenGameIsDraw() {
        fillBoard();
        Assert.assertTrue("When no more disc can be inserted, game is a draw",
                          game.isDraw());

    }

    @Test
    public void whenGameIsDrawOneCannotInsertMoreDiscs() {
        whenAllDiscsAreInsertedThenGameIsDraw();
        expectedException.expectMessage("game is Draw, restart a new one to play again");
        game.insertDisc(0);

    }

    /**
     * Convenience method for inserting in each of the column of the game, one disc at a time.
     */
    private void fillBoard() {

        for (int i = 0; i <= 5; i++) {
            game.insertDisc(0);
            game.insertDisc(1);
            game.insertDisc(2);
            game.insertDisc(3);
            game.insertDisc(4);
            game.insertDisc(5);
            game.insertDisc(6);
        }
    }

    /**
     * We want feedback when either an event or an error occurs within the game.
     * The output shows the status of the board after every move.
     */

    /**
     * Event game started
     */
    @Test
    public void whenGameIsStartedThenGameOutputIsGameStarted() {
        assertThat("game is started at creation", game.LastGameEvent(), equalTo("Game started"));

    }

    /**
     * Event discAdded
     */

    @Test
    public void whenDiscIsAddedByPlayer1ThenGameOutputShowsDiscAdded() {
        game.insertDisc(1);
        assertThat("Disc added", game.LastGameEvent(), equalTo("(G) was inserted by Player1 in position (1,5)"));

    }

    @Test
    public void whenDiscIsAddedByPlayer2ThenGameOutputShowsDiscAdded() {
        game.insertDisc(1);
        game.insertDisc(1);
        assertThat("Disc added", game.LastGameEvent(), equalTo("(R) was inserted by Player2 in position (1,4)"));

    }

    /**
     * Event Game draw
     */
    @Test
    public void whenGAmeIsDrawOutputShowsGameIsDraw(){
        whenGameIsDrawOneCannotInsertMoreDiscs();
        assertThat("Game is a draw", game.LastGameEvent(), equalTo("Game is DRAW"));

    }

    /**
     * Event Game won
     */

}
