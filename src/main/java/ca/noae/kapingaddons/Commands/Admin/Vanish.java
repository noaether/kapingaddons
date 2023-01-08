package ca.noae.kapingaddons.Commands.Admin;

import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.LogHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;
import ca.noae.kapingaddons.Handlers.PlayerHandler;
import ca.noae.kapingaddons.PluginMain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    private final PluginMain plugin;
    public Set<UUID> vanished;

    public Vanish(PluginMain plugin) {
        this.plugin = plugin;
        this.vanished = new HashSet<>();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            // The sender is not a player, so return
            return true;
        }

        Player player = (Player) sender;

        if (PlayerHandler.VanishedPlayers.isVanished(player))  {
            // The player is vanished, so unvanish them
            PlayerHandler.VanishedPlayers.removePlayer(player);
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.showPlayer(plugin, player);
            }
            MessageHandler.InGame.sendMessage(player, ConfigHandler.messageConfig.getString("messages.unvanish"));
            LogHandler.Console.info(player.getName() + " unvanished");
        } else {
            // The player is not vanished, so vanish them
            PlayerHandler.VanishedPlayers.addPlayer(player);
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.hidePlayer(plugin, player);
            }
            MessageHandler.InGame.sendMessage(player, ConfigHandler.messageConfig.getString("messages.vanish"));
            LogHandler.Console.info(player.getName() + " vanished");
        }

        return true;
    }
}
