package pl.edu.uj.ii.webapp.execute;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.model.CarMove;
import pl.edu.uj.ii.webapp.execute.test.TestCase;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by gauee on 4/7/16.
 */
public class JavaTask implements Task, Compilable {
    private static final Logger LOGGER = Logger.getLogger(JavaTask.class);

    @Override
    public Task compile(UploadFile uploadFile) throws IOException, ClassNotFoundException {
        String className = uploadFile.getName().split("\\.")[0];
        String packageDir = generateNewPackage();
        Path root = Paths.get("target/classes/");
        Path file = Paths.get(root.toString(), packageDir, className + ".java");
        Files.createDirectories(file.getParent());
        String filePackage = packageDir.replaceAll(File.separator, ".");
        String sourceCode = uploadFile.getData().replaceFirst("package .+?;", "package " + filePackage + ";");

        LOGGER.debug("Source code to compile:\n" + StringUtils.replaceChars(sourceCode, '\n', ' '));
        Files.write(file, sourceCode.getBytes(StandardCharsets.UTF_8));

        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        systemJavaCompiler.run(null, outputStream, null, file.toFile().getPath());
        LOGGER.info(outputStream.toString());

//        ProcessBuilder processBuilder = new ProcessBuilder("/opt/jdk1.8.0_25/bin/java", filePackage + "." + className);
//        File testCases = new File(getClass().getClassLoader().getResource("testCases.in").getFile());
//        processBuilder.redirectInput(testCases);
//        processBuilder.redirectErrorStream(true);
//        processBuilder.directory(testCases.getParentFile());
//        Process start = processBuilder.start();
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
//        LOGGER.info("<Output>");
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            LOGGER.info(line);
//        }
//        LOGGER.info("</Output>");
//        bufferedReader.close();
        return null;
    }

    private String generateNewPackage() {
        return "runtimeCompiled" + File.separator + getClassDir() + File.separator + generateUniqueDir();
    }

    private String generateUniqueDir() {
        return "_" + System.currentTimeMillis();
    }

    private String getClassDir() {
        return "jdk";
    }

    @Override
    public List<CarMove> resolveTestCases(TestCase testCase) {
        return null;
    }

}
