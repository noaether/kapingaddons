package ca.noae.kapingaddons;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import ca.noae.kapingaddons.Handlers.PermissionHandler;
import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.FileHandler;
import ca.noae.kapingaddons.Handlers.LogHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;

import ca.noae.kapingaddons.Commands.Admin.AdminMode;
import ca.noae.kapingaddons.Commands.Admin.Vanish;
import ca.noae.kapingaddons.Commands.Gamemode.Adventure;
import ca.noae.kapingaddons.Commands.Gamemode.Creative;
import ca.noae.kapingaddons.Commands.Gamemode.Spectator;
import ca.noae.kapingaddons.Commands.Gamemode.Survival;
import ca.noae.kapingaddons.EventListeners.PlayerConnections;

import java.util.Objects;

public final class PluginMain extends JavaPlugin implements Listener {

    private static PluginMain instance;

    @Override
    public void onEnable() {
            instance = this;
            if (!getDataFolder().exists()) getDataFolder().mkdir();
            this.saveDefaultConfig();
        getInstance().getLogger().info("Kapinga Kalunda arrive ! Cachez vous !");


        PlayerConnections playerConnections = new PlayerConnections(this);

            getServer().getPluginManager().registerEvents(this, this);
            getServer().getPluginManager().registerEvents(playerConnections, this);
        getInstance().getLogger().info("EventListeners; they do be listening");

            new ConfigHandler(this);
            new PermissionHandler(this);
            new LogHandler(this);
            new MessageHandler(this);
            new FileHandler(this);
        getInstance().getLogger().info("Handlers; they do be handling");

            Objects.requireNonNull(this.getCommand("gma")).setExecutor(new Adventure(this));
            Objects.requireNonNull(this.getCommand("gmc")).setExecutor(new Creative(this));
            Objects.requireNonNull(this.getCommand("gmsp")).setExecutor(new Spectator(this));
            Objects.requireNonNull(this.getCommand("gms")).setExecutor(new Survival(this));

            Objects.requireNonNull(this.getCommand("adminmode")).setExecutor(new AdminMode(this));
            Objects.requireNonNull(this.getCommand("vanish")).setExecutor(new Vanish(this));
        getInstance().getLogger().info("CommandExecutors; they do be executing");

        // create two folders in the plugin folder

        getInstance().getLogger().info("Noae (c) 2022-2023");

    }

    @Override
    public void onDisable() {
        this.saveConfig();
        getInstance().getLogger().info("Kapinga Kalunda s'en va ! Enfin !");

        getInstance().getLogger().info("Noae (c) 2022-2023");
    }

    public static PluginMain getInstance() {
        return instance;
    }
}
