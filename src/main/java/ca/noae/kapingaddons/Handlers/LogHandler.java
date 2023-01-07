package ca.noae.kapingaddons.Handlers;

import ca.noae.kapingaddons.PluginMain;

public class LogHandler {
    private static PluginMain plugin;

    public LogHandler(PluginMain plugin) {
        LogHandler.plugin = plugin;
    }

    public static class Console {
        public static void info(String message) {
            plugin.getLogger().info(message);
        }
        public static void warning(String message) {
            plugin.getLogger().warning(message);
        }
        public static void error(String message) {
            plugin.getLogger().severe(message);
        }
    }

    public static class LogToFile {
        // null
    }
}