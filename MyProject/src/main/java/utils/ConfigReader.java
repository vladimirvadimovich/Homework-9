package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    public static String get(String key) {
        return props.getProperty(key);
    }

    static {
        try {
            try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (in == null) {
                    throw new RuntimeException("config.properties not found in classpath");
                }

                props.load(in);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
}
