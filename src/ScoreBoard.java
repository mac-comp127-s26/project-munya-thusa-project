import edu.macalester.graphics.GraphicsText;
import java.awt.Color;

public class ScoreBoard {

    private GraphicsText scoreText;
    private int score = 0;

    /**
     * Creates a scoreboard in the top-left corner.
     */
    public ScoreBoard() {
        scoreText = new GraphicsText("Score: 0", 10, 20);
        scoreText.setFillColor(Color.WHITE);

        // Optional polish (safe in Kilt Graphics)
        scoreText.setFontSize(18);
    }

    /**
     * Increase score and update display.
     */
    public void increaseScore() {
        score += 10;
        scoreText.setText("Score: " + score);
    }

    /**
     * Returns the text object so it can be added to the canvas.
     */
    public GraphicsText getDisplay() {
        return scoreText;
    }

    public int getScore() {
        return score;
    }
}