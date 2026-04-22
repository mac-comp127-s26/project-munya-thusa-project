public class GameLevel {
    public final int speed;
    public final boolean hasObstacles;

    public GameLevel(int speed, boolean hasObstacles) {
        this.speed = speed;
        this.hasObstacles = hasObstacles;
    }

    public static GameLevel EASY = new GameLevel(120, false);
    public static GameLevel MEDIUM = new GameLevel(80, false);
    public static GameLevel HARD = new GameLevel(50, true);
}