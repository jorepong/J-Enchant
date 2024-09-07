package com.github.jorepong.jenchant;

import com.github.jorepong.jenchant.event.EnchantableItemCraftEvent;
import com.github.jorepong.jenchant.event.EnchantScrollUseEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class J_Enchant extends JavaPlugin {

    private static J_Enchant instance = null;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginCommand("test").setExecutor(new TestCommand());
        getServer().getPluginManager().registerEvents(new EnchantScrollUseEvent(), this);
        getServer().getPluginManager().registerEvents(new EnchantableItemCraftEvent(), this);
        EnchantableItem.initAvailableUpgradeCount();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static J_Enchant getInstance() {
        return instance;
    }
}
