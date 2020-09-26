package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class vanishCommand implements TabExecutor {
    public static ArrayList<Player> vanishedPlayers = new ArrayList<>();
    public static ArrayList<Player> fullVanishedPlayers = new ArrayList<>();

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

        if (args.length > 0) {
            if (!(args[0].equalsIgnoreCase("full") || args[0].equalsIgnoreCase("f"))) return true;
            if (!(player.hasPermission("astride.vanish.full"))) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
                return true;
            }
            if (fullVanishedPlayers.contains(player)) {
                fullVanishedPlayers.remove(player);
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (online.hasPermission("astride.vanish.see")) {
                        online.showPlayer(plugin, player);
                    }
                }
                if (!(player.hasPermission("astride.fly"))) {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                }

                player.setPlayerListName(Utils.displayName(prefix, player.getDisplayName()));
                player.sendMessage("§cFull Vanish for " + player.getDisplayName() + " has been §ldisabled§c.");
            } else if (!(fullVanishedPlayers.contains(player))){
                if (!(vanishedPlayers.contains(player))) vanishedPlayers.add(player);
                fullVanishedPlayers.add(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (!(online.hasPermission("astride.vanish.exempt"))) {
                        online.hidePlayer(plugin, player);
                    }
                }
                player.setPlayerListName("§7[VANISHED] §o" + player.getDisplayName());
                player.setAllowFlight(true);
                player.sendMessage("§bFull Vanish for " + player.getDisplayName() + " has been §lenabled§b.");
            }
            return true;
        }

        if (vanishedPlayers.contains(player)) {
            vanishedPlayers.remove(player);
            fullVanishedPlayers.remove(player);
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }
            if (!(player.hasPermission("astride.fly"))) {
                player.setFlying(false);
                player.setAllowFlight(false);
            }
            player.setPlayerListName(Utils.displayName(prefix, player.getDisplayName()));
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.sendMessage("§cVanish for " + player.getDisplayName() + " has been §ldisabled§c.");

        } else if (!(vanishedPlayers.contains(player))){
            vanishedPlayers.add(player);
            fullVanishedPlayers.remove(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(online.hasPermission("astride.vanish.see"))) {
                    online.hidePlayer(plugin, player);
                }
            }
            player.setPlayerListName("§7[VANISHED] §o" + player.getDisplayName());
            player.setAllowFlight(true);
            player.sendMessage("§bVanish for " + player.getDisplayName() + " has been §lenabled§b.");
        }


        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 0) {
            list.add("full");
            Collections.sort(list);
            return list;
        } else if (args.length == 1) {
            list.add("full");
            list.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            Collections.sort(list);
            return list;
        }
        return null;
    }
}
