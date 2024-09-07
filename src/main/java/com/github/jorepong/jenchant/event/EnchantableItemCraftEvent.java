package com.github.jorepong.jenchant.event;

import com.github.jorepong.jenchant.EnchantableItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantableItemCraftEvent implements Listener {
    @EventHandler
    public void onEnchantableItemCraft(PrepareItemCraftEvent event) {
        ItemStack resultItem = event.getInventory().getResult();
        Bukkit.broadcastMessage("test");
        if(resultItem != null && EnchantableItem.isEnchantableItem(resultItem.getType())) {
            Bukkit.broadcastMessage("test2");
            EnchantableItem enchantableItem = new EnchantableItem(resultItem);
            enchantableItem.attachEnchantableLoreAndContainer(resultItem);
        }
    }
}
