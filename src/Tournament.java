/**
 * This is responsible for hosting a tournament, with 2 players where their types are determined by the user.
 */
public class Tournament {
    private static final int PLAYER_COUNT = 2;
    private static final int INDEX_PLAYER1 = 0;
    private static final int INDEX_PLAYER2 = 1;

    private static final int ARG_ROUNDS = 0;
    private static final int ARG_BOARD_SIZE = 1;
    private static final int ARG_WIN_STREAK = 2;
    private static final int ARG_RENDER_TARGET = 3;
    private static final int ARG_PLAYER1_NAME = 4;
    private static final int ARG_PLAYER2_NAME = 5;

    private final int tournamentRounds;
    private final Renderer boardRenderer;
    private final Player[] tournamentPlayers = new Player[PLAYER_COUNT];
    private final int[] tournamentScores = new int[PLAYER_COUNT];

    /**
     * Tournament constructor, accepting parameters and initializes tournament instance accordingly.
     * @param rounds Number of rounds desired.
     * @param renderer Renderer type for the board.
     * @param player1 First Player instance, can be human/whatever/clever/genius.
     * @param player2 Second Player instance, can be human/whatever/clever/genius.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.tournamentRounds = rounds;
        this.boardRenderer = renderer;
        this.tournamentPlayers[INDEX_PLAYER1] = player1;
        this.tournamentPlayers[INDEX_PLAYER2] = player2;
    }

    /**
     * Starts the tournament, manages each game play, and announces summary results at the end.
     * @param size Size for the board for all games.
     * @param winStreak The streak needed to win each game.
     * @param playerName1 Name for the first player.
     * @param playerName2 Name for the second player.
     */
    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        for (int i = 0; i < tournamentRounds; i++) {
            Game singleGame = new Game(
                    this.tournamentPlayers[INDEX_PLAYER1],
                    this.tournamentPlayers[INDEX_PLAYER2],
                    size,
                    winStreak,
                    this.boardRenderer
            );
            Mark winnerMark = singleGame.run();
            updateTournamentScores(winnerMark);
        }
        printTournamentResults(playerName1, playerName2);
    }

    private void updateTournamentScores(Mark winnerMark) {
        if (winnerMark == Mark.X) {
            this.tournamentScores[INDEX_PLAYER1]++;
        } else if (winnerMark == Mark.O) {
            this.tournamentScores[INDEX_PLAYER2]++;
        }
    }

    private void printTournamentResults(String playerName1, String playerName2) {
        System.out.println("######### Results #########");
        System.out.println(String.format("Player 1, %s won: %d rounds", playerName1,
                this.tournamentScores[INDEX_PLAYER1]));
        System.out.println(String.format("Player 2, %s won: %d rounds", playerName2,
                this.tournamentScores[INDEX_PLAYER2]));

        int tournamentTies = this.tournamentRounds
                - this.tournamentScores[INDEX_PLAYER1]
                - this.tournamentScores[INDEX_PLAYER2];
        System.out.println(String.format("Ties: %d", tournamentTies));
    }

    /**
     * The main function of the tournament program, it creates and runs one instance of tournament
     * depending on settings received as input from the user.
     * @param args args contain settings for the tournament, supplied by the user.
     */
    public static void main(String[] args) {
        int parsedRounds = Integer.parseInt(args[ARG_ROUNDS]);
        int parsedSize = Integer.parseInt(args[ARG_BOARD_SIZE]);
        int parsedWinStreak = Integer.parseInt(args[ARG_WIN_STREAK]);
        String parsedRenderTarget = args[ARG_RENDER_TARGET];
        String parsedPlayer1Name = args[ARG_PLAYER1_NAME];
        String parsedPlayer2Name = args[ARG_PLAYER2_NAME];

        RendererFactory rendererFactory = new RendererFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        Renderer renderer = rendererFactory.buildRenderer(parsedRenderTarget, parsedSize);
        Player player1 = playerFactory.buildPlayer(parsedPlayer1Name);
        Player player2 = playerFactory.buildPlayer(parsedPlayer2Name);

        Tournament tournament = new Tournament(parsedRounds, renderer, player1, player2);
        tournament.playTournament(parsedSize, parsedWinStreak, parsedPlayer1Name, parsedPlayer2Name);
    }
}
