package me.labstorm.bspawn.commands;

import me.labstorm.bspawn.Main;
import me.labstorm.bspawn.utils.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("spawn.admin.reload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
        Main.confObj = new Config();
        Main.reload();
        sender.sendMessage(ChatColor.GREEN + "Config was reloaded!");
        return true;
    }
}
