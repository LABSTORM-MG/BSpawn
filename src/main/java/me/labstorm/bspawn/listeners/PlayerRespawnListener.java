package me.labstorm.bspawn.listeners;

import me.labstorm.bspawn.Main;
import me.labstorm.bspawn.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        e.setRespawnLocation(Main.SPAWN_LOCATION);
        new BukkitRunnable() {
            @Override
            public void run() {
                Utils.spawnLogic(e.getPlayer(), false);
            }
        }.runTaskLater(Main.getInstance(), 1);
    }

}
