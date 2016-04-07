package pl.edu.uj.ii.webapp.execute;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.model.CarId;
import pl.edu.uj.ii.model.CarMove;
import pl.edu.uj.ii.model.Direction;
import pl.edu.uj.ii.webapp.AppConfig;
import pl.edu.uj.ii.webapp.execute.test.TestCase;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static pl.edu.uj.ii.webapp.AppConfig.CONFIG;

/**
 * Created by gauee on 4/7/16.
 */
public class JavaTask implements Task, Compilable {
    private static final Logger LOGGER = Logger.getLogger(JavaTask.class);
    private final String compiledFileDir;
    private String compiledFilePath;

    public JavaTask(String compiledFileDir) {
        this.compiledFileDir = compiledFileDir;
    }

    @Override
    public Task compile(UploadFile uploadFile) throws IOException, ClassNotFoundException {
        String className = uploadFile.getName().split("\\.")[0];
        String packageDir = generateNewPackage();
        Path root = Paths.get(CONFIG.getUploadedFileDir());
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
        compiledFilePath = filePackage + "." + className;
        return this;
    }

    @Override
    public List<CarMove> resolveTestCases(TestCase testCase) {
        return parseOutput(resolveTestCases(testCase.getFile()));
    }

    public String resolveTestCases(File inputFile) {
        ProcessBuilder processBuilder = new ProcessBuilder(AppConfig.CONFIG.getJava8Home() + "/bin/java", compiledFilePath);
        processBuilder.redirectInput(inputFile);
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(inputFile.getParentFile());
        StringBuilder outputBuilder = new StringBuilder();
        try {
            Process start = processBuilder.start();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    outputBuilder.append(line).append('\n');
                }
            }
        } catch (IOException e) {
            LOGGER.error("Cannot execute process.", e);
        }
        return outputBuilder.toString();
    }

    public List<CarMove> parseOutput(String output) {
        return Arrays.asList(output.split("\\n"))
                .stream()
                .map(step -> parseStep(step))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CarMove parseStep(String step) {
        String[] args = step.split(" ");
        if (args.length != 3) {
            return null;
        }
        return new CarMove(
                CarId.valueOf(args[0]),
                Direction.valueOf(args[1]),
                Byte.valueOf(args[2])
        );
    }

    private String generateNewPackage() {
        return "runtimeCompiled" + File.separator + compiledFileDir + File.separator + "_" + System.currentTimeMillis();
    }

}
