package pl.edu.uj.ii.webapp.execute;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by gauee on 4/7/16.
 */
public class JavaTaskTest {

    private final String javaClassSourceCode = "package test; import java.util.Scanner;import java.util.concurrent.TimeUnit; public class Reader {public static void main(String[] args) throws InterruptedException {TimeUnit.SECONDS.sleep(5);System.out.println(\"Hello\");Scanner scanner = new Scanner(System.in);while (scanner.hasNext()) {String line = scanner.nextLine();System.out.println(Reader.class.getClass() + \" read line = \" + line);}System.out.println(\"Executed \" + Reader.class.getName());}}";

    @Test
    public void matchesClassName() {
        assertThat(JavaTask.CLASS_NAME_PATTERN.matcher(javaClassSourceCode).matches(), is(true));
    }

    @Test
    public void retrieveClassName() {
        Matcher matcher = JavaTask.CLASS_NAME_PATTERN.matcher(javaClassSourceCode);
        matcher.matches();

        assertThat(matcher.group(1), is(equalTo("Test")));
    }

    @Test
    public void doesNotMatchClassName() {
        assertThat(JavaTask.CLASS_NAME_PATTERN.matcher("public classTest{}").matches(), is(false));
    }

    @Test
    public void compilesProvidedSourceCode() throws IOException, ClassNotFoundException {
        new JavaTask().compile(javaClassSourceCode);
    }


//    static class Reader {
//        public static void main(String[] args) throws InterruptedException {
//            TimeUnit.SECONDS.sleep(5);
//            System.out.println("Hello");
//            Scanner scanner = new Scanner(System.in);
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                System.out.println(Reader.class.getClass() + " read line = " + line);
//            }
//            System.out.println("Executed " + Reader.class.getName());
//        }
//    }

}