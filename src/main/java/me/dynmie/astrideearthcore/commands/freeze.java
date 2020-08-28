package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getPlayer;

public class freeze implements CommandExecutor {

    public static ArrayList<Player> frozenPlayers = new ArrayList<>();

    Main plugin;
    public freeze(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("astride.freeze"))) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("&cThat player is offline.");
            return true;
        }

        Player target = getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("PlayerOffline")));
            return true;
        }

        if (target.hasPermission("astride.freeze.exempt")) {
            sender.sendMessage(Utils.chat("&cYou cannot freeze this player."));
            return true;
        }

        if (frozenPlayers.contains(target)) {
            frozenPlayers.remove(target);
            target.setWalkSpeed(0.2f);
            target.setFlySpeed(0.1f);
            target.sendMessage(Utils.chat("&bYou have been unfrozen."));
            sender.sendMessage(Utils.chat("&f" + target.getName() + " &ahas been unfrozen."));
        } else {
            frozenPlayers.add(target);
            target.setWalkSpeed(0);
            target.setFlySpeed(0);
            target.sendMessage(Utils.chat("&bYou have been frozen by &f" + sender.getName()));
            sender.sendMessage(Utils.chat("&f" + target.getName() + " &ahas been frozen."));
        }

        return true;
    }
}
