package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpall implements CommandExecutor {
    Main plugin;
    public tpall(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission("astride.teleport.all"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        for (Player online : Bukkit.getOnlinePlayers()) {
            online.teleport(player);
            if (!(online == sender)) {
                online.sendMessage(Utils.chat("&aYou have been teleported to &f" + player.getDisplayName() + "&a."));
            }
        }
        player.sendMessage(Utils.chat("&aTeleported all players to your location."));
        return true;
    }
}