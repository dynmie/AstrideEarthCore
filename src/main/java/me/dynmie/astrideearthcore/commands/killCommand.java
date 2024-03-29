package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class killCommand implements CommandExecutor {
    Main plugin;
    public killCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("astride.kill"))) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.chat("&cProvide a player to kill."));
            } else {
                Player player = (Player) sender;
                player.setHealth(0);
                player.sendMessage(Utils.chat("&bYou have killed &f" + player.getName() + "&a."));
            }
            return true;
        }

        if (getPlayer(args[0]) == null) {
            sender.sendMessage(Utils.chat("&cThat player is offline."));
            return true;
        } else {
            Player target = getPlayer(args[0]);
            target.setHealth(0);
            sender.sendMessage(Utils.chat("&bYou have killed &f" + target.getName() + "&a."));
        }

        return true;
    }
}
