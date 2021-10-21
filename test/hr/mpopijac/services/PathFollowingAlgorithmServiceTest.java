package hr.mpopijac.services;

import hr.mpopijac.data.ResultData;
import hr.mpopijac.exceptions.*;
import hr.mpopijac.util.FileUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PathFollowingAlgorithmServiceTest {

    private PathFollowingAlgorithmService algorithmService = new PathFollowingAlgorithmService();

    @Test
    public void findPath_Example1() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example1.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        final ResultData resultData = algorithmService.findPath(lines);
        assertEquals("@---A---+|C|+---+|+-B-x", resultData.getPath());
        assertEquals("ACB", resultData.getLetters());
    }

    @Test
    public void findPath_Example2() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example2.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        final ResultData resultData = algorithmService.findPath(lines);
        assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", resultData.getPath());
        assertEquals("ABCD", resultData.getLetters());
    }

    @Test
    public void findPath_Example3() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example3.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        final ResultData resultData = algorithmService.findPath(lines);
        assertEquals("@---A---+|||C---+|+-B-x", resultData.getPath());
        assertEquals("ACB", resultData.getLetters());
    }

    @Test
    public void findPath_Example4() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example4.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        final ResultData resultData = algorithmService.findPath(lines);
        assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", resultData.getPath());
        assertEquals("GOONIES", resultData.getLetters());
    }

    @Test
    public void findPath_Example5() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example5.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        final ResultData resultData = algorithmService.findPath(lines);
        assertEquals("@B+++B|+-L-+A+++A-+Hx", resultData.getPath());
        assertEquals("BLAH", resultData.getLetters());
    }

    @Test(expected = NoStartingCharacterException.class)
    public void findPath_Example6_noStart() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example6.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = NoEndingCharacterException.class)
    public void findPath_Example7_noEnd() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example7.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = MultipleStartingCharacterException.class)
    public void findPath_Example8_multipleStarts() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example8.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = MultipleEndingCharacterException.class)
    public void findPath_Example9_multipleEnds() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example9.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = MultipleEndingCharacterException.class)
    public void findPath_Example10_multipleEndsMultiplePaths() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example10.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = NoEndingCharacterException.class)
    public void findPath_Example11_brokenPath() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example11.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = MultiplePossiblePathsException.class)
    public void findPath_Example12_multiplePossiblePaths() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example12.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = NoEndingCharacterException.class)
    public void findPath_Example13_fakeTurn() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example13.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }

    @Test(expected = NotAllowedCharacterException.class)
    public void findPath_Example14_notAllowedCharacter() throws ReadFromFileException, MultipleEndingCharacterException, MultipleStartingCharacterException, NoEndingCharacterException, MultiplePossiblePathsException, NotAllowedCharacterException, NoStartingCharacterException {
        final String testFilePath = "./././././examples/Example14.txt";
        final List<String> lines = FileUtil.readFromFile(testFilePath);
        algorithmService.findPath(lines);
    }
}
