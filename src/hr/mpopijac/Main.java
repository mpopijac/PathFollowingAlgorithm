package hr.mpopijac;

import hr.mpopijac.data.ResultData;
import hr.mpopijac.exceptions.ReadFromFileException;
import hr.mpopijac.services.PathFollowingAlgorithmService;
import hr.mpopijac.util.FileUtil;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws ReadFromFileException {
        LOG.info(() -> "Application started with: " + args.length + " arguments.");
        if (args.length == 1) {
            LOG.info(() -> "Input file path:" + args[0]);
            try {
                List<String> fileLines = FileUtil.readFromFile(args[0]);
                printContentOfDocument(fileLines);
                PathFollowingAlgorithmService service = new PathFollowingAlgorithmService();
                ResultData result = service.findPath(fileLines);
                printSolution(result);
            } catch (Exception e) {
                LOG.info("ERROR Message: " + e.getMessage());
                LOG.info("ERROR StackTrace: " + Arrays.toString(e.getStackTrace()));
            }
        } else {
            LOG.info("Please provide only one text document as a argument.");
        }
        LOG.info("Application STOP");
    }

    private static void printContentOfDocument(List<String> fileLines) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("Content of document:");
        sb.append(System.lineSeparator());
        for (String s : fileLines) {
            sb.append(s);
            sb.append(System.lineSeparator());
        }
        String content = sb.toString();
        LOG.info(content);
    }

    private static void printSolution(ResultData result) {
        String solutionResult = System.lineSeparator() +
                "Letters: " + result.getLetters() + System.lineSeparator() +
                "Path as characters: " + result.getPath();
        LOG.info(solutionResult);
    }

}
