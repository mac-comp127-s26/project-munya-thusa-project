import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;


import java.awt.Color;
import java.util.ArrayList;

/**
 * Main class for Snake Xenzia+ game. Controls game loop, rendering, and interactions between
 * objects.
 */
public class SnakeGame {

    // Canvas dimensions and grid size
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int CELL = 20;

    // Core game objects
    private CanvasWindow canvas;
    private Snake snake;
    private Food food;
    private ArrayList<Obstacle> obstacles;
    private ScoreBoard scoreBoard;

    // Game configuration and state
    private GameLevel level;
    private GameState state = GameState.RUNNING;

    /**
     * Sets up the game world, initializes objects, and starts the game loop.
     */
    public SnakeGame(GameLevel level) {
        this.level = level;

        // Create window and background
        canvas = new CanvasWindow("Snake Xenzia+", WIDTH, HEIGHT);
        canvas.setBackground(new Color(25, 25, 25));

        // Create snake and food
        snake = new Snake(100, 100, CELL);
        food = new Food(CELL);
        food.relocate(WIDTH, HEIGHT);

        // Initialize score UI
        scoreBoard = new ScoreBoard();
        canvas.add(scoreBoard.getDisplay());

        // Create obstacle list (used only in hard mode)
        obstacles = new ArrayList<>();
        if (level.hasObstacles)
            createObstacles();

        // Set up keyboard input
        setupControls();

        // Start the main game loop
        gameLoop();
    }

    /**
     * Registers keyboard controls for snake movement. Uses event handling (COMP 127 concept).
     */
    private void setupControls() {
        canvas.onKeyDown(event -> {
            switch (event.getKey()) {
                case UP_ARROW -> snake.setDirection(Direction.UP);
                case DOWN_ARROW -> snake.setDirection(Direction.DOWN);
                case LEFT_ARROW -> snake.setDirection(Direction.LEFT);
                case RIGHT_ARROW -> snake.setDirection(Direction.RIGHT);
                default -> {
                }
            }
        });
    }

    /**
     * Creates obstacles for HARD difficulty. These act as additional collision hazards.
     */
    private void createObstacles() {
        for (int i = 0; i < 10; i++) {
            Obstacle o = new Obstacle(i * CELL * 2, HEIGHT / 2, CELL);
            obstacles.add(o);
        }
    }

    /**
     * Main game loop: - Updates game state - Checks collisions - Redraws screen - Controls game speed
     */
    private void gameLoop() {
        while (true) {

            // Only update gameplay if still running
            if (state == GameState.RUNNING) {

                // Move snake forward one step
                snake.move();

                // Check for losing conditions
                if (snake.checkSelfCollision() || hitObstacle() || hitWall()) {
                    state = GameState.GAME_OVER;
                }

                // Check if snake eats food
                if (eatFood()) {
                    snake.grow();              // increase snake size
                    scoreBoard.increaseScore(); // update score
                    food.relocate(WIDTH, HEIGHT); // move food
                }

                // Redraw everything
                drawAll();

            } else {
                // Game over state: show final screen
                showGameOver();
            }

            // Control game speed (lower = faster)
            canvas.pause(level.speed);
        }
    }

    /**
     * Checks if snake head intersects with food.
     */
    private boolean eatFood() {
        return snake.getHead().getBounds()
            .intersects(food.getShape().getBounds());
    }

    /**
     * Checks if snake hits screen boundaries.
     */
    private boolean hitWall() {
        var head = snake.getHead();
        return head.getX() < 0 || head.getY() < 0 ||
            head.getX() > WIDTH || head.getY() > HEIGHT;
    }

    /**
     * Checks collision with any obstacle (only relevant in HARD mode).
     */
    private boolean hitObstacle() {
        for (var o : obstacles) {
            if (snake.getHead().getBounds()
                .intersects(o.getShape().getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Draws a grid background for visual clarity. Helps player see movement alignment.
     */
    private void drawGrid() {
        for (int x = 0; x < WIDTH; x += CELL) {
            for (int y = 0; y < HEIGHT; y += CELL) {
                Rectangle r = new Rectangle(x, y, CELL, CELL);
                r.setStrokeColor(new Color(40, 40, 40));
                r.setStroked(true);
                r.setFilled(false);
                canvas.add(r);
            }
        }
    }

    /**
     * Clears screen and redraws all game elements. This ensures smooth animation frame-by-frame.
     */
    private void drawAll() {
        canvas.removeAll();

        drawGrid(); // background grid
        canvas.add(scoreBoard.getDisplay()); // UI
        canvas.add(food.getShape()); // food

        // Draw snake segments
        for (var seg : snake.getBody()) {
            canvas.add(seg);
        }

        // Draw obstacles
        for (var o : obstacles) {
            canvas.add(o.getShape());
        }
    }

    /**
     * Displays game over screen with final score.
     */
    private void showGameOver() {
        canvas.removeAll();

        GraphicsText text = new GraphicsText(
            "GAME OVER\nScore: " + scoreBoard.getScore(),
            WIDTH / 3.0,
            HEIGHT / 2.0);

        text.setFontSize(36);
        text.setFillColor(java.awt.Color.RED);
        text.setFontStyle(edu.macalester.graphics.FontStyle.BOLD);

        canvas.add(text);
    }

    /**
     * Entry point: starts game in HARD mode.
     */
    public static void main(String[] args) {
        new SnakeGame(GameLevel.HARD);
    }
}