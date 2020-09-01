package me.dynmie.astrideearthcore;

import me.dynmie.astrideearthcore.commands.*;
import me.dynmie.astrideearthcore.listeners.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public final class Main extends JavaPlugin {
    public messagingListener mM;
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
        getCommand("fly").setExecutor(new flyCommand());
        getCommand("speed").setExecutor(new speedCommand());
        getCommand("enderchest").setExecutor(new enderchestCommand(this));
        getCommand("gamemode").setExecutor(new gamemodeCommand());
        getCommand("heal").setExecutor(new healCommand());
        getCommand("feed").setExecutor(new feedCommand());
        getCommand("message").setExecutor(new messageCommand(this));
        getCommand("reply").setExecutor(new replyCommand(this));
        getCommand("flyspeed").setExecutor(new flyspeedCommand());
        getCommand("walkspeed").setExecutor(new walkspeedCommand());
        getCommand("workbench").setExecutor(new workbenchCommand(this));
        getCommand("kill").setExecutor(new killCommand());
        getCommand("sudo").setExecutor(new sudoCommand());
        getCommand("vanish").setExecutor(new vanishCommand(this));
        getCommand("disguise").setExecutor(new disguiseCommand());
        getCommand("nightvision").setExecutor(new nightvisionCommand());
        getCommand("chat").setExecutor(new chatCommand(this, this));
        getCommand("killall").setExecutor(new killallCommand(this));
        getCommand("list").setExecutor(new listCommand());
        getCommand("invsee").setExecutor(new invseeCommand(this));
        getCommand("freeze").setExecutor(new freezeCommand(this));
        getCommand("teleport").setExecutor(new teleportCommand(this));
        getCommand("tpall").setExecutor(new tpallCommand(this));
        getCommand("tpask").setExecutor(new tpaskCommand(this));
    }

    public void tabCompleter() {
        getCommand("gamemode").setTabCompleter(new gamemodeCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new joinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new vanishListener(this), this);
        getServer().getPluginManager().registerEvents(new chatToggleListener(this), this);
        getServer().getPluginManager().registerEvents(new chatSlowListener(this), this);
        getServer().getPluginManager().registerEvents(new craftListener(this), this);
        getServer().getPluginManager().registerEvents(new freezeListener(this), this);
        mM = new messagingListener(this);
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
                    player.setPlayerListHeaderFooter("\nHeader, " + vanishListener.vanishedPlayers() + " online.\n", "\nFooter\n");
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

