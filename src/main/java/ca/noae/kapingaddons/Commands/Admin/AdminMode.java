package ca.noae.kapingaddons.Commands.Admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import ca.noae.kapingaddons.PluginMain;
import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.FileHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;

public class AdminMode implements CommandExecutor {
    private final PluginMain plugin;

    public AdminMode(PluginMain plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            MessageHandler.Error.sendNoConsole();
            return true;
        }

        boolean hasAdminPerm = sender.hasPermission("kapingaddons.admin.adminmode");

        if (sender instanceof Player && hasAdminPerm) {
            Player player = (Player) sender;
            if (args.length == 0) {
                MessageHandler.Error.sendArgs(player, "/adminmode <on/off>");
            } else if (args[0].equalsIgnoreCase("on")) {
                try {
                    FileHandler.inventory.saveInventory(player);
                    FileHandler.location.saveLocation(player);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                player.getInventory().clear();
                player.setGameMode(GameMode.SPECTATOR);
                MessageHandler.InGame.sendMessage(player,
                        ConfigHandler.messageConfig.getString("messages.adminmode.on"));
            } else if (args[0].equalsIgnoreCase("off")) {
                player.getInventory().clear();
                try {
                    FileHandler.inventory.loadInventory(player);
                    FileHandler.location.loadLocation(player);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                player.setGameMode(GameMode.SURVIVAL);
                MessageHandler.InGame.sendMessage(player,
                        ConfigHandler.messageConfig.getString("messages.adminmode.off"));
            } else {
                MessageHandler.Error.unknown(player);
            }
        } else {
            MessageHandler.Error.sendNoPermission((Player) sender);
            return true;
        }
        return false;
    }
}
