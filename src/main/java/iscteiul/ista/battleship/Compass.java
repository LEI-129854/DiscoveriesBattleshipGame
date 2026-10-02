/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Cardinal directions used as the bearing (orientation) of a ship.
 * <p>
 * Each constant is associated with a single character used when reading
 * commands from the user. Note that west is represented by {@code 'o'}
 * (from the Portuguese "oeste").
 *
 * @author fba
 */
public enum Compass {
    /** North, represented by {@code 'n'}. */
    NORTH('n'),
    /** South, represented by {@code 's'}. */
    SOUTH('s'),
    /** East, represented by {@code 'e'}. */
    EAST('e'),
    /** West, represented by {@code 'o'} (Portuguese "oeste"). */
    WEST('o'),
    /** Unknown or invalid direction, represented by {@code 'u'}. */
    UNKNOWN('u');

    private final char c;

    /**
     * Creates a compass direction.
     *
     * @param c the character that represents this direction
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Gets the character that represents this direction.
     *
     * @return the direction character ({@code n}, {@code s}, {@code e},
     *         {@code o} or {@code u})
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the direction character as a string.
     *
     * @return a one-character string with the direction character
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character into the corresponding {@link Compass} value.
     *
     * @param ch the character to convert ({@code n}, {@code s}, {@code e}
     *           or {@code o})
     * @return the matching direction, or {@link #UNKNOWN} if the character
     *         does not match any direction
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}
