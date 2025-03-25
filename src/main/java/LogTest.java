import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        try {
            Files.createDirectory(Paths.get("logs"));
        } catch(Exception e) {
            logger.error("не удалось создать папку logs", e);
        }
        logger.debug("Это debug сообщение");
        logger.info("Это info сообщение");
        logger.error("Это error сообщение");
        logger.warn("Это warn сообщение");
    }
}