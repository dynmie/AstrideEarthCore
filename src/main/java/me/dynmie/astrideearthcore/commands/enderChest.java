package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class enderChest implements CommandExecutor {
    Main plugin;
    public enderChest(Main plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.chat(this.plugin.getConfig().getString("console")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.hasPermission("astride.enderchest")) {
                player.openInventory(player.getEnderChest());
                return true;
            }
        }

        if (args.length > 0) {
            if (player.hasPermission("astride.enderchest.others")) {
                Player target = getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(Utils.chat(this.plugin.getConfig().getString("PlayerOffline")));
                } else {
                    player.openInventory(target.getEnderChest());
                }
                return true;
            }
        } else {
            player.sendMessage(Utils.chat(this.plugin.getConfig().getString("noperms")));
            return true;
        }
        return true;
    }
}
