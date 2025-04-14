public class Tournament {
    private final int tournamentRounds;
    private final Renderer boardRenderer;
    private final Player[] tournamentPlayers = new Player[2];
    private final int[] tournamentScores = new int[2];

    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.tournamentRounds = rounds;
        this.boardRenderer = renderer;
        this.tournamentPlayers[0] = player1;
        this.tournamentPlayers[1] = player2;
    }

    public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        for (int i = 0; i < tournamentRounds; i++) {
            Game singleGame = new Game(this.tournamentPlayers[0], this.tournamentPlayers[1], size, winStreak,
                    this.boardRenderer);
            Mark winnerMark = singleGame.run();
            updateTournamentScores(winnerMark);
        }
        printTournamentResults(playerName1, playerName2);
    }

    private void updateTournamentScores(Mark winnerMark) {
        if (winnerMark == Mark.X) {
            this.tournamentScores[0]++;
        }
        else if (winnerMark == Mark.O) {
            this.tournamentScores[1]++;
        }
    }

    private void printTournamentResults(String playerName1, String playerName2) {
        System.out.println("######### Results #########");
        System.out.println(String.format("Player 1, %s won: %d rounds", playerName1,
                this.tournamentScores[0]));
        System.out.println(String.format("Player 2, %s won: %d rounds", playerName2,
                this.tournamentScores[1]));

        int tournamentTies = this.tournamentRounds - this.tournamentScores[0] - this.tournamentScores[1];
        System.out.println(String.format("Ties: %d", tournamentTies));
    }

    public static void main(String[] args) {
        int parsedRounds = Integer.parseInt(args[0]);
        int parsedSize = Integer.parseInt(args[1]);
        int parsedWinStreak = Integer.parseInt(args[2]);
        String parsedRenderTarget = args[3];
        String parsedPlayer1Name = args[4];
        String parsedPlayer2Name = args[5];

        RendererFactory rendererFactory = new RendererFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        Renderer renderer = rendererFactory.buildRenderer(parsedRenderTarget, parsedSize);
        Player player1 = playerFactory.buildPlayer(parsedPlayer1Name);
        Player player2 = playerFactory.buildPlayer(parsedPlayer2Name);

        // construct tournament
        Tournament tournament = new Tournament(parsedRounds, renderer, player1, player2);
        tournament.playTournament(parsedSize, parsedWinStreak, parsedPlayer1Name, parsedPlayer2Name);
    }
}
