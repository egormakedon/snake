package by.makedon.snake.util;

import java.util.Properties;

/**
 * @author Yahor Makedon
 */
public final class ResourceUtil {
    private static final Properties properties;

    static {
        properties = loadApplicationProperties();
    }

    private ResourceUtil() {
    }

    public static String getPropertyValue(String key) {
        String propertyValue = properties.getProperty(key);

        if (propertyValue == null) {
            throw new IllegalArgumentException(String.format("%s property not exists", key));
        } else {
            return propertyValue;
        }
    }

    private static Properties loadApplicationProperties() {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(Constants.APPLICATION_PROPERTIES_FILENAME));
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

        return properties;
    }
}
