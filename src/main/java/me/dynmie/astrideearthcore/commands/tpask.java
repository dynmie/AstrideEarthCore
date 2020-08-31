package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static org.bukkit.Bukkit.getPlayer;

public class tpask implements CommandExecutor {
    Main plugin;
    public tpask(Main plugin) {
        this.plugin = plugin;
    }

    public HashMap<Player, Player> teleportAskPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Utils.chat("&cUsage: /" + command.getName() + " <player>"));
            return true;
        }

        if (args.length == 1) {
            Player target = getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("OfflinePlayer")));
                return true;
            }

            if (teleportAskPlayers.containsKey(player)) {
                teleportAskPlayers.remove(player);
                teleportAskPlayers.put(player, target);
                player.sendMessage(Utils.chat("&aSent a t"));
                return true;
            }
        }


        return true;
    }
}
