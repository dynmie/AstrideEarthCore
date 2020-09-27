package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.PlayerData;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

import static org.bukkit.Bukkit.getPlayer;


public class gamemodeCommand implements TabExecutor {
    Main plugin;
    public gamemodeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("astride.gamemode"))) {
            player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Utils.chat("&cUsage: /gamemode <adventure|creative|spectator|survival> [player]"));
            return true;
        }

        Player target = getPlayer(args[1]);
        if (!(target == null) && args.length == 2) {
            if (args[0].equalsIgnoreCase("creative")) {
                target.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Utils.chat("&bGame Mode for " + target.getDisplayName() + " has been set to &fcreative&b."));
            } else if (args[0].equalsIgnoreCase("survival")) {
                target.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Utils.chat("&bGame Mode for " + target.getDisplayName() + " has been set to &fsurvival&b."));
            } else if (args[0].equalsIgnoreCase("adventure")) {
                target.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(Utils.chat("&bGame Mode for " + target.getDisplayName() + " has been set to &fadventure&b."));
            } else if (args[0].equalsIgnoreCase("spectator")) {
                target.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Utils.chat("&bGame Mode for " + target.getDisplayName() + " has been set to &fspectator&b."));
            }
            return true;
        } else {
            if (args[0].equalsIgnoreCase("creative")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Utils.chat("&bGame Mode for " + player.getDisplayName() + " has been set to &fcreative&b."));
            } else if (args[0].equalsIgnoreCase("survival")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Utils.chat("&bGame Mode for " + player.getDisplayName() + " has been set to &fsurvival&b."));
            } else if (args[0].equalsIgnoreCase("adventure")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(Utils.chat("&bGame Mode for " + player.getDisplayName() + " has been set to &fadventure&b."));
            } else if (args[0].equalsIgnoreCase("spectator")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Utils.chat("&bGame Mode for " + player.getDisplayName() + " has been set to &fspectator&b."));
            }
        }
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 0) {
            list.add("creative");
            list.add("survival");
            list.add("adventure");
            list.add("spectator");
            Collections.sort(list);
            return list;
        } else if (args.length == 1) {
            list.add("creative");
            list.add("survival");
            list.add("adventure");
            list.add("spectator");
            list.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            Collections.sort(list);
            return list;
        }
        return null;
    }
}
