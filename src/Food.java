import edu.macalester.graphics.Ellipse;
import java.awt.Color;
import java.util.Random;

public class Food {
    private Ellipse shape;
    private double size;
    private Random rand = new Random();

    public Food(double size) {
        this.size = size;

        shape = new Ellipse(0, 0, size, size);
        shape.setFillColor(new Color(255, 80, 80));
        shape.setFilled(true);
    }

    public void relocate(double maxWidth, double maxHeight) {
        double x = rand.nextInt((int)(maxWidth / size)) * size;
        double y = rand.nextInt((int)(maxHeight / size)) * size;
        shape.setPosition(x, y);
    }

    public Ellipse getShape() {
        return shape;
    }
}