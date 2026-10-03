/**
 *
 */
package iscteiul.ista.battleship;

/**
 * A galleon ("Galeao"): the largest ship of the game, occupying five
 * positions arranged in a "T" shape (a bar of three positions plus a stem of
 * two positions starting at the middle of the bar).
 * <p>
 * The bearing determines which way the stem points:
 * <ul>
 *   <li>{@link Compass#NORTH}: bar on the reference row, stem going down;</li>
 *   <li>{@link Compass#SOUTH}: stem on the reference column, bar two rows
 *       below, centred on the stem (it extends one column to the left of the
 *       reference position);</li>
 *   <li>{@link Compass#EAST}: vertical bar on the reference column, stem
 *       going left (it extends two columns to the left of the reference
 *       position);</li>
 *   <li>{@link Compass#WEST}: vertical bar on the reference column, stem
 *       going right.</li>
 * </ul>
 * Since some orientations use positions to the left of the reference
 * position, the ship may end up partly outside the board; the fleet rejects
 * such ships.
 */
public class Galleon extends Ship {
    private static final Integer SIZE = 5;
    private static final String NAME = "Galeao";

    /**
     * Creates a galleon at the given position and with the given bearing.
     *
     * @param bearing the bearing of the galleon, which defines its shape
     *                orientation
     * @param pos     the reference position used to place the galleon
     * @throws NullPointerException     if {@code bearing} is {@code null}
     * @throws IllegalArgumentException if {@code bearing} is not one of
     *                                  north, south, east or west
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;

            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Fills the positions for the north orientation: three positions on the
     * reference row (columns {@code c..c+2}) and two positions below the
     * middle one.
     *
     * @param pos the reference position
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Fills the positions for the south orientation: two positions on the
     * reference column (rows {@code r..r+1}) and three positions on row
     * {@code r+2} (columns {@code c-1..c+1}).
     *
     * @param pos the reference position
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Fills the positions for the east orientation: a vertical bar on the
     * reference column (rows {@code r} and {@code r+2}, plus the middle one)
     * and a stem on row {@code r+1} going to the left (columns
     * {@code c-2..c-1}).
     *
     * @param pos the reference position
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Fills the positions for the west orientation: a vertical bar on the
     * reference column (rows {@code r..r+2}) and a stem on row {@code r+1}
     * going to the right (columns {@code c+1..c+2}).
     *
     * @param pos the reference position
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

}
