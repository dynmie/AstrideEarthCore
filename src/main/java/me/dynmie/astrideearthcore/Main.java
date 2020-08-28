package me.dynmie.astrideearthcore;

import me.dynmie.astrideearthcore.commands.*;
import me.dynmie.astrideearthcore.listeners.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class Main extends JavaPlugin {
    public messageManager mM;
    private static Economy econ;
    private static Permission perms;
    private static Chat chat;

    @Override
    public void onEnable() {
        /*if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }*/
        configLoader();
        startCommands();
        registerEvents();
        tabCompleter();
        setupPermissions();
        setupChat();
        setupEconomy();
        rankLoader();
    }

    @Override
    public void onDisable() {
        getLogger().info("AstrideEarth Disabled");
    }

    public void startCommands() {
        getLogger().info("AstrideEarth Enabled");
        getCommand("fly").setExecutor(new Fly());
        getCommand("speed").setExecutor(new Speed());
        getCommand("enderchest").setExecutor(new enderChest(this));
        getCommand("gamemode").setExecutor(new Gamemode());
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("message").setExecutor(new messageCommand(this));
        getCommand("reply").setExecutor(new replyCommand(this));
        getCommand("flyspeed").setExecutor(new flySpeed());
        getCommand("walkspeed").setExecutor(new walkSpeed());
        getCommand("workbench").setExecutor(new workbench(this));
        getCommand("kill").setExecutor(new Kill());
        getCommand("sudo").setExecutor(new sudo());
        getCommand("vanish").setExecutor(new vanish(this));
        getCommand("disguise").setExecutor(new disguise());
        getCommand("nightvision").setExecutor(new nightVision());
        getCommand("chat").setExecutor(new chat(this, this));
        getCommand("killall").setExecutor(new killAll(this));
        getCommand("list").setExecutor(new list());
        getCommand("invsee").setExecutor(new invsee(this));
        getCommand("freeze").setExecutor(new freeze(this));
        getCommand("teleport").setExecutor(new teleport(this));
        getCommand("tpall").setExecutor(new tpall(this));
    }

    public void tabCompleter() {
        getCommand("gamemode").setTabCompleter(new Gamemode());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new joinLeaveMessages(this), this);
        getServer().getPluginManager().registerEvents(new vanishManager(this), this);
        getServer().getPluginManager().registerEvents(new ToggleChat(this), this);
        getServer().getPluginManager().registerEvents(new SlowMode(this), this);
        getServer().getPluginManager().registerEvents(new cannotCraft(this), this);
        getServer().getPluginManager().registerEvents(new freezeManager(this), this);
        mM = new messageManager(this);
    }

    public void configLoader() {
        saveDefaultConfig();
        reloadConfig();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    public void rankLoader() {
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
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setPlayerListHeaderFooter("\nHeader, " + vanishManager.vanishedPlayers() + " online.\n", "\nFooter\n");
                    String group = Main.getChat().getPrimaryGroup(player);
                    if (group.equalsIgnoreCase("owner")) {
                        owner.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("manage")) {
                        manage.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("sradmin")) {
                        sradmin.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("admin")) {
                        admin.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("mod")) {
                        mod.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("helper")) {
                        helper.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("builder")) {
                        builder.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("dragon")) {
                        dragon.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("wither")) {
                        wither.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("shulker")) {
                        shulker.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("blaze")) {
                        blaze.addEntry(player.getName());
                    } else if (group.equalsIgnoreCase("zombie")) {
                        zombie.addEntry(player.getName());
                    } else {
                        default1.addEntry(player.getName());
                    }
                }
            }
        }.runTaskTimer(this, 0, 40);


}
}

