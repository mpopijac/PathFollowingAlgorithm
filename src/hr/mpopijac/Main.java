package hr.mpopijac;

import hr.mpopijac.exceptions.ReadFromFileException;
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
                logContentOfDocument(fileLines);

            } catch (Exception e) {
                LOG.info("ERROR Message: " + e.getMessage());
                LOG.info("ERROR StackTrace: " + Arrays.toString(e.getStackTrace()));
            }
        } else {
            LOG.info("Please provide only one text document as a argument.");
        }
        LOG.info("Application STOP");
    }

    private static void logContentOfDocument(List<String> fileLines) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("Content of document:");
        sb.append(System.lineSeparator());
        for (String s : fileLines) {
            sb.append(s);
            sb.append(System.lineSeparator());
        }
        LOG.info(sb.toString());
    }

}
