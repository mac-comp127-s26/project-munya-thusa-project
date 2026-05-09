
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

import java.awt.Color;
import java.util.Random;

// Apple class: shows apple on screen and moves to random position when eaten

public class Apple {

    private Ellipse shape;
    private Random random;

    private static final int SIZE = 20;

    public Apple(GraphicsGroup layer) {

        random = new Random();

        shape = new Ellipse(0, 0, SIZE, SIZE);
        shape.setFillColor(Color.RED);

        layer.add(shape);

        relocate();
    }

    // Move apple to random position

    public void relocate() {

        double x = random.nextInt(40) * SIZE;
        double y = random.nextInt(30) * SIZE;

        shape.setPosition(x, y);
    }


    public double getX() {
        return shape.getX();
    }

    public double getY() {
        return shape.getY();
    }


    public void draw() {

    }
}