/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contract for a fleet of ships belonging to one player.
 * <p>
 * A fleet keeps the ships placed on a square board and provides queries over
 * them (by category, by position, floating ships).
 */
public interface IFleet {
    /** Side length of the (square) board, in positions. */
    Integer BOARD_SIZE = 10;

    /** Number of ships that make up a fleet. */
    Integer FLEET_SIZE = 10;

    /**
     * Gets all the ships of the fleet.
     *
     * @return the list of ships in the fleet
     */
    List<IShip> getShips();

    /**
     * Tries to add a ship to the fleet. The ship is only added if it lies
     * entirely inside the board, does not collide with (or touch) any ship
     * already in the fleet and the fleet is not full.
     *
     * @param s the ship to add
     * @return {@code true} if the ship was added, {@code false} otherwise
     */
    boolean addShip(IShip s);

    /**
     * Gets the ships of a given category.
     *
     * @param category the category to look for, e.g. "Galeao" or "Nau"
     * @return the list of ships of that category (empty if there are none)
     */
    List<IShip> getShipsLike(String category);

    /**
     * Gets the ships that are still floating, i.e. not yet sunk.
     *
     * @return the list of floating ships
     */
    List<IShip> getFloatingShips();

    /**
     * Gets the ship that occupies a given position.
     *
     * @param pos the position to check
     * @return the ship at that position, or {@code null} if there is none
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the status of the fleet: all ships, floating ships and ships
     * grouped by category.
     */
    void printStatus();
}
