package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.commands.vanishCommand;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class vanishListener implements Listener {
    Main plugin;
    public vanishListener(Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = Main.getChat().getPlayerPrefix(player);
        if (player.hasPermission("astride.vanish.vanish")) {
            vanishCommand.vanishedPlayers.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false));
            player.setAllowFlight(true);
            player.setPlayerListName(Utils.chat("&7[VANISHED] &o") + player.getDisplayName());
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!(online.hasPermission("astride.vanish.see"))) {
                    online.hidePlayer(plugin, player);
                }
            }
            player.sendMessage(Utils.chat(plugin.getConfig().getString("enabled-vanish")));
        } else {
            player.setPlayerListName(Utils.displayName(prefix, player.getDisplayName()));
        }
        if (!(player.hasPermission("astride.vanish.see"))) {
            for (int i = 0; i < vanishCommand.vanishedPlayers.size(); i++) {
                player.hidePlayer(plugin, vanishCommand.vanishedPlayers.get(i));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        vanishCommand.vanishedPlayers.remove(player);
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getTarget())) {
                event.setCancelled(true);
            }
        }
    }

   @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getDamager())) {
                event.setCancelled(true);
            }
        } else if (event.getEntity() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (vanishCommand.vanishedPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (vanishCommand.vanishedPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (vanishCommand.vanishedPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerCropTrample(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(vanishCommand.vanishedPlayers.contains(player))) return;
        if (event.getAction() != Action.PHYSICAL) return;
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().toString().matches("SOIL|FARMLAND"))  event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (vanishCommand.vanishedPlayers.contains(p)) e.setDeathMessage(null);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (vanishCommand.vanishedPlayers.contains(player) && !vanishCommand.fullVanishedPlayers.contains(player)) {
            if (player.hasPermission("astride.vanish.vanish")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (!(online.hasPermission("astride.vanish.see"))) {
                        online.hidePlayer(plugin, player);
                    }
                }
            }
            if (!(player.hasPermission("astride.vanish.see"))) {
                for (int i = 0; i < vanishCommand.vanishedPlayers.size(); i++) {
                    player.hidePlayer(plugin, vanishCommand.vanishedPlayers.get(i));
                }
            }
        } else if (vanishCommand.vanishedPlayers.contains(player) && vanishCommand.fullVanishedPlayers.contains(player)) {
            if (player.hasPermission("astride.vanish.full")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (!(online.hasPermission("astride.vanish.exempt"))) {
                        online.hidePlayer(plugin, player);
                    }
                }
            }
            if (!(player.hasPermission("astride.vanish.exempt"))) {
                for (int i = 0; i < vanishCommand.vanishedPlayers.size(); i++) {
                    player.hidePlayer(plugin, vanishCommand.vanishedPlayers.get(i));
                }
            }
        }
    }

    public static int vanishedPlayers() {
        return Bukkit.getOnlinePlayers().size() - vanishCommand.vanishedPlayers.size();
    }

}
