/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Console-driven tasks used to exercise the game incrementally: building
 * ships (task A), building fleets (tasks B and C) and firing rounds of shots
 * (task D).
 * <p>
 * All input is read from the standard input through a {@link Scanner} and all
 * output is sent to a Log4j logger. Commands typed by the user are in
 * Portuguese: {@code nova}, {@code desisto}, {@code rajada}, {@code ver},
 * {@code mapa} and {@code estado}.
 */
public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int NUMBER_SHOTS = 3;

    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /**
     * Strings to be used by the user
     */
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * This task tests the building up of ships: For each ship, reads positions and
     * indicates whether the ship occupies each one of such positions or not
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * This task tests the building up of fleets.
     * <p>
     * Accepted commands: {@code nova} (build a new fleet), {@code estado}
     * (print the fleet status) and {@code desisto} (quit).
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This task tests the building up of fleets and takes into consideration the
     * possibility of cheating.
     * <p>
     * Accepts the commands of {@link #taskB()} plus {@code mapa}, which logs
     * the fleet.
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This task also tests the fighting element of a round of three shots.
     * <p>
     * Accepts the commands {@code nova} (new fleet and game), {@code estado}
     * (fleet status), {@code mapa} (print the fleet), {@code rajada} (fire a
     * round of three shots), {@code ver} (print the valid shots) and
     * {@code desisto} (quit). After each round it logs the hits, invalid and
     * repeated shots and the number of remaining ships.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * This operation allows the build up of a fleet, given user data.
     * <p>
     * Ships are read and added to the fleet until the loop counter, which
     * counts the successfully added ships, exceeds {@code Fleet.FLEET_SIZE}.
     * Ships that are unknown or cannot be added are reported and skipped.
     *
     * @param in The scanner to read from, must not be {@code null} (checked
     *           with an assertion)
     * @return The fleet that has been built
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * This operation reads data about a ship, build it and returns it.
     * <p>
     * The expected input is the ship kind, the row, the column and the bearing
     * character.
     *
     * @param in The scanner to read from
     * @return The created ship based on the data that has been read, or
     *         {@code null} if the ship kind is unknown
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * This operation allows reading a position in the map.
     * <p>
     * The expected input is two integers: the row and the column.
     *
     * @param in The scanner to read from
     * @return The position that has been read
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * This operation allows firing a round of shots (three) over a fleet, in the
     * context of a game. If a shot sinks a ship, a message is logged.
     *
     * @param in   The scanner to read from
     * @param game The context game while fleet is being attacked
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
