package me.labstorm.bspawn.commands;

import me.labstorm.bspawn.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a Player!");
            return true;
        }
        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "Unknown command please use: /setspawn");
            return true;
        }
        if (!sender.hasPermission("spawn.admin.setspawn")) {
            sender.sendMessage(ChatColor.RED + "You don't have the permissions to do that!");
            return true;
        }

        Player p = ((Player) sender).getPlayer();
        assert p != null;
        YamlConfiguration config = Main.getConfiguration();
        config.set("spawn-location", p.getLocation());
        Main.getConfObj().save();
        Main.reload();
        sender.sendMessage(ChatColor.GREEN + "Spawn Set!");
        return true;
    }
}
