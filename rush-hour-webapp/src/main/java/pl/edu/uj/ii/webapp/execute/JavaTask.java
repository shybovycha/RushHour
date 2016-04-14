package pl.edu.uj.ii.webapp.execute;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import pl.edu.uj.ii.DataConverter;
import pl.edu.uj.ii.model.CarMove;
import pl.edu.uj.ii.webapp.execute.test.TestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static pl.edu.uj.ii.webapp.AppConfig.CONFIG;

/**
 * Created by gauee on 4/7/16.
 */
public class JavaTask implements Task, Compilable {
    private static final Logger LOGGER = Logger.getLogger(JavaTask.class);
    private final String compiledFileDir;
    private final String jdkDir;
    private String compiledFilePath;

    public JavaTask(String compiledFileDir, String jdkDir) {
        this.compiledFileDir = compiledFileDir;
        this.jdkDir = jdkDir;
    }

    @Override
    public Task compile(UploadFile uploadFile) throws IOException, ClassNotFoundException {
        String className = uploadFile.getName().split("\\.")[0];
        String packageDir = generateNewPackage();
        Path root = Paths.get(CONFIG.getUploadedFileDir());
        Path file = Paths.get(root.toString(), packageDir, className + ".java");
        Files.createDirectories(file.getParent());
        String filePackage = packageDir.replaceAll("\\" + File.separator, ".");
        String sourceCode = uploadFile.getData().replaceFirst("package .+?;", "package " + filePackage + ";");
        LOGGER.debug("Source code to compile:\n" + StringUtils.replaceChars(sourceCode, '\n', ' '));
        Files.write(file, sourceCode.getBytes(StandardCharsets.UTF_8));

//        ProcessBuilder processBuilder = new ProcessBuilder(getCompileCommand(), packageDir + File.separator + className + ".java");
        ProcessBuilder processBuilder = new ProcessBuilder(getCompileCommand(), file.toFile().getAbsolutePath());
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(file.getParent().getParent().getParent().toFile());
        StringBuilder compilerOut = new StringBuilder();
        try {
            Process start = processBuilder.start();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    compilerOut.append(line);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Cannot execute process.", e);
        }
        LOGGER.info(compilerOut.toString());
        compiledFilePath = filePackage + "." + className;
        return this;
    }

    @Override
    public List<List<CarMove>> resolveTestCases(TestCase testCase) {
        List<String> lines = resolveTestCases(testCase.getFile());
        try {
            return DataConverter.parseOutputLines(lines);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cannot parse output " + lines, e);
        }
    }

    public List<String> resolveTestCases(File inputFile) {
        ProcessBuilder processBuilder = new ProcessBuilder(getExecuteCommand(), compiledFilePath);
        processBuilder.redirectInput(inputFile);
        processBuilder.redirectErrorStream(true);
        processBuilder.directory(inputFile.getParentFile().getParentFile());
        List<String> lines = Lists.newLinkedList();
        try {
            Process start = processBuilder.start();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Cannot execute process.", e);
        }
        return lines;
    }

    private String getExecuteCommand() {
        return jdkDir + File.separator + "bin" + File.separator + "java";
    }

    private String getCompileCommand() {
        return jdkDir + File.separator + "bin" + File.separator + "javac";
    }

    private String generateNewPackage() {
        return "runtimeCompiled" + File.separator + compiledFileDir + File.separator + "_" + System.currentTimeMillis();
    }

}
