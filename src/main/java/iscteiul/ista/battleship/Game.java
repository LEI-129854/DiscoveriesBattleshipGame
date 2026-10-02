/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of {@link IGame}.
 * <p>
 * A game holds the fleet being attacked, the list of valid shots already fired
 * and counters for invalid, repeated and successful shots and for sunk ships.
 * <p>
 * <b>Note:</b> the constructor only initialises the invalid and repeated
 * shot counters; {@code countHits} and {@code countSinks} are not initialised
 * there.
 *
 * @author fba
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Creates a game against the given fleet, with no shots fired yet.
     *
     * @param fleet the fleet that will be attacked
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * {@inheritDoc}
     * <p>
     * An invalid shot increments the invalid counter and a repeated shot
     * increments the repeated counter; neither affects the fleet. A valid new
     * shot is recorded, and if it hits a ship the hit counter is incremented.
     * If the ship sinks, the sunk counter is incremented and the ship is
     * returned.
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Checks whether a shot position is considered valid, i.e. its row and
     * column are not negative and do not exceed {@code Fleet.BOARD_SIZE}.
     *
     * @param pos the position of the shot
     * @return {@code true} if the position is considered valid
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks whether a position has already been shot.
     *
     * @param pos the position to check
     * @return {@code true} if the position is in the list of previous shots
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }


    /**
     * Prints a {@code BOARD_SIZE x BOARD_SIZE} board to the standard output,
     * using {@code '.'} for empty cells and the given marker for the given
     * positions.
     *
     * @param positions the positions to mark
     * @param marker    the character used to mark those positions
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Prints the board showing valid shots that have been fired
     * (marked with {@code 'X'}).
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Prints the board showing the fleet (ship positions marked with
     * {@code '#'}).
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
