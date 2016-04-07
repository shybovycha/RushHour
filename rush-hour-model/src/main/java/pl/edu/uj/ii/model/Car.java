package pl.edu.uj.ii.model;

import java.awt.*;

/**
 * Created by gauee on 3/31/16.
 */
public class Car {
    private final CarId id;
    private final Point startPoint;
    private final Point direction;

    public Car(CarId id, Point startPoint, Point endPoint) {
        this.id = id;
        this.startPoint = startPoint;
        this.direction = new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
    }

    public CarId getId() {
        return id;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getDirection() {
        return direction;
    }
}
