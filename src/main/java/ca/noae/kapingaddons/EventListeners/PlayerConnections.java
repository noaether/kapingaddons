package ca.noae.kapingaddons.EventListeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ca.noae.kapingaddons.PluginMain;
import ca.noae.kapingaddons.Customs.FakeJoinEvent;
import ca.noae.kapingaddons.Customs.FakeLeaveEvent;
import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;

public class PlayerConnections implements Listener {
    private final PluginMain plugin;

    public PlayerConnections(PluginMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(
                ConfigHandler.messageConfig.getString("messages.join").replace("%player%", player.getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(
                ConfigHandler.messageConfig.getString("messages.quit").replace("%player%", player.getName()));
    }

    @EventHandler
    public void onFakeLeave(FakeLeaveEvent event) {
        Player player = event.getPlayer();
        String leaveMessage = event.getLeaveMessage();
        // Send the leave message to all online players
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(leaveMessage);
        }
    }

    @EventHandler
    public void onFakeJoin(FakeJoinEvent event) {
        Player player = event.getPlayer();
        String joinMessage = event.getJoinMessage();
        // Send the join message to all online players
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.sendMessage(joinMessage);
        }
    }
}
