package me.dynmie.astrideearthcore.commands;


import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class flyCommand implements CommandExecutor {
    Main plugin;
    public flyCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.fly"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return false;
        }
        if (!(player.getAllowFlight())) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(Utils.chat("&aFlying &lenabled&a."));
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(Utils.chat("&cFlying &ldisabled&c."));
        }

        return true;
    }
}



//player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));