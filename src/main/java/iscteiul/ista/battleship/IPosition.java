/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Contract for a position (cell) on the game board.
 * <p>
 * A position is identified by its row and column, and keeps track of whether
 * it is occupied by a ship and whether it has been hit.
 *
 * @author fba
 */
public interface IPosition {
    /**
     * Gets the row of the position.
     *
     * @return the row (0-based)
     */
    int getRow();

    /**
     * Gets the column of the position.
     *
     * @return the column (0-based)
     */
    int getColumn();

    /**
     * Compares this position with another object. Two positions are equal if
     * they have the same row and column.
     *
     * @param other the object to compare with
     * @return {@code true} if {@code other} is a position with the same row
     *         and column
     */
    boolean equals(Object other);

    /**
     * Checks whether this position is adjacent to another one, including
     * diagonally.
     *
     * @param other the other position
     * @return {@code true} if the row and column differ by at most one unit
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marks the position as occupied by a ship.
     */
    void occupy();

    /**
     * Marks the position as hit by a shot.
     */
    void shoot();

    /**
     * Checks whether the position is occupied by a ship.
     *
     * @return {@code true} if the position has been marked as occupied
     */
    boolean isOccupied();

    /**
     * Checks whether the position has been hit.
     *
     * @return {@code true} if the position has been shot
     */
    boolean isHit();
}
