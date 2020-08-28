package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getWorld;

public class teleport implements CommandExecutor {

    Main plugin;
    public teleport(Main plugin) {this.plugin = plugin;}

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
                player.sendMessage(Utils.chat("&aTeleported to &f" + target.getName() + "&a."));
            } else {
                player.sendMessage(Utils.chat("&aTeleported &f" + target.getName() + "&a to " + target2.getName() + "&a."));
            }
            return true;
        }

        double x = 0;
        double y = 0;
        double z = 0;
        try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            z = Integer.parseInt(args[2]);
        } catch (NumberFormatException error) {
            player.sendMessage(Utils.chat("&cThat isn't a valid location."));
        }
        player.teleport(new Location(getWorld(player.getWorld().toString()), x, y, z));
        player.sendMessage(Utils.chat("&aTeleported to &fx: " + x + " y: " + y + " z: " + z + "&a."));

        return true;
    }
}
