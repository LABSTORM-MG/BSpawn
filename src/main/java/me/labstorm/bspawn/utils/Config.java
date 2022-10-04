package me.labstorm.bspawn.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;

public class Config {

    private final File file;
    private final YamlConfiguration config;

    public Config() {
        Utils.msg("Config-Config");
        File dir = new File("./plugins/BSpawn/");
        boolean dirWasCreated = false;
        if (!dir.exists()) {
            dirWasCreated = dir.mkdirs();
        }

        this.file = new File(dir, "config.yml");
        boolean fileWasCreated = false;
        if (!file.exists()) {
            try {
                fileWasCreated = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = loadConfiguration(file);
        if (dirWasCreated || fileWasCreated) {
            generateConfig();
        }
        save();
    }

    private void generateConfig() {
        Utils.msg("Config-generateConfig");
        config.set("clear-inventory-on-spawn", true);
        config.set("heal-player-on-spawn", true);
        config.set("send-player-to-spawn-on-join", true);
        config.set("spawn-location", null);
    }

    public YamlConfiguration getConfig() {
        Utils.msg("Config-getConfig");
        return config;
    }

    public void save() {
        Utils.msg("Config-save");
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
