package entity;


public class Scoreboard {

    private int player1Score = 0;
    private int player2Score = 0;

    private ScoreList scoreList; // add this

    private final int MAX_POINTS = 10;

    // Constructor takes ScoreList instance
    public Scoreboard(ScoreList scoreList) {
        this.scoreList = scoreList;
    }

    // Player 1 scores a point
    public void player1Scores() {
        player1Score++;
        System.out.println("🟦 Player 1 scores! Total: " + player1Score);
        checkSetEnd();
    }

    // Player 2 scores a point
    public void player2Scores() {
        player2Score++;
        System.out.println("🟥 Player 2 scores! Total: " + player2Score);
        checkSetEnd();
    }
    
    public void displayScore() {
        System.out.println("Score: " + player1Score + " |: " + player2Score);
    }

    // Check if set ended (one player reaches MAX_POINTS)
    private void checkSetEnd() {
        if (player1Score >= MAX_POINTS || player2Score >= MAX_POINTS) {
            // Save the finished set to ScoreList
            scoreList.addSet(player1Score, player2Score);

            // Reset scores for next set
            player1Score = 0;
            player2Score = 0;
        }
    }

    // Getters for current scores
    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }
}