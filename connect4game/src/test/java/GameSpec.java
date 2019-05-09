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
    private Output gameOutput;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        gameOutput = new Output();
        game = new Connect4GameFactory().build(gameOutput);

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

        final char[][] board = {
                {'G', 'G', 'G', 'R', 'G', 'R', 'G'},
                {'R', 'G', 'R', 'G', 'G', 'R', 'G'},
                {'R', 'G', 'R', 'G', 'R', 'R', 'R'},
                {'G', 'R', 'R', 'G', 'R', 'G', 'R'},
                {'R', 'G', 'G', 'R', 'G', 'G', 'G'},
                {'G', 'R', 'G', 'R', 'G', 'R', 'R'}
        };

        fillBoardWith(game, board);
        assertThat("When no more disc can be inserted, game is a draw", game.isDraw(), equalTo(true));

    }

    @Test
    public void whenGameIsDrawOneCannotInsertMoreDiscs() {
        whenAllDiscsAreInsertedThenGameIsDraw();
        game.insertDisc(0);
        assertThat("game is Draw", gameOutput.lastMessage(), equalTo("Game is Draw, restart a new one to play again"));

    }

    /**
     * Convenience method for inserting in each of the column of the game, one disc at a time.
     */

    private void fillBoardWith(final Connect4 game, char[][] boardfixture) {
        Board board = game.getBoard();
        for (int row = 0; row < boardfixture.length; row++) {
            for (int col = 0; col < boardfixture[0].length; col++) {
                char disc = boardfixture[row][col];
                board.insert(col, row, disc);
            }
        }

    }

    /**
     * We want feedback when either an event or an error occurs within the game.
     * The output shows the status of the board after every move.
     */

    /**
     * Event discAdded
     */

    @Test
    public void whenDiscIsAddedByPlayer1ThenGameOutputShowsDiscAdded() {
        game.insertDisc(1);
        assertThat("Disc added", gameOutput.lastMessage(), equalTo("(G) was inserted by Player1 in position (1,5)"));

    }

    @Test
    public void whenDiscIsAddedByPlayer2ThenGameOutputShowsDiscAdded() {
        game.insertDisc(1);
        game.insertDisc(1);
        assertThat("Disc added", gameOutput.lastMessage(), equalTo("(R) was inserted by Player2 in position (1,4)"));

    }

    /**
     * Event Game draw
     */
    @Test
    public void whenGAmeIsDrawOutputShowsGameIsDraw() {
        whenGameIsDrawOneCannotInsertMoreDiscs();
        assertThat("Game is a draw", gameOutput.lastMessage(), equalTo("Game is Draw, restart a new one to play again"));

    }

    /**
     * Game won
     */

    @Test
    public void testBoardOK() {
        final char[][] board = {
                {'G', 'G', 'G', 'G'}
        };
        fillBoardWith(game, board);
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardOK_1() {
        final char[][] board = {
                {'G', 'G', 'G', 'R'},
                {'G', 'G', 'G', 'G'}
        };

        fillBoardWith(game, board);
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardOK_2() {
        final char[][] board = {
                {'G', 'G', 'G', 'R'},
                {'G', 'G', 'R', 'G'},
                {'G', 'R', 'R', 'G'},
                {'G', 'G', 'R', 'G'},
                };
        fillBoardWith(game, board);
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardOK_3() {
        final char[][] board = {
                {'G', 'G', 'G', 'R'},
                {'G', 'G', 'R', 'G'},
                {'G', 'R', 'G', 'G'},
                {'R', 'G', 'R', 'G'},
                };
        fillBoardWith(game, board);
        final boolean rcur = game.isWin();
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardOK_4() {
        final char[][] board = {
                {'G', 'R', 'G', 'R'},
                {'R', 'R', 'R', 'G'},
                {'G', 'R', 'G', 'R'},
                {'R', 'G', 'R', 'R'},
                };
        fillBoardWith(game, board);
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardOK_5() {
        final char[][] board = {
                {'G', 'G', 'G', 'R', 'G'},
                {'G', 'R', 'R', 'G', 'R'},
                {'R', 'R', 'R', 'G', 'R'},
                {'G', 'R', 'G', 'R', 'G'},
                {'R', 'G', 'R', 'R', 'R'},
                };
        fillBoardWith(game, board);
        assertThat("board is a win", game.isWin(), equalTo(true));
    }

    @Test
    public void testBoardKO() {
        final char[][] board = {
                {'G', 'G', 'G', 'R'}
        };
        fillBoardWith(game, board);
        assertThat("board is not a win", game.isWin(), equalTo(false));
    }

}
