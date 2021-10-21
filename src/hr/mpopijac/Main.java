package hr.mpopijac;

import hr.mpopijac.data.ResultData;
import hr.mpopijac.services.PathFollowingAlgorithmService;
import hr.mpopijac.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Application started with: {} arguments.", args.length);
        if (args.length == 1) {
            LOG.info("Input file path: {}", args[0]);
            try {
                List<String> fileLines = FileUtil.readFromFile(args[0]);
                printContentOfDocument(fileLines);
                PathFollowingAlgorithmService service = new PathFollowingAlgorithmService();
                ResultData result = service.findPath(fileLines);
                printSolution(result);
            } catch (Exception e) {
                LOG.error("ERROR Message: " + e.getMessage(), e);
            }
        } else {
            LOG.info("Please provide only one path to the text document as an argument.");
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
