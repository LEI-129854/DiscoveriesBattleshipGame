/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Default implementation of {@link IPosition}.
 * <p>
 * Stores the row and column of a board cell and two flags: whether the cell is
 * occupied by a ship and whether it has been hit.
 * <p>
 * <b>Note:</b> {@link #equals(Object)} only compares row and column, while
 * {@link #hashCode()} also uses the {@code isHit} and {@code isOccupied}
 * flags, so two equal positions may have different hash codes.
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Creates a position that is neither occupied nor hit.
     *
     * @param row    the row of the position
     * @param column the column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumn() {
        return column;
    }


    /**
     * Computes a hash code from the row, column, occupied and hit flags.
     *
     * @return the hash code of this position
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Any {@link IPosition} with the same row and column is considered equal,
     * regardless of the occupied and hit flags.
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a textual representation in the form
     * {@code Linha = <row> Coluna = <column>}.
     *
     * @return the string representation of the position
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
