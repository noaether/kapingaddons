package ca.noae.kapingaddons.Handlers;

import ca.noae.kapingaddons.PluginMain;
import org.bukkit.entity.Player;

public class PermissionHandler {
    private static PluginMain plugin;

    public PermissionHandler(PluginMain plugin) {
        PermissionHandler.plugin = plugin;
    }

    public static class Read {
        public static boolean hasPerm(Player player, String permission) {
            return player.hasPermission(permission);
        }
    }

    public static class Write {

    };
}