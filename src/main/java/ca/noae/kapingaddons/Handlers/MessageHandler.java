package ca.noae.kapingaddons.Handlers;

import ca.noae.kapingaddons.PluginMain;
import org.bukkit.entity.Player;

public class MessageHandler {
    private static PluginMain plugin;

    public MessageHandler(PluginMain plugin) {
        MessageHandler.plugin = plugin;
    }

    public static String prefix = "§8[§6Kaping-Addons§8]§r ";
    public static String noPermission = prefix + ConfigHandler.messageConfig.getString("messages.error.noperm");

    public static class Error {
        public static void sendNoPermission(Player player) {
            player.sendMessage(prefix + noPermission);
        }
        // Send no console
        public static void sendNoConsole() {
            plugin.getServer().getConsoleSender().sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.noconsole"));
        }
        public static void sendArgs(Player player, String usage) { // If player
            player.sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.args").replace("%usage%", usage));
        }
        public static void sendArgs(String usage) { // If console
            plugin.getServer().getConsoleSender().sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.args").replace("%usage%", usage));
        }
        public static void sendDoesntExist(Player player, String error) { // If player
            player.sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.doesntexist").replace("%error%", error));
        }
        public static void sendDoesntExist(String error) { // If console
            plugin.getServer().getConsoleSender().sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.doesntexist").replace("%error%", error));
        }
        public static void sendMax(Player player, String error) { // If player
            player.sendMessage(prefix + ConfigHandler.messageConfig.getString("messages.error.max").replace("%error%", error));
        }

    }
    public static class InGame {
        public static void sendMessage(Player player, String message) {
            player.sendMessage(prefix + message);
        }
    }
    public static class Console {
        public static void sendMessage(String message) {
            plugin.getServer().getConsoleSender().sendMessage(prefix + message);
        }
    }
}