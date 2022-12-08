package interview.exercise.replacement;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Report {

    private  static final Logger logger = Logger.getLogger(Report.class.getName());
    public static Map<String, Integer> textReplacementReport = new HashMap<>();
    public static Integer processedFiles = 0;
    public static String replaced;

    public static String logReport() {
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        logger.addHandler(handlerObj);
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        String str = "";
        str += "Processed " + processedFiles + " files" + "\n";
        str += "\n";
        str += "Replaced to '" + replaced + "':" + "\n";
        for (Map.Entry<String,Integer> entry : textReplacementReport.entrySet()) {
            str += " * " + entry.getKey() + " : " + entry.getValue() + " occurence" + "\n";
        }
        logger.log(Level.INFO, str);
        return str;
    }

    public static void resetReport() {
        textReplacementReport = new HashMap<>();
        processedFiles = 0;
    }
}
