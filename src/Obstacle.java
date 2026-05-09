import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// Obstacle class:
// creates wall blocks
// appears after certain score
// increases difficulty

public class Obstacle {

    private List<Rectangle> obstacles;

    private GraphicsGroup layer;
    private Random random;

    private static final int SIZE = 20;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    public Obstacle(GraphicsGroup layer) {

        this.layer = layer;

        obstacles = new ArrayList<>();
        random = new Random();
    }


    // Create obstacles

    public void generateObstacles(int amount) {

        clear();

        for (int i = 0; i < amount; i++) {

            double x = random.nextInt(SCREEN_WIDTH / SIZE) * SIZE;
            double y = random.nextInt(SCREEN_HEIGHT / SIZE) * SIZE;

            Rectangle block = new Rectangle(x, y, SIZE, SIZE);

            block.setFillColor(Color.WHITE);
            //block.setStrokeColor(Color.WHITE);

            obstacles.add(block);
            layer.add(block);
        }
    }


    // Remove old obstacles

    public void clear() {

        for (Rectangle block : obstacles) {
            layer.remove(block);
        }

        obstacles.clear();
    }


    // Collision check with snake head

    public boolean checkCollision(double x, double y) {

        for (Rectangle block : obstacles) {

            if (block.getX() == x &&
                block.getY() == y) {

                return true;
            }
        }

        return false;
    }
}