package ca.noae.kapingaddons.Commands.Gamemode;

import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;
import ca.noae.kapingaddons.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Spectator implements CommandExecutor {
    private final PluginMain plugin;

    String gamemodeString = "Spectator";
    GameMode gamemode = GameMode.SPECTATOR;

    public Spectator(PluginMain plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean hasAdminPerm = sender.hasPermission("kapingaddons.gamemode.other");

        if(sender instanceof ConsoleCommandSender) {
            if(args.length > 0) {
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null) {
                    MessageHandler.Error.sendDoesntExist("player");
                }
                assert player != null;

                player.setGameMode(gamemode);
                MessageHandler.Console.sendMessage(ConfigHandler.messageConfig.getString("messages.gamemode.other").replace("%player%", player.getName()).replace("%gamemode%", gamemodeString));
            } else {
                MessageHandler.Error.sendArgs("/gmsp <player>");
            }
            return true;
        }
        if(sender instanceof Player) {
            if(args.length > 0 && hasAdminPerm) {
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null) {
                    MessageHandler.Error.sendDoesntExist((Player) sender, "player");
                }
                assert player != null;
                player.setGameMode(gamemode);
                MessageHandler.InGame.sendMessage((Player) sender, ConfigHandler.messageConfig.getString("messages.gamemode.other").replace("%player%", player.getName()).replace("%gamemode%", gamemodeString));
            };
            Player player = (Player) sender;
            player.setGameMode(gamemode);
            MessageHandler.InGame.sendMessage(player, plugin.getConfig().getString("messages.gamemode.self").replace("%gamemode%", gamemodeString));
            return true;
        }
        return false;
    }
}