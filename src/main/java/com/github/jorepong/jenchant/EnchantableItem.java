package com.github.jorepong.jenchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class EnchantableItem {
    private final ItemStack item;
    private static Map<Material, Integer> availableUpgradeCount = new HashMap<>();

    public EnchantableItem(ItemStack item) {
        this.item = item;
        initAvailableUpgradeCount();
    }

    public static void initAvailableUpgradeCount() {
        availableUpgradeCount.put(Material.WOODEN_SHOVEL, 8);
        availableUpgradeCount.put(Material.WOODEN_AXE, 8);
        availableUpgradeCount.put(Material.WOODEN_HOE, 8);
        availableUpgradeCount.put(Material.WOODEN_SWORD, 12);
        availableUpgradeCount.put(Material.WOODEN_PICKAXE, 16);
        availableUpgradeCount.put(Material.STONE_AXE, 8);
        availableUpgradeCount.put(Material.STONE_HOE, 8);
        availableUpgradeCount.put(Material.STONE_SWORD, 8);
        availableUpgradeCount.put(Material.STONE_PICKAXE, 8);
        availableUpgradeCount.put(Material.STONE_SHOVEL, 8);
        availableUpgradeCount.put(Material.IRON_AXE, 8);
        availableUpgradeCount.put(Material.IRON_HOE, 8);
        availableUpgradeCount.put(Material.IRON_SWORD, 8);
        availableUpgradeCount.put(Material.IRON_PICKAXE, 8);
        availableUpgradeCount.put(Material.IRON_SHOVEL, 8);
        availableUpgradeCount.put(Material.GOLDEN_AXE, 8);
        availableUpgradeCount.put(Material.GOLDEN_HOE, 8);
        availableUpgradeCount.put(Material.GOLDEN_SWORD, 8);
        availableUpgradeCount.put(Material.GOLDEN_PICKAXE, 8);
        availableUpgradeCount.put(Material.GOLDEN_SHOVEL, 8);
        availableUpgradeCount.put(Material.DIAMOND_AXE, 8);
        availableUpgradeCount.put(Material.DIAMOND_HOE, 8);
        availableUpgradeCount.put(Material.DIAMOND_SWORD, 8);
        availableUpgradeCount.put(Material.DIAMOND_PICKAXE, 8);
        availableUpgradeCount.put(Material.DIAMOND_SHOVEL, 8);
        availableUpgradeCount.put(Material.NETHERITE_AXE, 8);
        availableUpgradeCount.put(Material.NETHERITE_HOE, 8);
        availableUpgradeCount.put(Material.NETHERITE_PICKAXE, 8);
        availableUpgradeCount.put(Material.NETHERITE_SWORD, 8);
        availableUpgradeCount.put(Material.NETHERITE_SHOVEL, 8);
        availableUpgradeCount.put(Material.FISHING_ROD, 8);
        availableUpgradeCount.put(Material.FLINT_AND_STEEL, 8);
        availableUpgradeCount.put(Material.SHEARS, 8);
        availableUpgradeCount.put(Material.ELYTRA, 8);
        availableUpgradeCount.put(Material.TRIDENT, 8);
        availableUpgradeCount.put(Material.LEATHER_HELMET, 8);
        availableUpgradeCount.put(Material.LEATHER_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.LEATHER_LEGGINGS, 8);
        availableUpgradeCount.put(Material.LEATHER_BOOTS, 8);
        availableUpgradeCount.put(Material.CHAINMAIL_HELMET, 8);
        availableUpgradeCount.put(Material.CHAINMAIL_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.CHAINMAIL_LEGGINGS, 8);
        availableUpgradeCount.put(Material.CHAINMAIL_BOOTS, 8);
        availableUpgradeCount.put(Material.IRON_HELMET, 8);
        availableUpgradeCount.put(Material.IRON_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.IRON_LEGGINGS, 8);
        availableUpgradeCount.put(Material.IRON_BOOTS, 8);
        availableUpgradeCount.put(Material.GOLDEN_HELMET, 8);
        availableUpgradeCount.put(Material.GOLDEN_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.GOLDEN_LEGGINGS, 8);
        availableUpgradeCount.put(Material.GOLDEN_BOOTS, 8);
        availableUpgradeCount.put(Material.DIAMOND_HELMET, 8);
        availableUpgradeCount.put(Material.DIAMOND_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.DIAMOND_LEGGINGS, 8);
        availableUpgradeCount.put(Material.DIAMOND_BOOTS, 8);
        availableUpgradeCount.put(Material.NETHERITE_HELMET, 8);
        availableUpgradeCount.put(Material.NETHERITE_CHESTPLATE, 8);
        availableUpgradeCount.put(Material.NETHERITE_LEGGINGS, 8);
        availableUpgradeCount.put(Material.NETHERITE_BOOTS, 8);
        availableUpgradeCount.put(Material.TURTLE_HELMET, 8);
        availableUpgradeCount.put(Material.BOW, 8);
        availableUpgradeCount.put(Material.CROSSBOW, 8);
    }

    public static boolean isValidEnchantableItem(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(new NamespacedKey(J_Enchant.getInstance(), "upgradeNumber"), PersistentDataType.INTEGER);
    }

    private int getCurrentUpgradeNumber() {
        ItemMeta itemMeta = item.getItemMeta();
        if(!itemMeta.getPersistentDataContainer().has(new NamespacedKey(J_Enchant.getInstance(), "upgradeNumber"), PersistentDataType.INTEGER))
            return -1;
        int upgradeNumber = itemMeta.getPersistentDataContainer().get(new NamespacedKey(J_Enchant.getInstance(), "upgradeNumber"), PersistentDataType.INTEGER);
        return upgradeNumber;
    }

//    public void decreaseUpgradeNumber(int requiredUpgradeNumber) {
//
//        decreaseUpgradeNumberAtContainer(item, (getRemainUpgradeNumber() - requiredUpgradeNumber));
//    }

    public static void decreaseUpgradeNumberAtContainer(ItemStack item, int upgradeNumber) {
    }

    public void attachEnchantableLoreAndContainer(ItemStack item) {
        attachEnchantableLore(item);
        attachEnchantableContainer(item);
    }

    private void attachEnchantableLore(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = itemMeta.getLore();
        if (lore == null)
            lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "업그레이드 가능 횟수: " + availableUpgradeCount.get(item.getType()) + "회");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    private void attachEnchantableContainer(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(new NamespacedKey(J_Enchant.getInstance(), "upgradeNumber"), PersistentDataType.INTEGER, availableUpgradeCount.get(item.getType()));
        item.setItemMeta(itemMeta);
    }

    public static boolean isEnchantableItem(Material material) {
        return availableUpgradeCount.containsKey(material);
    }

    public boolean decreaseUpgradeCount(int count) {
        int currentUpgradeNumber = getCurrentUpgradeNumber();

        if (currentUpgradeNumber - count < 0)
            return false;

        decreaseUpgradeCountAtLore(currentUpgradeNumber, count);
        decreaseUpgradeCountAtContainer(currentUpgradeNumber, count);
        return true;
    }

    private void decreaseUpgradeCountAtLore(int currentUpgradeNumber, int count) {
        Bukkit.broadcastMessage(currentUpgradeNumber + "");
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = itemMeta.getLore();
        Iterator<String> iterator = lore.iterator();
        int targetLoreIndex = 0;
        while(iterator.hasNext()) {
            String currentLore = iterator.next();
            if(currentLore.equals(ChatColor.GRAY + "업그레이드 가능 횟수: " + currentUpgradeNumber + "회")) {
                break;
            }
            targetLoreIndex += 1;
        }
        lore.set(targetLoreIndex, ChatColor.GRAY + "업그레이드 가능 횟수: " + (currentUpgradeNumber - count) + "회");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    private void decreaseUpgradeCountAtContainer(int currentUpgradeNumber, int count) {
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(new NamespacedKey(J_Enchant.getInstance(), "upgradeNumber"), PersistentDataType.INTEGER, (currentUpgradeNumber - count));
        item.setItemMeta(itemMeta);
    }

    public EnchantOutcome attemptEnchant(Scroll scroll) {
        Enchantment enchantment = scroll.getEnchantment();
        int currentLevel = item.getEnchantmentLevel(enchantment);
        int outcome = scroll.getRandomOutcome();

        switch (outcome) {
            case 0:
                item.addUnsafeEnchantment(enchantment, currentLevel + 1);
                return EnchantOutcome.SUCCESS;
            case 1:
                return EnchantOutcome.FAILURE;
            case 2:
                if (currentLevel > 1) {
                    item.addUnsafeEnchantment(enchantment, currentLevel - 1);
                    return EnchantOutcome.LEVEL_DOWN;
                }
            case 3:
                item.setAmount(0);
                return EnchantOutcome.DESTROYED;
            default:
        }

        return EnchantOutcome.FAILURE;
    }
}
