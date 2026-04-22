import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Rectangle> body;

    // Current movement direction of the snake
    private Direction direction = Direction.RIGHT;

    // Size of each grid cell 
    private double cellSize;

    public Snake(double startX, double startY, double cellSize) {
        this.cellSize = cellSize;
        body = new LinkedList<>();

        // Initialize snake with a single head segment
        body.add(createSegment(startX, startY, true));
    }

    private Rectangle createSegment(double x, double y, boolean isHead) {
        Rectangle r = new Rectangle(x, y, cellSize, cellSize);

        if (isHead) {
            r.setFillColor(new Color(0, 255, 100));
        } else {
            r.setFillColor(new Color(0, 180, 70));
        }

        r.setFilled(true);
        return r;
    }

    public void move() {
        Rectangle head = body.getFirst();
        double x = head.getX();
        double y = head.getY();

        // mvt/direction 
        switch (direction) {
            case UP -> y -= cellSize;
            case DOWN -> y += cellSize;
            case LEFT -> x -= cellSize;
            case RIGHT -> x += cellSize;
        }

        Rectangle newHead = createSegment(x, y, true);
        body.addFirst(newHead);

        if (body.size() > 1) {
            body.get(1).setFillColor(new Color(0, 180, 70));
        }

        body.removeLast();
    }

    public void grow() {
        Rectangle tail = body.getLast();

        // Adds a new segment at the tail 
        body.addLast(createSegment(tail.getX(), tail.getY(), false));
    }

    public boolean checkSelfCollision() {
        var head = body.getFirst().getBounds();

        // Check if head intersects any other segment
        for (int i = 1; i < body.size(); i++) {
            if (head.intersects(body.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public LinkedList<Rectangle> getBody() {
        return body;
    }

    public Rectangle getHead() {
        return body.getFirst();
    }
}