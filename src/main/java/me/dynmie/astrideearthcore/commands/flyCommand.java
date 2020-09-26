package me.dynmie.astrideearthcore.commands;


import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;


public class flyCommand implements CommandExecutor {
    Main plugin;
    public flyCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.fly"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (!(args.length == 1)) {
            if (!(player.getAllowFlight())) {
                player.setAllowFlight(true);
                player.sendMessage(Utils.chat("&bFlight Mode for " + player.getDisplayName() + "&lenabled&a."));
            } else {
                player.setAllowFlight(false);
                player.sendMessage(Utils.chat("&cFlight Mode for " + player.getDisplayName() + "&ldisabled&c."));
            }
        } else {
            Player target = getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
            } else {
                if (target.getAllowFlight()) {
                    target.setAllowFlight(false);
                    player.sendMessage(Utils.chat("&cFlight Mode for " + target.getDisplayName() + "&ldisabled&c."));
                } else {
                    target.setAllowFlight(true);
                    player.sendMessage(Utils.chat("&bFlight Mode for " + target.getDisplayName() + "&lenabled&a."));
                }
                return true;
            }
        }
        return true;
    }
}