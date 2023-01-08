package ca.noae.kapingaddons.Handlers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ca.noae.kapingaddons.PluginMain;
import ca.noae.kapingaddons.Customs.FakeJoinEvent;
import ca.noae.kapingaddons.Customs.FakeLeaveEvent;

public class PlayerHandler {
  private static PluginMain plugin;

  public PlayerHandler(PluginMain plugin) {
    PlayerHandler.plugin = plugin;
  }

  public static class VanishedPlayers {
    private final static Set<UUID> vanished = new HashSet<>();

    public static void addPlayer(Player player) {
      vanished.add(player.getUniqueId());
      fakeLeave(player, ConfigHandler.messageConfig.getString("messages.quit").replace("%player%", player.getName()));
    }

    public static void removePlayer(Player player) {
      vanished.remove(player.getUniqueId());
      fakeJoin(player, ConfigHandler.messageConfig.getString("messages.join").replace("%player%", player.getName()));
    }

    public static boolean isVanished(Player player) {
      return vanished.contains(player.getUniqueId());
    }

    public static void fakeLeave(Player player, String leaveMessage) {
      FakeLeaveEvent event = new FakeLeaveEvent(player);
      event.setLeaveMessage(leaveMessage);
      Bukkit.getPluginManager().callEvent(event);
    };

    public static void fakeJoin(Player player, String joinMessage) {
      FakeJoinEvent event = new FakeJoinEvent(player);
      event.setJoinMessage(joinMessage);
      Bukkit.getPluginManager().callEvent(event);
    }

  }
}