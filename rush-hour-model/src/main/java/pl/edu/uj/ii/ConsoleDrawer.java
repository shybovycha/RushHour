package pl.edu.uj.ii;

import pl.edu.uj.ii.model.Board;

import java.awt.*;

/**
 * Created by gauee on 3/31/16.
 */
public class ConsoleDrawer {

    public static final Integer ZERO = Integer.valueOf(0);

    public void print(Board board) {
        System.out.println();
        char[][] positionOfCars = generatePositionOfCars(board);
        printNewLine(board);
        for (int i = 0; i < board.getHeight(); i++) {
            System.out.print("|");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(positionOfCars[j][i] + "|");
            }
            System.out.println();
            printNewLine(board);
        }
    }

    private char[][] generatePositionOfCars(Board board) {
        char[][] positionOfCars = new char[board.getWidth()][board.getHeight()];
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                positionOfCars[j][i] = ' ';
            }
        }

        board.getCars()
                .stream()
                .forEach(c -> {
                    Point startPoint = c.getStartPoint();
                    Point direction = c.getDirection();
                    for (int i = 0; i <= Math.abs(direction.y); i++) {
                        for (int j = 0; j <= Math.abs(direction.x); j++) {
                            positionOfCars[startPoint.x + j * sign(direction.x)][startPoint.y + i * sign(direction.y)] = c.getId().getId();
                        }
                    }
                });

        return positionOfCars;
    }

    private int sign(int x) {
        return -ZERO.compareTo(x);
    }

    private void printNewLine(Board board) {
        System.out.print('+');
        for (int j = 0; j < board.getWidth(); j++) {
            System.out.print("-+");
        }
        System.out.println();
    }

}
