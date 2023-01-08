package ca.noae.kapingaddons.EventListeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import ca.noae.kapingaddons.Handlers.PlayerHandler.VanishedPlayers;

public class ChatInteractions implements Listener {
  private VanishedPlayers vanishedPlayers;

  public void TabCompleteListener(VanishedPlayers vanishedPlayers) {
      this.vanishedPlayers = vanishedPlayers;
  }

  @EventHandler
  public void onTabComplete(TabCompleteEvent event) {
      List<String> completions = event.getCompletions();
      // Remove vanished players from the list of completions
      completions.removeIf(name -> {
          Player player = Bukkit.getPlayer(name);
          return player != null && vanishedPlayers.isVanished(player);
      });
  }
}
