package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Bukkit.getPlayer;


public class nightVision implements CommandExecutor {
    public nightVision() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("Only players can execute this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.nightvision"))) {
            player.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
            return true;
        }

        if (args.length == 0) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(Utils.chat("&cNight Vision &ldisabled&c."));
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
                player.sendMessage(Utils.chat("&aNight Vision &lenabled&a."));
            }
            return true;
        }

        if (args.length == 1) {
            Player target = getPlayer(args[0]);
            if (getPlayer(args[0]) == null) {
                sender.sendMessage(Utils.chat("&cThat player is offline."));
                return true;
            } else {
                if (target.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    target.sendMessage(Utils.chat("&cNight Vision &ldisabled&c."));
                    player.sendMessage(Utils.chat("&cNight Vision &ldisabled&c for &l" + target.getDisplayName() + "&c."));
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
                    target.sendMessage(Utils.chat("&aNight Vision &lenabled&a."));
                    player.sendMessage(Utils.chat("&aNight Vision &lenabled&a for &l" + target.getDisplayName() + "&a."));
                }
                return true;
            }
        }

        return false;
    }
}
