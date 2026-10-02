/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Pontos cardeais usados como orientação (rumo) de um navio.
 * <p>
 * Cada constante está associada a um carácter, usado na leitura dos comandos
 * introduzidos pelo utilizador. Note-se que o oeste é representado por
 * {@code 'o'}.
 *
 * @author fba
 */
public enum Compass {
    /** Norte, representado por {@code 'n'}. */
    NORTH('n'),
    /** Sul, representado por {@code 's'}. */
    SOUTH('s'),
    /** Este, representado por {@code 'e'}. */
    EAST('e'),
    /** Oeste, representado por {@code 'o'}. */
    WEST('o'),
    /** Direção desconhecida ou inválida, representada por {@code 'u'}. */
    UNKNOWN('u');

    private final char c;

    /**
     * Cria uma direção da bússola.
     *
     * @param c o carácter que representa esta direção
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Obtém o carácter que representa esta direção.
     *
     * @return o carácter da direção ({@code n}, {@code s}, {@code e},
     *         {@code o} ou {@code u})
     */
    public char getDirection() {
        return c;
    }

    /**
     * Devolve o carácter da direção sob a forma de cadeia de caracteres.
     *
     * @return uma cadeia com o carácter da direção
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter no valor {@link Compass} correspondente.
     *
     * @param ch o carácter a converter ({@code n}, {@code s}, {@code e} ou
     *           {@code o})
     * @return a direção correspondente, ou {@link #UNKNOWN} se o carácter não
     *         corresponder a nenhuma direção
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
