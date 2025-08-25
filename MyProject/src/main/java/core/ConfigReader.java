package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigReader() {
        // запрет инстанцирования
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key, "false"));
    }

    public static List<String> getList(String key) {
        String value = props.getProperty(key, "").trim();
        if (value.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public static List<String> getBrowsers() {
        return getList("browsers");
    }
}