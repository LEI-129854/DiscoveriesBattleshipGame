/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A frigate ("Fragata"): a ship that occupies four consecutive positions in a
 * straight line.
 * <p>
 * With bearing {@link Compass#NORTH} or {@link Compass#SOUTH} it extends
 * downwards from the reference position (increasing rows); with
 * {@link Compass#EAST} or {@link Compass#WEST} it extends to the right
 * (increasing columns).
 */
public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a frigate starting at the given position.
     *
     * @param bearing the bearing of the frigate
     * @param pos     the initial position of the frigate
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  north, south, east or west
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}
