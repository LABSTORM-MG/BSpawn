package me.labstorm.bspawn.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;

public class Config {

    private final File file;
    private final YamlConfiguration config;

    public Config() {
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
        config.createSection("on-spawn-command");
        config.setComments("on-spawn-command",
                           new ArrayList<>(Arrays.asList(
                                   "add commands below starting with \"- \"instead of \"/\",u can use %player% as a placeholder e.g.:,on-spawn-command:, - gamemode creative %player%, - execute as %player% run say hello".split(
                                           ","))));
        config.set("clear-inventory-on-spawn", true);
        config.set("heal-player-on-spawn", true);
        config.set("send-player-to-spawn-on-join", true);
        config.set("spawn-location", null);
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
