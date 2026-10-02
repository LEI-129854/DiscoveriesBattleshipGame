/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contract for a ship in the Discoveries Battleship Game.
 * <p>
 * A ship has a category (e.g. "Galeao", "Fragata"), a size, a bearing and the
 * list of board positions it occupies. It can be shot, and it can tell whether
 * it is still floating, whether it occupies a given position and whether it is
 * too close to another ship or position.
 */
public interface IShip {
    /**
     * Gets the category (name) of the ship, e.g. "Galeao" or "Barca".
     *
     * @return the ship category
     */
    String getCategory();

    /**
     * Gets the size of the ship, i.e. the number of positions it occupies.
     *
     * @return the number of positions of the ship
     */
    Integer getSize();

    /**
     * Gets all the positions occupied by the ship.
     *
     * @return the list of positions occupied by the ship
     */
    List<IPosition> getPositions();

    /**
     * Gets the reference position used to place the ship on the board.
     *
     * @return the initial position of the ship
     */
    IPosition getPosition();

    /**
     * Gets the bearing (orientation) of the ship.
     *
     * @return the bearing of the ship
     */
    Compass getBearing();

    /**
     * Checks whether the ship is still floating, i.e. whether at least one of
     * its positions has not been hit.
     *
     * @return {@code true} if at least one position has not been hit,
     *         {@code false} if the ship has sunk
     */
    boolean stillFloating();

    /**
     * Gets the smallest row occupied by the ship.
     *
     * @return the topmost row of the ship
     */
    int getTopMostPos();

    /**
     * Gets the largest row occupied by the ship.
     *
     * @return the bottommost row of the ship
     */
    int getBottomMostPos();

    /**
     * Gets the smallest column occupied by the ship.
     *
     * @return the leftmost column of the ship
     */
    int getLeftMostPos();

    /**
     * Gets the largest column occupied by the ship.
     *
     * @return the rightmost column of the ship
     */
    int getRightMostPos();

    /**
     * Checks whether the ship occupies the given position.
     *
     * @param pos the position to check, must not be {@code null}
     * @return {@code true} if the ship occupies the position
     */
    boolean occupies(IPosition pos);

    /**
     * Checks whether this ship is too close to another ship, i.e. whether any
     * position of the other ship is adjacent to a position of this ship.
     *
     * @param other the other ship, must not be {@code null}
     * @return {@code true} if the ships touch or are adjacent
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks whether this ship is too close to a given position.
     *
     * @param pos the position to check
     * @return {@code true} if any position of the ship is adjacent to
     *         {@code pos}
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a shot on the ship. If the position belongs to the ship, that
     * position is marked as hit.
     *
     * @param pos the position that was shot, must not be {@code null}
     */
    void shoot(IPosition pos);
}
