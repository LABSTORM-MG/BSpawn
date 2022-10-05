package me.labstorm.bspawn.utils;

import me.labstorm.bspawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public class Utils {

    public static void spawnLogic(Player p, boolean forceTp) {
        if (Main.CLEAR_INV) {
            p.getInventory().clear();
        }

        if (Main.HEAL_PLAYER) {
            p.setHealth(Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue());
            p.setFoodLevel(20);
        }

        if (forceTp || Main.TP_PLAYER) {
            p.teleport(Main.SPAWN_LOCATION);
        }
    }

    public static void onSpawnCommand(Player p) {
        YamlConfiguration config = Main.getConfiguration();
        List<?> commands = config.getList("on-spawn-command");
        if (commands == null) {
            return;
        }
        for (Object c : commands) {
            Bukkit.getServer()
                  .dispatchCommand(Bukkit.getConsoleSender(),
                                   String.valueOf(c).replace("%player%", p.getDisplayName()));
        }
    }

}
