package me.labstorm.bspawn.utils;

import me.labstorm.bspawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Utils {

    public static void spawnLogic(Player p, boolean forceTp) {
        Utils.msg("Utils.spawnLogic");
        if (Main.CLEAR_INV) {
            p.getInventory().clear();
        }

        if (Main.HEAL_PLAYER) {
            p.setHealth(Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue());
            p.setFoodLevel(18);
        }

        if (forceTp || Main.TP_PLAYER) {
            p.teleport(Main.SPAWN_LOCATION);
        }
    }

    public static void msg(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + msg);
    }

}
