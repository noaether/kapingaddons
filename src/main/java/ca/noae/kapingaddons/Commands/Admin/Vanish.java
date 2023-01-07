package ca.noae.kapingaddons.Commands.Admin;

import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;
import ca.noae.kapingaddons.PluginMain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    private final PluginMain plugin;
    private List<UUID> vanished = new ArrayList<>();

    public Vanish(PluginMain plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("stuff.vanish")) {

            if (vanished.contains(player.getUniqueId())) { // their are vanished
                vanished.remove(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.showPlayer(plugin, player);
                }
                MessageHandler.InGame.sendMessage((Player) sender,
                        ConfigHandler.messageConfig.getString("messages.vanish.off"));
            } else { // not vanished
                vanished.add(player.getUniqueId());

                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.hidePlayer(plugin, player);
                }

                MessageHandler.InGame.sendMessage((Player) sender,
                        ConfigHandler.messageConfig.getString("messages.vanish.on"));
            }
        } else {
            MessageHandler.Error.sendNoPermission((Player) sender);
        }
        return true;
    }
}
