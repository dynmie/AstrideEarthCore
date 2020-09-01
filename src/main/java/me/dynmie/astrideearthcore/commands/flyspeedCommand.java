package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class flyspeedCommand implements CommandExecutor {
    Main plugin;
    public flyspeedCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.speed.flyspeed"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return false;
        }
        if (args.length == 0) {
            player.sendMessage(Utils.chat("&cProvide a speed from &l1 - 10&c."));
            return false;
        }
        int speed;
        try {
            speed = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(Utils.chat("&cYou did not specify a valid speed."));
//            if (player.isFlying()) {
//                player.setWalkSpeed(0.2f);
//            } else {
//                player.setFlySpeed(0.1f);
//            }
//            player.sendMessage(Utils.chat("&aYour current speed has been reset."));
            return false;
        }
        if (speed < 1 || speed > 10) {
            player.sendMessage(Utils.chat("&cProvide a speed from &l1 - 10&c."));
            return false;
        }
        player.setFlySpeed((float) speed / 10);
        player.sendMessage(Utils.chat("&aYour fly speed has been set to &l" + speed + "&a."));
        return true;
    }
}
