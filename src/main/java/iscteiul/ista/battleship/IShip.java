/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contrato de um navio no jogo da Batalha Naval das Descobertas.
 * <p>
 * Um navio tem uma categoria (por exemplo "Galeao", "Fragata"), uma dimensão,
 * uma orientação e a lista de posições do tabuleiro que ocupa. Pode ser
 * atingido por tiros e sabe indicar se ainda flutua, se ocupa uma dada posição
 * e se está demasiado perto de outro navio ou de uma posição.
 */
public interface IShip {
    /**
     * Obtém a categoria (nome) do navio, por exemplo "Galeao" ou "Barca".
     *
     * @return a categoria do navio
     */
    String getCategory();

    /**
     * Obtém a dimensão do navio, isto é, o número de posições que ocupa.
     *
     * @return o número de posições do navio
     */
    Integer getSize();

    /**
     * Obtém todas as posições ocupadas pelo navio.
     *
     * @return a lista de posições ocupadas pelo navio
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição de referência usada para colocar o navio no tabuleiro.
     *
     * @return a posição inicial do navio
     */
    IPosition getPosition();

    /**
     * Obtém a orientação (rumo) do navio.
     *
     * @return a orientação do navio
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda flutua, isto é, se pelo menos uma das suas
     * posições ainda não foi atingida.
     *
     * @return {@code true} se pelo menos uma posição não foi atingida,
     *         {@code false} se o navio já foi afundado
     */
    boolean stillFloating();

    /**
     * Obtém a menor linha ocupada pelo navio.
     *
     * @return a linha mais acima ocupada pelo navio
     */
    int getTopMostPos();

    /**
     * Obtém a maior linha ocupada pelo navio.
     *
     * @return a linha mais abaixo ocupada pelo navio
     */
    int getBottomMostPos();

    /**
     * Obtém a menor coluna ocupada pelo navio.
     *
     * @return a coluna mais à esquerda ocupada pelo navio
     */
    int getLeftMostPos();

    /**
     * Obtém a maior coluna ocupada pelo navio.
     *
     * @return a coluna mais à direita ocupada pelo navio
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa a posição indicada.
     *
     * @param pos a posição a verificar, não pode ser {@code null}
     * @return {@code true} se o navio ocupa a posição
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado perto de outro, isto é, se alguma
     * posição do outro navio é adjacente a uma posição deste navio.
     *
     * @param other o outro navio, não pode ser {@code null}
     * @return {@code true} se os navios se tocam ou são adjacentes
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se este navio está demasiado perto de uma dada posição.
     *
     * @param pos a posição a verificar
     * @return {@code true} se alguma posição do navio é adjacente a
     *         {@code pos}
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um tiro no navio. Se a posição pertencer ao navio, essa posição
     * fica marcada como atingida.
     *
     * @param pos a posição onde foi disparado o tiro, não pode ser
     *            {@code null}
     */
    void shoot(IPosition pos);
}
