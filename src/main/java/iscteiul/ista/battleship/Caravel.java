/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A caravel ("Caravela"): a ship that occupies two consecutive positions in a
 * straight line.
 * <p>
 * With bearing {@link Compass#NORTH} or {@link Compass#SOUTH} it extends
 * downwards from the reference position (increasing rows); with
 * {@link Compass#EAST} or {@link Compass#WEST} it extends to the right
 * (increasing columns).
 */
public class Caravel extends Ship {
    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Creates a caravel starting at the given position.
     *
     * @param bearing the bearing where the Caravel heads to
     * @param pos     initial point for positioning the Caravel
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  north, south, east or west
     */

    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
