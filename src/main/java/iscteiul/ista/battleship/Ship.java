/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for all ships of the game.
 * <p>
 * It stores the category, bearing, reference position and the list of
 * positions occupied by the ship, and implements the behaviour common to every
 * kind of ship (shooting, collision checks, bounding limits). Concrete
 * subclasses ({@link Galleon}, {@link Frigate}, {@link Carrack},
 * {@link Caravel} and {@link Barge}) define the size and fill the positions
 * according to their shape.
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Factory method that builds a ship of the requested kind.
     *
     * @param shipKind the kind of ship, in lower case: {@code "galeao"},
     *                 {@code "fragata"}, {@code "nau"}, {@code "caravela"} or
     *                 {@code "barca"}
     * @param bearing  the bearing of the ship
     * @param pos      the reference position of the ship
     * @return the new ship, or {@code null} if {@code shipKind} is not
     *         recognised
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    /** Positions occupied by the ship, filled by the subclasses. */
    protected List<IPosition> positions;


    /**
     * Creates a ship. The list of occupied positions starts empty and is
     * filled by the subclass constructor.
     *
     * @param category the category (name) of the ship
     * @param bearing  the bearing of the ship, must not be {@code null}
     *                 (checked with an assertion)
     * @param pos      the reference position of the ship, must not be
     *                 {@code null} (checked with an assertion)
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Gets the positions occupied by the ship.
     *
     * @return the list of positions occupied by the ship
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * {@inheritDoc}
     *
     * @param pos the position to check, must not be {@code null} (checked with
     *            an assertion)
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param other the other ship, must not be {@code null} (checked with an
     *              assertion)
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Every position of the ship equal to {@code pos} is marked as hit.
     *
     * @param pos the position that was shot, must not be {@code null}
     *            (checked with an assertion)
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }


    /**
     * Returns a textual representation of the ship in the form
     * {@code [category bearing position]}.
     *
     * @return the string representation of the ship
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
