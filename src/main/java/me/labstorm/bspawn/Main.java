package me.labstorm.bspawn;

import me.labstorm.bspawn.commands.ReloadCommand;
import me.labstorm.bspawn.commands.SetSpawnCommand;
import me.labstorm.bspawn.commands.SpawnCommand;
import me.labstorm.bspawn.listeners.PlayerJoinListener;
import me.labstorm.bspawn.listeners.PlayerRespawnListener;
import me.labstorm.bspawn.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    public static boolean CLEAR_INV;
    public static boolean HEAL_PLAYER;
    public static boolean TP_PLAYER;
    public static Location SPAWN_LOCATION;
    public static Config confObj;
    private static Main instance;

    public static YamlConfiguration getConfiguration() {
        return confObj.getConfig();
    }

    public static void reload() {
        YamlConfiguration config = confObj.getConfig();
        CLEAR_INV = config.getBoolean("clear-inventory-on-spawn");
        HEAL_PLAYER = config.getBoolean("heal-player-on-spawn");
        TP_PLAYER = config.getBoolean("send-player-to-spawn-on-join");
        SPAWN_LOCATION = config.getLocation("spawn-location");
    }

    public static Config getConfObj() {
        return confObj;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        confObj = new Config();
    }

    @Override
    public void onDisable() {
        confObj.save();
    }

    @Override
    public void onEnable() {
        Main.reload();
        if (Main.SPAWN_LOCATION == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please set the server-spawn with /setspawn !");
        }
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand());
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(getCommand("reloadspawnconfig")).setExecutor(new ReloadCommand());
    }

}
