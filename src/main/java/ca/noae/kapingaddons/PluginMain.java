package ca.noae.kapingaddons;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

import ca.noae.kapingaddons.Handlers.PermissionHandler;
import ca.noae.kapingaddons.Handlers.ConfigHandler;
import ca.noae.kapingaddons.Handlers.LogHandler;
import ca.noae.kapingaddons.Handlers.MessageHandler;

import ca.noae.kapingaddons.Commands.Gamemode.Adventure;
import ca.noae.kapingaddons.Commands.Gamemode.Creative;
import ca.noae.kapingaddons.Commands.Gamemode.Spectator;
import ca.noae.kapingaddons.Commands.Gamemode.Survival;

import java.util.Objects;

public final class PluginMain extends JavaPlugin implements Listener {

    private static PluginMain instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getInstance().getLogger().info("Kapinga Kalunda arrive ! Cachez vous !");

        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("gma")).setExecutor(new Adventure(this));
        Objects.requireNonNull(this.getCommand("gmc")).setExecutor(new Creative(this));
        Objects.requireNonNull(this.getCommand("gmsp")).setExecutor(new Spectator(this));
        Objects.requireNonNull(this.getCommand("gms")).setExecutor(new Survival(this));
        getInstance().getLogger().info("CommandExecutors; they do be executing");

        getInstance().getLogger().info("EventListeners; they do be listening");

        new ConfigHandler(this);
        new PermissionHandler(this);
        new LogHandler(this);
        new MessageHandler(this);
        getInstance().getLogger().info("Handlers; they do be handling");

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
