package com.github.jorepong.jenchant.event;

import com.github.jorepong.jenchant.EnchantAnimation;
import com.github.jorepong.jenchant.EnchantOutcome;
import com.github.jorepong.jenchant.EnchantableItem;
import com.github.jorepong.jenchant.Scroll;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;

public class EnchantScrollUseEvent implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // 유효한 클릭 AND 아이템인지 확인
        if(!isValidClick(event))
            return;
        else
            event.setCancelled(true);

        Scroll scroll = new Scroll(event.getCursor());
        EnchantableItem item = new EnchantableItem(event.getCurrentItem());

        if(!doEnchant(scroll, item)) {
            player.sendMessage(ChatColor.RED + "마법 부여 대상의 업그레이드 가능 횟수가 부족합니다.");
            return;
        }

        EnchantAnimation ea = new EnchantAnimation();
        ea.showEnchantAnimation(player);
        EnchantOutcome outcome = item.attemptEnchant(scroll);
        switch (outcome) {
            case SUCCESS:
                player.sendMessage(ChatColor.GREEN + "아이템이 강력한 힘을 흡수하여 더욱 강해졌습니다!");
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                break;
            case FAILURE:
                player.sendMessage(ChatColor.RED + "강화가 실패했지만, 다행히 아이템은 손상되지 않았습니다.");
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.5F);
                break;
            case LEVEL_DOWN:
                player.sendMessage(ChatColor.YELLOW + "강화 실패로 아이템의 에너지가 일부 소실되었습니다.");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 0.5F);
                break;
            case DESTROYED:
                player.sendMessage(ChatColor.DARK_RED + "아이템이 강화의 부담을 견디지 못하고 소멸의 길을 선택했습니다.");
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.5F);
                break;
        }
        event.getCursor().setAmount(0);
    }

    private boolean isValidClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return false;
        if (event.getCursor() == null || event.getCurrentItem() == null)
            return false;
        return event.getClick() == ClickType.LEFT && event.getClickedInventory().getType() == InventoryType.PLAYER
                && event.getAction() == InventoryAction.SWAP_WITH_CURSOR && Scroll.isValidScroll(event.getCursor())
                && EnchantableItem.isValidEnchantableItem(event.getCurrentItem());
    }

    private boolean doEnchant(Scroll scroll, EnchantableItem item) {
        if (!item.decreaseUpgradeCount(scroll.getNeedUpgradeLevel())) {
            return false;
        }
        return true;
    }
}
