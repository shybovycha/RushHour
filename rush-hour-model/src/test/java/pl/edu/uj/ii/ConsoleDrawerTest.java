package pl.edu.uj.ii;

import org.junit.Test;
import pl.edu.uj.ii.model.Board;
import pl.edu.uj.ii.model.Car;
import pl.edu.uj.ii.model.CarId;

import java.awt.*;

/**
 * Created by gauee on 3/31/16.
 */
public class ConsoleDrawerTest {

    @Test
    public void printEmptyBoard() {
        new ConsoleDrawer().print(new Board(5, 5));
    }

    @Test
    public void printEmptyBoardWithDifferentSideLength() {
        new ConsoleDrawer().print(new Board(10, 5));
    }

    @Test
    public void printBoardWithOneCar() {
        Board board = new Board(6, 6);
        board.add(new Car(CarId.A, new Point(0, 0), new Point(0, 2)));

        new ConsoleDrawer().print(board);
    }

    @Test
    public void printBoardWithFourCarsInEdges() {
        Board board = new Board(6, 6);
        board.add(new Car(CarId.A, new Point(0, 0), new Point(0, 2)));
        board.add(new Car(CarId.C, new Point(0, 5), new Point(1, 5)));
        board.add(new Car(CarId.E, new Point(5, 0), new Point(4, 0)));
        board.add(new Car(CarId.I, new Point(5, 5), new Point(5, 3)));

        new ConsoleDrawer().print(board);
    }

    @Test
    public void throwsExceptionOnInvalidCarMove() {
        Board board = new Board(6, 6);
        board.add(new Car(CarId.A, new Point(0, 0), new Point(0, 2)));

        board.move();
    }

}