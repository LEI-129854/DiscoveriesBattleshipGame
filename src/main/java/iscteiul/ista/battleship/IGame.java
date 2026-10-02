/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contract for a game round played against a fleet.
 * <p>
 * It allows firing at positions and querying statistics about the shots
 * (hits, repeated, invalid) and about the state of the fleet.
 */
public interface IGame {
    /**
     * Fires a shot at the given position.
     *
     * @param pos the position to shoot at
     * @return the ship that was sunk by this shot, or {@code null} if the shot
     *         was invalid, repeated, missed, or hit a ship without sinking it
     */
    IShip fire(IPosition pos);

    /**
     * Gets the valid, non-repeated shots fired so far.
     *
     * @return the list of shots
     */
    List<IPosition> getShots();

    /**
     * Gets the number of repeated shots, i.e. valid shots at a position that
     * had already been shot.
     *
     * @return the number of repeated shots
     */
    int getRepeatedShots();

    /**
     * Gets the number of invalid shots, i.e. shots outside the board.
     *
     * @return the number of invalid shots
     */
    int getInvalidShots();

    /**
     * Gets the number of shots that hit a ship.
     *
     * @return the number of hits
     */
    int getHits();

    /**
     * Gets the number of ships sunk so far.
     *
     * @return the number of sunk ships
     */
    int getSunkShips();

    /**
     * Gets the number of ships still floating.
     *
     * @return the number of remaining ships
     */
    int getRemainingShips();

    /**
     * Prints the board showing the valid shots that have been fired.
     */
    void printValidShots();

    /**
     * Prints the board showing the fleet.
     */
    void printFleet();
}
