package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    Main plugin;
    public Feed(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.feed"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        } else {
            player.sendMessage(Utils.chat("&aYou have been fed."));
            player.setFoodLevel(20);
        }
        return true;
    }
}
