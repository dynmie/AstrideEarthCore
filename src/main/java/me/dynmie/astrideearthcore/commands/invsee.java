package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class invsee implements CommandExecutor {
    Main plugin;
    public invsee(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.invsee"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
        }

        if (args.length == 0) {
            player.sendMessage(Utils.chat("&cUsage: /" + command.getName() + " <player>"));
            return true;
        }

        if (getPlayer(args[0]) == null) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
            return true;
        }
        Player target = getPlayer(args[0]);
        player.openInventory(target.getInventory());

        return true;
    }
}
