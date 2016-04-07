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

    public String getAction() {
        return action;
    }
}
