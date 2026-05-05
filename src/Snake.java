import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


// Snake class:
// stores body parts
// moves snake
// grows when eating apple

public class Snake {

    private List<Rectangle> body;
    private char direction = 'R';

    private GraphicsGroup layer;

    private static final int SIZE = 20;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    public Snake(GraphicsGroup layer) {

        this.layer = layer;
        body = new ArrayList<>();

        // Create initial snake size 5
        for (int i = 0; i < 5; i++) {
            Rectangle part = new Rectangle(100 - i * SIZE, 100, SIZE, SIZE);
            part.setFillColor(Color.GREEN);

            body.add(part);
            layer.add(part);
        }
    }


    // Change snake direction

    public void setDirection(char newDirection) {
        // prevent reversing direction
        if (direction == 'U' && newDirection == 'D') {
            return;
        }
        if (direction == 'D' && newDirection == 'U') {
            return;
        }
        if (direction == 'L' && newDirection == 'R') {
            return;
        }
        if (direction == 'R' && newDirection == 'L') {
            return;
        }

        direction = newDirection;
    }


    public boolean move() {

        Rectangle head = body.get(0);

        double newX = head.getX();
        double newY = head.getY();

        // calculate next position
        if (direction == 'U') {
            newY -= SIZE;
        } else if (direction == 'D') {
            newY += SIZE;
        } else if (direction == 'L') {
            newX -= SIZE;
        } else if (direction == 'R') {
            newX += SIZE;
        }

        // boundary check
        if (newX < 0 || newX >= SCREEN_WIDTH ||
            newY < 0 || newY >= SCREEN_HEIGHT) {
            return false;
        }

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).setPosition(
                body.get(i - 1).getX(),
                body.get(i - 1).getY());
        }

        head.setPosition(newX, newY);

        // check collision with body (skip head at index 0)
        for (int i = 1; i < body.size(); i++) {
            Rectangle part = body.get(i);

            if (head.getX() == part.getX() &&
                head.getY() == part.getY()) {
                return false; // hit itself → game over
            }
        }
        return true;
    }


    public boolean checkCollisionWithApple(Apple apple) {

        Rectangle head = body.get(0);

        if (head.getX() == apple.getX() &&
            head.getY() == apple.getY()) {

            grow();
            apple.relocate();
            return true;
        }

        return false;
    }


    private void grow() {

        Rectangle tail = body.get(body.size() - 1);

        Rectangle newPart = new Rectangle(
            tail.getX(),
            tail.getY(),
            SIZE,
            SIZE);

        newPart.setFillColor(Color.GREEN);

        body.add(newPart);
        layer.add(newPart);
    }
}