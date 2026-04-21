import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Obstacle {
    private Rectangle block;

    public Obstacle(double x, double y, double size) {
        block = new Rectangle(x, y, size, size);
        block.setFillColor(Color.DARK_GRAY);
        block.setFilled(true);
    }

    public Rectangle getShape() {
        return block;
    }
}