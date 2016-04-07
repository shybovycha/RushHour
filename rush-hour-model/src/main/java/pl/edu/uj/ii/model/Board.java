package pl.edu.uj.ii.model;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by gauee on 3/31/16.
 */
public class Board {

    private final int width;
    private final int height;
    private final List<Car> cars = Lists.newLinkedList();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean add(Car car) {
        cars.add(car);
        return true;
    }

    public List<Car> getCars() {
        return Lists.newLinkedList(cars);
    }

    public void move() {

    }
}
