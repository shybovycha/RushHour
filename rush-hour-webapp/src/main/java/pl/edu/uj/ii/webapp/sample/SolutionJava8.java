package pl.edu.uj.ii.webapp.sample;

import java.util.Scanner;

/**
 * Created by gauee on 4/7/16.
 */
public class SolutionJava8 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                System.out.println(SolutionJava8.class.getName() + " processed " + line);
            }
        }
    }
}
