package com.github.jorepong.jenchant;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scroll {
    private Enchantment enchantment;
    private double success;
    private double failure;
    private double levelDown;
    private double destroy;
    private int needUpgradeLevel;

    public Scroll(Enchantment enchantment, double success, double failure, double levelDown, double destroy) {
        this(enchantment, success, failure, levelDown, destroy, 1);
    }

    public Scroll(Enchantment enchantment, double success, double failure, double levelDown, double destroy, int needUpgradeLevel) {
        this.enchantment = enchantment;
        this.success = success;
        this.failure = failure;
        this.levelDown = levelDown;
        this.destroy = destroy;
        this.needUpgradeLevel = needUpgradeLevel;
    }

    public Scroll(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();

        J_Enchant plugin = J_Enchant.getInstance();
        NamespacedKey enchantmentKey = new NamespacedKey(plugin, "enchantment");
        NamespacedKey successKey = new NamespacedKey(plugin, "success");
        NamespacedKey failureKey = new NamespacedKey(plugin, "failure");
        NamespacedKey levelDownKey = new NamespacedKey(plugin, "level_down");
        NamespacedKey destroyKey = new NamespacedKey(plugin, "destroy");
        NamespacedKey upgradeLevelKey = new NamespacedKey(plugin, "upgrade_level");

        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        this.enchantment = Enchantment.getByKey(NamespacedKey.fromString(data.get(enchantmentKey, PersistentDataType.STRING)));
        this.success = data.get(successKey, PersistentDataType.DOUBLE);
        this.failure = data.get(failureKey, PersistentDataType.DOUBLE);
        this.levelDown = data.get(levelDownKey, PersistentDataType.DOUBLE);
        this.destroy = data.get(destroyKey, PersistentDataType.DOUBLE);
        this.needUpgradeLevel = data.get(upgradeLevelKey, PersistentDataType.INTEGER);
    }

    public int getNeedUpgradeLevel() {
        return needUpgradeLevel;
    }

    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + EnchantUtils.getName(enchantment) + ChatColor.WHITE + " 부여 주문서");
        savePersistentData(itemMeta.getPersistentDataContainer());

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "- 마우스를 이용해 주문서를 든 채로 마법 부여 대상 아이템을");
        lore.add(ChatColor.GRAY + "좌클릭하여 사용할 수 있습니다.");
        lore.add(ChatColor.GRAY + "- 강화 대상의 업그레이드 가능 횟수를 " + ChatColor.RED + "1" + ChatColor.GRAY + "만큼 소모합니다.");
        lore.add("");
        if (success > 0) lore.add(ChatColor.WHITE + "성공: " + ChatColor.GOLD + success + "%");
        if (failure > 0) lore.add(ChatColor.WHITE + "실패: " + ChatColor.GRAY + failure + "%");
        if (levelDown > 0) lore.add(ChatColor.WHITE + "하락: " + ChatColor.DARK_GRAY + levelDown + "%");
        if (destroy > 0 ) lore.add(ChatColor.WHITE + "파괴: " + ChatColor.DARK_RED + destroy + "%");
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        return item;
    }

    private void savePersistentData(PersistentDataContainer container) {
        J_Enchant plugin = J_Enchant.getInstance();
        container.set(new NamespacedKey(plugin, "enchantment"), PersistentDataType.STRING, enchantment.getKey().toString());
        container.set(new NamespacedKey(plugin, "success"), PersistentDataType.DOUBLE, success);
        container.set(new NamespacedKey(plugin, "failure"), PersistentDataType.DOUBLE, failure);
        container.set(new NamespacedKey(plugin, "level_down"), PersistentDataType.DOUBLE, levelDown);
        container.set(new NamespacedKey(plugin, "destroy"), PersistentDataType.DOUBLE, destroy);
        container.set(new NamespacedKey(plugin, "upgrade_level"), PersistentDataType.INTEGER, needUpgradeLevel);
    }

    public static boolean isValidScroll(ItemStack item) {
        return item.getType() == Material.ENCHANTED_BOOK && item.getItemMeta().getDisplayName().contains("부여 주문서");
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getRandomOutcome() {
        double total = success + failure + levelDown + destroy;
        double rand = new Random().nextDouble() * total;

        if (rand < success) {
            return 0;
        } else if (rand < success + failure) {
            return 1;
        } else if (rand < success + failure + levelDown) {
            return 2;
        } else {
            return 3;
        }
    }
}
