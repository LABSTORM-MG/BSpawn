package me.labstorm.bspawn.listeners;

import me.labstorm.bspawn.Main;
import me.labstorm.bspawn.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Main.SPAWN_LOCATION == null) {
            if (p.hasPermission("spawn.admin.setspawn")) {
                p.sendMessage(ChatColor.RED + "Please set the server-spawn with /setspawn !");
            } else {
                p.sendMessage(ChatColor.RED + "There is a configuration error, please notify the server owner.");
            }
            return;
        }
        Utils.spawnLogic(p, false);
    }
}

