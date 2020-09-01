package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.String.valueOf;
import static org.bukkit.Bukkit.*;

public class teleportCommand implements CommandExecutor {

    Main plugin;
    public teleportCommand(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("astride.teleport"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Utils.chat("&cUsage: /tp <player> [otherplayer]"));
            return true;
        }

        if (args.length == 1) {
            Player target = getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
                return true;
            } else {
                player.teleport(target);
            }
            player.sendMessage(Utils.chat("&aTeleported to &f" + target.getName() + "&a."));
            return true;
        }

        if (args.length == 2) {
            Player target = getPlayer(args[0]);
            Player target2 = getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
                return true;
            }
            if (target2 == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
                return true;
            }
            target.teleport(target2);
            if (target.getName() == player.getName()) {
                player.sendMessage(Utils.chat("&aYou have been teleported to &f" + target.getName() + "&a."));
            } else {
                player.sendMessage(Utils.chat("&aYou have teleported " + target.getName() + "&a to " + target2.getName() + "&a."));
            }
            return true;
        }

        double x;
        double y;
        double z;
        try {
            x = args[0].startsWith("~") ? player.getLocation().getX() + Integer.parseInt(args[0].substring(1)) : Integer.parseInt(args[0]);
            y = args[1].startsWith("~") ? player.getLocation().getY() + Integer.parseInt(args[1].substring(1)) : Integer.parseInt(args[1]);
            z = args[2].startsWith("~") ? player.getLocation().getZ() + Integer.parseInt(args[2].substring(1)) : Integer.parseInt(args[2]);
        } catch (NumberFormatException error) {
            player.sendMessage(Utils.chat("&cThat isn't a valid location."));
            return true;
        }
        if (x > 30000000 || y > 30000000 || z > 30000000 || x < -30000000 || y < -30000000 || z < -30000000) {
            player.sendMessage(Utils.chat("&cThat isn't a valid location."));
            return true;
        }
        player.teleport(new Location(player.getWorld(), x, y, z, player.getLocation().getYaw(), player.getLocation().getPitch()));
        player.sendMessage(Utils.chat("&aTeleported to &fx: " + x + "&a,&f y: " + y + "&a,&f z: " + z + "&a."));
        return true;
    }
}
