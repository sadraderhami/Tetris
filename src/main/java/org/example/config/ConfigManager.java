//package org.example.config;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import java.awt.Color;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class ConfigManager {
//    private JsonObject rootObject;
//
//    public ConfigManager(String filePath) throws IOException {
//        try (FileReader reader = new FileReader(filePath)) {
//            rootObject = JsonParser.parseReader(reader).getAsJsonObject();
//        }
//    }
//
//    public int getInt(String key, int defaultValue) {
//        JsonElement element = rootObject.get(key);
//        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
//            return element.getAsInt();
//        }
//        return defaultValue;
//    }
//
//    public String getString(String key, String defaultValue) {
//        JsonElement element = rootObject.get(key);
//        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
//            return element.getAsString();
//        }
//        return defaultValue;
//    }
//
//    public Color getColor(String key, Color defaultColor) {
//        String hex = getString(key, null);
//        if (hex != null) {
//            try {
//                if (hex.startsWith("#")) hex = hex.substring(1);
//                if (hex.length() == 6) {
//                    int rgb = Integer.parseInt(hex, 16);
//                    return new Color(rgb);
//                }
//            } catch (NumberFormatException ignored) {}
//        }
//        return defaultColor;
//    }
//
//    public JsonObject getJsonObject(String key) {
//        JsonElement element = rootObject.get(key);
//        if (element != null && element.isJsonObject()) {
//            return element.getAsJsonObject();
//        }
//        return null;
//    }
//}
//
package org.example.config;

import com.google.gson.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private JsonObject rootObject;

    // Load from a file path (e.g., when running from IDE or packaged jar with external config)
    public ConfigManager(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            rootObject = JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    // Alternate: Load from resources folder (e.g., src/main/resources/config/config.json)
    public ConfigManager(InputStream resourceStream) throws IOException {
        try (Reader reader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)) {
            rootObject = JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    private JsonElement getElementByPath(String path) {
        String[] parts = path.split("\\.");
        JsonElement element = rootObject;
        for (String part : parts) {
            if (element == null || !element.isJsonObject()) return null;
            element = element.getAsJsonObject().get(part);
        }
        return element;
    }

    public int getInt(String key, int defaultValue) {
        JsonElement element = getElementByPath(key);
        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
            return element.getAsInt();
        }
        return defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        JsonElement element = getElementByPath(key);
        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
            return element.getAsDouble();
        }
        return defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        JsonElement element = getElementByPath(key);
        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isBoolean()) {
            return element.getAsBoolean();
        }
        return defaultValue;
    }

    public String getString(String key, String defaultValue) {
        JsonElement element = getElementByPath(key);
        if (element != null && element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
            return element.getAsString();
        }
        return defaultValue;
    }

    public Color getColor(String key, Color defaultColor) {
        String hex = getString(key, null);
        if (hex != null) {
            try {
                if (hex.startsWith("#")) hex = hex.substring(1);
                if (hex.length() == 6 || hex.length() == 8) {
                    long rgba = Long.parseLong(hex, 16);
                    if (hex.length() == 6) {
                        return new Color((int) rgba);
                    } else {
                        return new Color((int)(rgba >> 8), true); // assumes ARGB
                    }
                }
            } catch (NumberFormatException ignored) {}
        }
        return defaultColor;
    }

    public JsonObject getJsonObject(String key) {
        JsonElement element = getElementByPath(key);
        if (element != null && element.isJsonObject()) {
            return element.getAsJsonObject();
        }
        return null;
    }

    public <T> T getAs(String key, Class<T> clazz) {
        JsonObject obj = getJsonObject(key);
        if (obj != null) {
            return new Gson().fromJson(obj, clazz);
        }
        return null;
    }
}
