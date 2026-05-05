import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsText;
import java.awt.Color;



// Game class controls; create window, create snake and apple handling keyboard input, running game
// loop

public class Game {

    private CanvasWindow canvas;
    private GraphicsGroup layer;

    private Snake snake;
    private Apple apple;

    private boolean running = true;

    private int frameCounter = 0;
    private static final int speed = 10;

    private int score = 0;

    private GraphicsText scoreText;
    private GraphicsText gameOverText;
    private GraphicsText restartText;
    private GraphicsText exitText;

    private static final int tileSize = 20;

    private Key key;


    public Game() {

        // Create game window
        canvas = new CanvasWindow("Snake Game", 800, 600);

        // Group for all graphics
        layer = new GraphicsGroup();
        canvas.add(layer);
        drawBackground();

        // Create game objects
        snake = new Snake(layer);
        apple = new Apple(layer);

        scoreText = new GraphicsText("Score: 0");
        scoreText.setPosition(10, 20);

        // styling
        scoreText.setFillColor(Color.WHITE);
        scoreText.setFontSize(20);
        // scoreText.setFont("Comic Sans MS");

        layer.add(scoreText);

        // Handle keyboard input
        setupKeys();

        runGame();
    }

    // Loop to for keyboard-input

    private void setupKeys() {
        canvas.onKeyDown(event -> {

            Key key = event.getKey();

            // String[] keys = { "ArrowLeft", "ArrowUp", "ArrowRight", "ArrowDown" };
            // char[] dirs = { 'L', 'U', 'R', 'D' };

            // for (int i = 0; i < keys.length; i++) {
            // if (key.equals(keys[i])) {
            // snake.setDirection(dirs[i]);
            // }
            // }

            if (key == Key.LEFT_ARROW) {
                snake.setDirection('L');
            } else if (key == Key.UP_ARROW) {
                snake.setDirection('U');
            } else if (key == Key.RIGHT_ARROW) {
                snake.setDirection('R');
            } else if (key == Key.DOWN_ARROW) {
                snake.setDirection('D');
            }
            // System.out.println("setupKeys is running");
            if (!running) {

            if (key == Key.RETURN_OR_ENTER) {
                restartGame();
            } else if (key == Key.ESCAPE) {
                canvas.closeWindow();
            }

            return;
        }
        });
    }


    
    //  Simple game loop using animate()
    private void runGame() {
        canvas.animate(() -> {

            if (!running)
                return;

            frameCounter++;

            if (frameCounter >= speed) {
                if (!snake.move()) {
                    endGame();
                }
                // snake.checkCollisionWithApple(apple);
                if (snake.checkCollisionWithApple(apple)) {
                    score += 10;
                    scoreText.setText("Score: " + score);
                }
                frameCounter = 0;
            }

            apple.draw(); 
        });
    }

    private void endGame() {
        running = false;
        showGameOverScreen();
    }

    private void drawBackground() {

        int cols = 800 / tileSize;
        int rows = 600 / tileSize;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                double x = col * tileSize;
                double y = row * tileSize;

                Rectangle tile = new Rectangle(x, y, tileSize, tileSize);

                // alternate colors
                if ((row + col) % 2 == 0) {
                    tile.setFillColor(Color.BLACK);
                } else {
                    tile.setFillColor(Color.DARK_GRAY);
                }

                layer.add(tile);
            }
        }
    }

    private void showGameOverScreen() {

        // GAME OVER title
        gameOverText = new GraphicsText("GAME OVER");
        gameOverText.setFontSize(50);
        gameOverText.setFillColor(Color.WHITE);

        // center it
        gameOverText.setPosition(250, 250);

        // Restart instruction
        restartText = new GraphicsText("Press ENTER to restart");
        restartText.setFontSize(20);
        restartText.setFillColor(Color.GREEN);
        restartText.setPosition(250, 300);

        // Exit instruction
        exitText = new GraphicsText("Press ESC to exit");
        exitText.setFontSize(20);
        exitText.setFillColor(Color.RED);
        exitText.setPosition(250, 330);

        layer.add(gameOverText);
        layer.add(restartText);
        layer.add(exitText);
    }

    private void restartGame() {

        // clear everything
        layer.removeAll();

        // reset state
        running = true;
        score = 0;
        frameCounter = 0;

        // redraw background
        drawBackground();

        // recreate objects
        snake = new Snake(layer);
        apple = new Apple(layer);

        // reset score display
        scoreText = new GraphicsText("Score: 0");
        scoreText.setPosition(10, 20);
        scoreText.setFillColor(Color.WHITE);
        scoreText.setFontSize(20);

        layer.add(scoreText);
    }


}