package pl.edu.uj.ii.model;

/**
 * Created by gauee on 4/7/16.
 */
public class CarMove {
    private final CarId carId;
    private final Direction direction;
    private final byte steps;

    public CarMove(CarId carId, Direction direction, byte steps) {
        this.carId = carId;
        this.direction = direction;
        this.steps = steps;
    }

    public CarId getCarId() {
        return carId;
    }

    public Direction getDirection() {
        return direction;
    }

    public byte getSteps() {
        return steps;
    }
}
