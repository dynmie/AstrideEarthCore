package me.dynmie.astrideearthcore.listeners;

import me.dynmie.astrideearthcore.Main;
import me.dynmie.astrideearthcore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;


public class cannotCraft implements Listener {
    Main plugin;
    public cannotCraft(Main plugin) {this.plugin = plugin;}

    @EventHandler
    public void craftItem(PrepareItemCraftEvent event) {
        if (event.getRecipe() == null) {
            return;
        }
        Material itemType = event.getRecipe().getResult().getType();
        for(HumanEntity he:event.getViewers()) {
            Player player = (Player) he;
            if(itemType==Material.HOPPER) {
                if (player.hasPermission("astride.craftexempt")) {
                    return;
                } else {
                    event.getInventory().setResult(new ItemStack(Material.AIR));
                    player.sendMessage(Utils.chat(this.plugin.getConfig().getString("CannotCraft")));
                }
            }
        }
    }
}
