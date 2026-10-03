/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A carrack ("Nau"): a ship that occupies three consecutive positions in a
 * straight line.
 * <p>
 * With bearing {@link Compass#NORTH} or {@link Compass#SOUTH} it extends
 * downwards from the reference position (increasing rows); with
 * {@link Compass#EAST} or {@link Compass#WEST} it extends to the right
 * (increasing columns).
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a carrack starting at the given position.
     *
     * @param bearing the bearing of the carrack
     * @param pos     the initial position of the carrack
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  north, south, east or west
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
