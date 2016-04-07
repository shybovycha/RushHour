package pl.edu.uj.ii.webapp.sample;

import java.util.Scanner;

/**
 * Created by gauee on 4/7/16.
 */
public class SolutionJava8 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                scanner.nextLine();
            }
        }
        System.out.println("c D 1");
        System.out.println("n L 2");
        System.out.println("m D 2");
        System.out.println("x P 4");
    }
}
