package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class workbenchCommand implements CommandExecutor {
    Main plugin;
    public workbenchCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (plugin.getConfig().getBoolean("lobby")) return true;

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("astride.workbench"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            player.openWorkbench(null, true);
        } else {

            if (!(player.hasPermission("astride.workbench.others"))) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
                return true;
            }

            Player target = getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
            } else {
                target.openWorkbench(null, true);
            }
            return true;

        }

        return true;
    }
}
