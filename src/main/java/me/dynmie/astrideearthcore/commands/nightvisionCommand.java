package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Bukkit.getPlayer;


public class nightvisionCommand implements CommandExecutor {
    Main plugin;
    public nightvisionCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat("Only players can execute this command!"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("astride.nightvision"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                player.sendMessage(Utils.chat("&cNight Vision for " + player.getDisplayName() + " has been &ldisabled&c."));
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
                player.sendMessage(Utils.chat("&bNight Vision for " + player.getDisplayName() + " has been &lenabled&b."));
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
                    player.sendMessage(Utils.chat("&cNight Vision for " + target.getDisplayName() + " has been &ldisabled&c."));
                } else {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0));
                    player.sendMessage(Utils.chat("&bNight Vision for " + target.getDisplayName() + " has been &lenabled&b."));
                }
                return true;
            }
        }

        return false;
    }
}
