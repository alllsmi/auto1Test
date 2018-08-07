package infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPS_FILE_NAME = "testing.properties";
    private static ClassLoader classLoader = Config.class.getClassLoader();
    private static Properties props;

    public static boolean isWindows;

    static {
        props = readPropertiesFile(DEFAULT_PROPS_FILE_NAME);
        isWindows = Boolean.parseBoolean(props.getProperty("isWindows"));
    }

    private static Properties readPropertiesFile(String propsFileName) {
        Properties props = new Properties();
        try (InputStream i = classLoader.getResourceAsStream(propsFileName)) {
            props.load(i);
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        }
        return props;
    }
}
