import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.events.Key;


// Game class controls; create window, create snake and apple handling keyboard input, running game
// loop

public class Game {

    private CanvasWindow canvas;
    private GraphicsGroup layer;

    private Snake snake;
    private Apple apple;

    private boolean running = true;

    private int frameCounter = 0;
    private static final int SPEED = 10;

    private Key key;


    public Game() {

        // Create game window
        canvas = new CanvasWindow("Snake Game", 800, 600);

        // Group for all graphics
        layer = new GraphicsGroup();
        canvas.add(layer);

        // Create game objects
        snake = new Snake(layer);
        apple = new Apple(layer);

        // Handle keyboard input
        setupKeys();

        // Run game loop
        runGame();
    }

    // Loop to for keyboard-input

    private void setupKeys() {
        canvas.onKeyDown(event -> {

            Key key = event.getKey();

            // String[] keys = { "ArrowLeft", "ArrowUp", "ArrowRight", "ArrowDown" };
            // char[] dirs = { 'L', 'U', 'R', 'D' };

            // for (int i = 0; i < keys.length; i++) {
            //     if (key.equals(keys[i])) {
            //         snake.setDirection(dirs[i]);
            //     }
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
        });
    }


    /**
     * Simple game loop using animate()
     */
    private void runGame() {
        canvas.animate(() -> {

            if (!running)
                return;

            frameCounter++;

            if (frameCounter >= SPEED) {
                snake.move();
                snake.checkCollisionWithApple(apple);
                frameCounter = 0;
            }

            apple.draw();
        });
    }
}