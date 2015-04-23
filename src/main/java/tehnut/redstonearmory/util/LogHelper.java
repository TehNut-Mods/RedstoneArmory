package tehnut.redstonearmory.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.ModInformation;

public class LogHelper {

    private static Logger logger = LogManager.getLogger(ModInformation.NAME);

    public static void info(Object info) {
        if (ConfigHandler.enableConsoleLogging)
            logger.info(info);
    }

    public static void error(Object error) {
        if (ConfigHandler.enableConsoleLogging)
            logger.error(error);
    }

    public static void warn(Object warn) {
        if (ConfigHandler.enableConsoleLogging)
            logger.debug(warn);
    }

    public static void debug(Object debug) {
        if (ConfigHandler.enableConsoleLogging)
            logger.debug(debug);
    }
}
