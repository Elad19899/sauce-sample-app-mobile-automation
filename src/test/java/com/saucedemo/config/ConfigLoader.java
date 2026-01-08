package com.saucedemo.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = new Properties();
        try (InputStream inp = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inp != null) {
                properties.load(inp);
            } else {
                throw new RuntimeException("config.properties not found in classpath");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getProperty(String key) {
        String prop = properties.getProperty(key);
        if (prop != null) {
            return prop;
        }
        throw new RuntimeException("Property " + key + " is not specified in config.properties");
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
