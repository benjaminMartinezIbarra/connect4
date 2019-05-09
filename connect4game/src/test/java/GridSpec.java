import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Specification for Grid construction for connect 4 game.
 *
 * @author benjaminmartinez
 * Date: 2019-05-04
 */
public class GridSpec {

    Connect4 game;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        game = new Connect4();

    }

    /**
     * The board is composed of seven columns and six rows;
     */
    @Test
    public void whenGameIsStartedThenBoardIsSetWith7ColumnsAnd6Rows() {
        assertThat("7 columns in connect 4 game", game.getBoard().numberOfColumns(),
                   equalTo(7));
        assertThat("6 rows in connect 4 game", game.getBoard().numberOfRows() == 6);
    }

    /**
     * all positions are empty
     */
    @Test
    public void whenGameIsStartedThenBoardIsSetWithEmptyValues() {
        Assert.assertTrue("each position must be empty", game.getBoard().isEmpty());
    }

    /**
     * Players introduce discs on the top of the columns.
     * The introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over the previous ones.
     */
    @Test
    public void whenPlayerIntroduceDiscInColumn1ThenLastPositionInThatColumnIsBeforeLast() {
        game.insertDisc(0);
        assertThat(game.getNextPositionAvailableInColumn(0), equalTo(4));
        assertThat("value at position is X", game.getValueAtPosition(0, 5), equalTo('G'));
    }

    @Test
    public void whenPlayerIntroduceDiscInColumn2ThenLastPositionInThatColumnBeforeLast() {
        game.insertDisc(1);
        assertThat(game.getNextPositionAvailableInColumn(1), equalTo(4));
        assertThat(game.getValueAtPosition(1, 5), equalTo('G'));
    }

    @Test
    public void whenPlayerIntroduceNoDiscInColumnThenLastPositionInThatColumnIsLast() {
        assertThat(game.getNextPositionAvailableInColumn(2), equalTo(5));
        assertThat(game.getValueAtPosition(2, 5), equalTo(Character.MIN_VALUE));
    }

    @Test
    public void whenPlayerCompleteAColumnWhitDiscThenHeCannotAddMoreDiscToIt() {
        game.insertDisc(1);
        game.insertDisc(1);
        game.insertDisc(1);
        game.insertDisc(1);
        game.insertDisc(1);
        game.insertDisc(1);
        expectedException.expectMessage("no available positions for column 1");
        game.insertDisc(1);
    }

    @Test
    public void verifyValueAtPosition() {
        game.insertDisc(1);
        game.insertDisc(1);
        game.insertDisc(1);
        assertThat(game.getValueAtPosition(1, 5), equalTo('G'));
        assertThat(game.getValueAtPosition(1, 4), equalTo('R'));
        assertThat(game.getValueAtPosition(1, 3), equalTo('G'));
    }

}
