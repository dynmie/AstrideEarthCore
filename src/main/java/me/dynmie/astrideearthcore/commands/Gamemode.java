package me.dynmie.astrideearthcore.commands;

import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.*;

import static org.bukkit.Bukkit.getPlayer;

public class Gamemode implements TabExecutor {
    private Map<String, GameMode> modes = new HashMap<>();

    // Set its values when the class is created
    // This way this map is only set ONCE as it should be
    public Gamemode() {
        modes.put("0", GameMode.SURVIVAL);
        modes.put("1", GameMode.CREATIVE);
        modes.put("2", GameMode.ADVENTURE);
        modes.put("3", GameMode.SPECTATOR);
        modes.put("survival", GameMode.SURVIVAL);
        modes.put("creative", GameMode.CREATIVE);
        modes.put("adventure", GameMode.ADVENTURE);
        modes.put("spectator", GameMode.SPECTATOR);
        modes.put("s", GameMode.SURVIVAL);
        modes.put("c", GameMode.CREATIVE);
        modes.put("a", GameMode.ADVENTURE);
        modes.put("t", GameMode.ADVENTURE);
        modes.put("sp", GameMode.SPECTATOR);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args.length > 2) {
            sender.sendMessage(Utils.chat("&cUsage: /gamemode <adventure|creative|spectator|survival>"));
        } else {
            Player player;
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    // message saying cannot set the gamemode of console
                    sender.sendMessage(Utils.chat("&cOnly players can execute this command!"));
                    // requiring a player arg
                    return true;
                }
                // if no player entered in the command, then player = sender
                player = ((Player) sender);

            } else {
                // If player entered in command, we try find said player
                player = getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(Utils.chat("&cThat player is offline."));
                    return true;
                }
            }

            if (!(player.hasPermission("astride.gamemode"))) {
                player.sendMessage(Utils.chat("&cYou do not have permission to execute this command."));
                return true;
            }

            // Now we check if the modes map contains the arg they typed
            // This saves having to do a whole bunch of conditions for each possible gamemode entry
            if (!modes.containsKey(args[0])) {
                // message saying the arg isn't one of the gamemode choices
                sender.sendMessage(Utils.chat("&cThis isn't a valid gamemode!"));
                return true;
            }

            // If all system are go, we then set the gamemode based on the arg they typed, getting it from the map
            GameMode mode = modes.get(args[0]);
            player.setGameMode(mode);

            // Send messages based on who's gamemode was being set (obviously add color codes, etc!
            if (player != sender) {
                sender.sendMessage(Utils.chat("&aYou have set " + player.getName() + "'s gamemode to &l" + mode.toString().toLowerCase() + "&a."));
            }
            player.sendMessage(Utils.chat("&aYou have set your gamemode to &l" + mode.toString().toLowerCase() + "&a."));

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
