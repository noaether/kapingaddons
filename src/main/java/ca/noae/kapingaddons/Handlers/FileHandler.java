package ca.noae.kapingaddons.Handlers;

import ca.noae.kapingaddons.PluginMain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FileHandler {
  private static PluginMain plugin;
  private static File dataFolder;
  private static File inventoriesFolder;
  private static File locationsFolder;

  public FileHandler(PluginMain plugin) {
    FileHandler.plugin = plugin;

    dataFolder = plugin.getDataFolder();
    inventoriesFolder = new File(dataFolder, "inventories");
    locationsFolder = new File(dataFolder, "locations");

    checkFolders();
  };

  public static void checkFolders() {
    if (!inventoriesFolder.exists()) {
      inventoriesFolder.mkdirs();
    }
    ;
    if (!locationsFolder.exists()) {
      locationsFolder.mkdirs();
    }
    ;
  };

  public static class inventory {
    // Save the player's inventory to a file
    public static void saveInventory(Player player) throws IOException {
      File file = new File(inventoriesFolder, player.getUniqueId() + ".inv");
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        PlayerInventory inventory = player.getInventory();
        // Write the inventory size
        writer.write(Integer.toString(inventory.getSize()));
        writer.newLine();
        // Write the inventory contents
        for (int i = 0; i < inventory.getSize(); i++) {
          ItemStack item = inventory.getItem(i);
          if (item != null) {
            writer.write(Integer.toString(i));
            writer.write(',');
            writer.write(item.getType().toString());
            writer.write(',');
            writer.write(Integer.toString(item.getAmount()));
            writer.write(',');
            writer.write(Short.toString(item.getDurability()));
            writer.newLine();
          }
        }
        // Write the armor contents
        ItemStack[] armor = inventory.getArmorContents();
        for (int i = 0; i < armor.length; i++) {
          ItemStack item = armor[i];
          if (item != null) {
            writer.write("armor");
            writer.write(',');
            writer.write(Integer.toString(i));
            writer.write(',');
            writer.write(item.getType().toString());
            writer.write(',');
            writer.write(Integer.toString(item.getAmount()));
            writer.write(',');
            writer.write(Short.toString(item.getDurability()));
            writer.newLine();
          }
        }
      }
    }

    // Load the player's inventory from a file
    // Load the player's inventory from a file
    // Load the player's inventory from a file
    public static void loadInventory(Player player) throws IOException {
      File file = new File(inventoriesFolder, player.getUniqueId() + ".inv");
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        int size = Integer.parseInt(reader.readLine());
        PlayerInventory inventory = player.getInventory();
        inventory.clear();
        for (int i = 0; i < size; i++) {
          String line = reader.readLine();
          if (line != null) {
            String[] parts = line.split(",");
            int index;
            try {
              index = Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
              index = -1;
            }
            if (index == -1) {
              // This is an armor item
              index = Integer.parseInt(parts[1]);
              Material material = Material.valueOf(parts[2]);
              int amount = Integer.parseInt(parts[3]);
              short durability = Short.parseShort(parts[4]);
              ItemStack item = new ItemStack(material, amount, durability);

              // Set the armor item
              ItemStack[] armor = player.getInventory().getArmorContents();
              armor[index] = item;
              player.getInventory().setArmorContents(armor);
            } else {
              // This is an inventory item
              Material material = Material.valueOf(parts[1]);
              int amount = Integer.parseInt(parts[2]);
              short durability = Short.parseShort(parts[3]);
              ItemStack item = new ItemStack(material, amount, durability);
              inventory.setItem(index, item);
            }
          }
        }
      }
    }

  }

  public static class location {
    // Save the player's location to a file
    public static void saveLocation(Player player) throws IOException {
      File file = new File(locationsFolder, player.getUniqueId() + ".loc");
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        Location location = player.getLocation();
        writer.write(Double.toString(location.getX()));
        writer.newLine();
        writer.write(Double.toString(location.getY()));
        writer.newLine();
        writer.write(Double.toString(location.getZ()));
        writer.newLine();
        writer.write(Float.toString(location.getYaw()));
        writer.newLine();
        writer.write(Float.toString(location.getPitch()));
        writer.newLine();
        writer.write(location.getWorld().getName());
      }
    }

    // Load the player's location from a file
    public static void loadLocation(Player player) throws IOException {
      File file = new File(locationsFolder, player.getUniqueId() + ".loc");
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        double x = Double.parseDouble(reader.readLine());
        double y = Double.parseDouble(reader.readLine());
        double z = Double.parseDouble(reader.readLine());
        float yaw = Float.parseFloat(reader.readLine());
        float pitch = Float.parseFloat(reader.readLine());
        String worldName = reader.readLine();
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
          Location location = new Location(world, x, y, z, yaw, pitch);
          player.teleport(location);
        }
      }
    }
  }
}
