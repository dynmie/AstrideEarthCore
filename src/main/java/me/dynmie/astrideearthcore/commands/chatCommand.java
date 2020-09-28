package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class chatCommand implements CommandExecutor {
    public static ArrayList<Player> chatSpy = new ArrayList<>();

    private Main plugin;
    public chatCommand(Main main, Main plugin) {
        this.plugin = plugin;
    }
    public static boolean muted = false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("astride.chat"))) {
            sender.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(Utils.chat("&cUsage: /chat <mute|slow|clear|spy>"));
            return true;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            int i;
            for (i = 0; i < 100; i++)
                for(Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(" ");
             }
            Bukkit.broadcastMessage(Utils.chat("&bThe chat has been cleared by &f" + sender.getName() + "&b."));
            Bukkit.broadcastMessage(" ");
            return true;
        }

        if (args[0].equalsIgnoreCase("mute")) {
            if (!(muted)) {
                muted = true;
                Bukkit.broadcastMessage(Utils.chat("&bThe chat has been muted by &f" + sender.getName() + "&b."));
            } else {
                muted = false;
                Bukkit.broadcastMessage(Utils.chat("&bThe chat has been unmuted."));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("slow")) {
            if (args.length == 1) {
                sender.sendMessage(Utils.chat("&aThe chat slow is currently set to &l" + plugin.getConfig().getInt("slowtime") + " seconds&b."));
                return true;
            } else {
                try {
                    int interval = Integer.parseInt(args[1]);
                    plugin.getConfig().set("slowtime", interval);
                    plugin.saveConfig();
                    Bukkit.broadcastMessage(Utils.chat("&bThe chat has been slowed to &f" + interval + " second" + (interval == 1 ? "" : "s") + " &bby &f" + sender.getName() + "&a."));
                } catch (NumberFormatException e) {
                    sender.sendMessage(Utils.chat("&cProvide a valid number."));
                }
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("spy")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Utils.chat(plugin.getConfig().getString("console")));
                return true;
            }

            Player player = (Player) sender;

            if (!(player.hasPermission("astride.chat.spy"))) {
                player.sendMessage(Utils.chat(plugin.getConfig().getString("noperms")));
                return true;
            }


            if (chatSpy.contains(player)) {
                chatSpy.add(player);
                player.sendMessage("§bSpy Mode for " + player.getDisplayName() + " has been §fenabled§b.");
            } else {
                chatSpy.remove(player);
                player.sendMessage("§bSpy Mode for " + player.getDisplayName() + " has been §fdisabled§b.");
            }
            return true;
        }

        return true;
    }
}
