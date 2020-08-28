package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Heal implements CommandExecutor {
    public Heal() {

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.heal"))) {
            player.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
            return true;
        } else {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setFireTicks(0);
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.sendMessage(Utils.chat("&aYou have been healed."));
        }
        return true;
    }
}
