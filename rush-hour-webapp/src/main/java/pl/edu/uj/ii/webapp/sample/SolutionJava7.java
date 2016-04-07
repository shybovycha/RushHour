package pl.edu.uj.ii.webapp.sample;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gauee on 4/7/16.
 */
public class SolutionJava7 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                scanner.nextLine();
            }
        }
        for (String step : Arrays.asList("c D 1", "n L 2", "m D 2", "x P 4")) {
            System.out.println(step);
        }
    }
}
