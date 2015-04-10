package tehnut.redstonearmory.util;

import tehnut.redstonearmory.ConfigHandler;
import tehnut.redstonearmory.ModInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static void debug(Object debug) {
        if (ConfigHandler.enableConsoleLogging)
            logger.debug(debug);
    }

}
