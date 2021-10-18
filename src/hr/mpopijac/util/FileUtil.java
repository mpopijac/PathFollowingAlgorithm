package hr.mpopijac.util;

import hr.mpopijac.exceptions.ReadFromFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    private FileUtil() {
    }

    public static List<String> readFromFile(final String filePath) throws ReadFromFileException {
        final Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new ReadFromFileException("Unable to read from file path:" + filePath, e);
        }
    }
}
