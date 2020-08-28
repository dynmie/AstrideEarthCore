package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class tabList implements Listener {
    Main plugin;

    public tabList(Main plugin) {
        this.plugin = plugin;
    }

    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    Scoreboard board = scoreboardManager.getNewScoreboard();
    Team owner = board.registerNewTeam("0010owner");
    Team manage = board.registerNewTeam("0020manage");
    Team sradmin = board.registerNewTeam("0030sradmin");
    Team admin = board.registerNewTeam("0040admin");
    Team mod = board.registerNewTeam("0050mod");
    Team helper = board.registerNewTeam("0060helper");
    Team builder = board.registerNewTeam("0070builder");
    Team dragon = board.registerNewTeam("0080dragon");
    Team wither = board.registerNewTeam("0090wither");
    Team shulker = board.registerNewTeam("0100shulker");
    Team blaze = board.registerNewTeam("0110blaze");
    Team zombie = board.registerNewTeam("0120zombie");
    Team default1 = board.registerNewTeam("0130default");

    private boolean titleChanged;
    public void onJoinEvent(PlayerJoinEvent event) {

        new BukkitRunnable() {

            @Override
            public void run() {
                event.getPlayer().sendMessage("hi");
            }
        }.runTaskTimer(plugin, 0, 20);
    }

}
