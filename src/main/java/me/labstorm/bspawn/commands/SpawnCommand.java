package me.labstorm.bspawn.commands;

import me.labstorm.bspawn.Main;
import me.labstorm.bspawn.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils.msg("SpawnCommand-onCommand");
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player!");
            return true;
        }

        if (Main.SPAWN_LOCATION == null) {
            if (sender.hasPermission("spawn.admin.setspawn")) {
                sender.sendMessage(ChatColor.RED + "Please set the server-spawn with /setspawn !");
            } else {
                sender.sendMessage(ChatColor.RED + "There is a configuration error, please notify the server owner.");
            }
            return true;
        }

        if (args.length == 0) {
            Player p = ((Player) sender).getPlayer();
            assert p != null;
            Utils.spawnLogic(p, true);
            sender.sendMessage(ChatColor.GOLD + "Teleporting to spawn...");
        } else if (args.length == 1) {
            if (!sender.hasPermission("permisson: spawn.admin.others")) {
                sender.sendMessage(ChatColor.RED + "You don't have the permissions to do that!");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                sender.sendMessage(ChatColor.RED + "The player " + args[0] + "was not found!");
                return true;
            }
            Utils.spawnLogic(p, true);
            sender.sendMessage(ChatColor.GOLD + "Teleporting " + args[0] + " to spawn...");
        } else {
            sender.sendMessage(ChatColor.RED + "Unknown command please use: /spawn [<player>]");
        }

        return true;
    }
}
