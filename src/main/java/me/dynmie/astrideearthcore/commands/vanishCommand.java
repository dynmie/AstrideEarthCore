package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class vanishCommand implements CommandExecutor {
    public static ArrayList<Player> vanishedPlayers = new ArrayList<>();

    Main plugin;
    public vanishCommand(Main plugin) {this.plugin = plugin;}

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }
        Player player = (Player) sender;
        String prefix = Main.getChat().getPlayerPrefix(player);

        if (!(player.hasPermission("astride.vanish.vanish"))) {
            sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("noperms")));
            return true;
        }

        if (vanishedPlayers.contains(player)) {
            vanishedPlayers.remove(player);
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }
            if (!(player.hasPermission("astride.fly"))) {
                player.setFlying(false);
                player.setAllowFlight(false);
            }
            player.setPlayerListName(Utils.displayName(prefix, player.getDisplayName()));
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage(Utils.chat(plugin.getConfig().getString("disabled-vanish")));
        } else if (!(vanishedPlayers.contains(player))){
            vanishedPlayers.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(online.hasPermission("astride.vanish.see"))) {
                    online.hidePlayer(plugin, player);
                }
            }
            player.setPlayerListName("§7[VANISHED] §o" + player.getDisplayName());
            player.setAllowFlight(true);
            player.sendMessage(Utils.chat(plugin.getConfig().getString("enabled-vanish")));
        }


        return true;
    }
}
