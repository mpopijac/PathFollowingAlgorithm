package hr.mpopijac.util;

import hr.mpopijac.exceptions.ReadFromFileException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileUtilTest {

    @Test
    public void readFromFile() throws ReadFromFileException {
        final String testFilePath = "./././././examples/Example1.txt";
        List<String> lines = FileUtil.readFromFile(testFilePath);
        assertEquals("  @---A---+",lines.get(0));
        assertEquals("          |",lines.get(1));
        assertEquals("  x-B-+   C",lines.get(2));
        assertEquals("      |   |",lines.get(3));
        assertEquals("      +---+",lines.get(4));
    }

    @Test(expected = ReadFromFileException.class)
    public void readFromFile_invalidPath() throws ReadFromFileException {
        final String testFilePath = "./././././examples/NotExistingDocument.txt";
        FileUtil.readFromFile(testFilePath);
    }
}
