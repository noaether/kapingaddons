package ca.noae.kapingaddons.Handlers;

import ca.noae.kapingaddons.PluginMain;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    private static PluginMain plugin;

    public ConfigHandler(PluginMain plugin) {
        ConfigHandler.plugin = plugin;
    }

    public static class messageConfig {
        // Cannot be modified
        public static FileConfiguration config = plugin.getConfig();
        public static int getInt(String path) {
            return config.getInt(path);
        }
        public static String getString(String path) {
            String response = config.getString(path);
            if(response == null) {
                return path;
            } else {
                return response;
            }
        }
        public static boolean getBoolean(String path) {
            return config.getBoolean(path);
        }
    }

    public static class databaseConfig {
        // Can be modified and saved
        public static FileConfiguration config = plugin.getConfig();
        public static int getInt(String path) {
            return config.getInt(path);
        }
        public static String getString(String path) {
            String response = config.getString(path);
            if(response == null) {
                return path;
            } else {
                return response;
            }
        }
        public static boolean getBoolean(String path) {
            return config.getBoolean(path);
        }

        public static void set(String path, Object value) {
            config.set(path, value);
            plugin.saveConfig();
        }
        public static void save() {
            plugin.saveConfig();
        }
    }
}