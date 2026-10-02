/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A barge ("Barca"): the smallest ship of the game, occupying a single
 * position. Its bearing has no effect on the positions it occupies.
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a barge occupying only the given position.
     *
     * @param bearing the bearing of the barge (does not change the occupied
     *                position)
     * @param pos     the position occupied by the barge
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Gets the size of the barge.
     *
     * @return {@code 1}
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
