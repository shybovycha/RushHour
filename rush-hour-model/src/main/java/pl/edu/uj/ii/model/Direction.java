package pl.edu.uj.ii.model;

/**
 * Created by gauee on 4/7/16.
 */
public enum Direction {
    Up("U"),
    Down("D"),
    Left("L"),
    Right("R");

    private final String action;

    Direction(String action) {
        this.action = action;
    }

    public static Direction convert(String shotcut) {
        for (Direction direction : values()) {
            if (direction.getAction().equals(shotcut)) {
                return direction;
            }
        }
        return Direction.valueOf(shotcut);
    }

    public String getAction() {
        return action;
    }
}
