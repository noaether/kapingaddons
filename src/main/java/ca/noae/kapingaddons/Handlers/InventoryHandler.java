package ca.noae.kapingaddons.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import ca.noae.kapingaddons.PluginMain;

public class InventoryHandler {
  private static PluginMain plugin;

  public InventoryHandler(PluginMain plugin) {
    InventoryHandler.plugin = plugin;
  }

  public static class adminMode {
    static File dataFolder = plugin.getDataFolder();

    public static void saveToFile(Player player) {
      try {
        // Save the inventory to a file
        File file = new File(dataFolder, player.getUniqueId() + ".ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(player.getInventory());
        oos.writeObject(player.getInventory().getArmorContents());
        oos.writeObject(player.getInventory().getItemInMainHand());
        oos.writeObject(player.getLocation());
        oos.close();
        fos.close();
        player.sendMessage("Inventory saved.");
      } catch (IOException e) {
        player.sendMessage("Error saving inventory.");
        e.printStackTrace();
      }
    }

    public static void restoreFromFile(Player player) {
      File file = new File(dataFolder, player.getUniqueId() + ".ser");
      if (file.exists()) {
        try {
          // Restore the inventory from a file
          FileInputStream fis = new FileInputStream(file);
          ObjectInputStream ois = new ObjectInputStream(fis);
          PlayerInventory inventory = (PlayerInventory) ois.readObject();
          ItemStack[] armor = (ItemStack[]) ois.readObject();
          ItemStack mainHand = (ItemStack) ois.readObject();
          Location location = (Location) ois.readObject();
          player.getInventory().setContents(inventory.getContents());
          player.getInventory().setArmorContents(armor);
          player.getInventory().setItemInMainHand(mainHand);
          player.teleport(location);
          ois.close();
          fis.close();
          player.sendMessage("Inventory restored.");
        } catch (IOException | ClassNotFoundException e) {
          player.sendMessage("Error restoring inventory.");
          e.printStackTrace();
        }
      } else {
        player.sendMessage("No saved inventory found.");
      }
    }
  }

}
