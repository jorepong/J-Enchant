package com.github.jorepong.jenchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            player.sendMessage("good!");
            player.sendMessage(J_Enchant.getInstance().toString());
            Scroll scroll = new Scroll(Enchantment.DURABILITY, 80, 10, 8, 2);
            player.getInventory().addItem(scroll.toItemStack());

            player.sendTitle("✨⚔️✨", "강화 주문서를 받았습니다!", 10, 70, 20);
        }

        return false;
    }
}
