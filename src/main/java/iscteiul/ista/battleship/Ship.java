/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata base de todos os navios do jogo.
 * <p>
 * Guarda a categoria, a orientação, a posição de referência e a lista de
 * posições ocupadas pelo navio, e implementa o comportamento comum a todos os
 * tipos de navio (receber tiros, verificação de colisões, limites do navio).
 * As subclasses concretas ({@link Galleon}, {@link Frigate}, {@link Carrack},
 * {@link Caravel} e {@link Barge}) definem a dimensão e preenchem as posições
 * de acordo com a sua forma.
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método de fábrica que constrói um navio do tipo pedido.
     *
     * @param shipKind o tipo de navio, em minúsculas: {@code "galeao"},
     *                 {@code "fragata"}, {@code "nau"}, {@code "caravela"} ou
     *                 {@code "barca"}
     * @param bearing  a orientação do navio
     * @param pos      a posição de referência do navio
     * @return o novo navio, ou {@code null} se {@code shipKind} não for
     *         reconhecido
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
    protected List<IPosition> positions;


    /**
     * Cria um navio. A lista de posições ocupadas começa vazia e é preenchida
     * no construtor da subclasse.
     *
     * @param category a categoria (nome) do navio
     * @param bearing  a orientação do navio, não pode ser {@code null}
     *                 (verificado com uma asserção)
     * @param pos      a posição de referência do navio, não pode ser
     *                 {@code null} (verificado com uma asserção)
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
     * Obtém as posições ocupadas pelo navio.
     *
     * @return a lista de posições ocupadas pelo navio
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
     * @param pos a posição a verificar, não pode ser {@code null} (verificado
     *            com uma asserção)
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
     * @param other o outro navio, não pode ser {@code null} (verificado com
     *              uma asserção)
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
     * Todas as posições do navio iguais a {@code pos} ficam marcadas como
     * atingidas.
     *
     * @param pos a posição onde foi disparado o tiro, não pode ser
     *            {@code null} (verificado com uma asserção)
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
     * Devolve uma representação textual do navio na forma
     * {@code [categoria orientação posição]}.
     *
     * @return a representação textual do navio
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
