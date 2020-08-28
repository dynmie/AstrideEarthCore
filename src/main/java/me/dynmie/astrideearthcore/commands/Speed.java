package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {
    public Speed() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.walkspeed") || (player.hasPermission("astride.flyspeed")))) {
            player.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
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
/*            if (player.isFlying()) {
                player.setWalkSpeed(0.2f);
            } else {
                player.setFlySpeed(0.1f);
            }
            player.sendMessage(Utils.chat("&aYour current speed has been reset.")); */
            return false;
        }
        if (speed < 1 || speed > 10) {
            player.sendMessage(Utils.chat("&cProvide a speed from &l1 - 10&c."));
            return false;
        }
        if (player.isFlying()) {
            player.setFlySpeed((float) speed / 10);
            player.sendMessage(Utils.chat("&aYou have set your flying speed to &l" + speed + "&a."));
        } else {
            player.setWalkSpeed((float) speed / 10);
            player.sendMessage(Utils.chat("&aYou have set your walking speed to &l" + speed + "&a."));
        }
        return true;
    }
}
