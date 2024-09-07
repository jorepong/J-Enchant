package com.github.jorepong.jenchant;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class EnchantAnimation {
    private List<Player> activePlayers = new ArrayList<>();

    public void showEnchantAnimation(Player player) {
        if (activePlayers.contains(player)) {
            return;
        }
        activePlayers.add(player);
        animateAnimation(player);
    }

    public boolean isActivated(Player player) {
        return activePlayers.contains(player);
    }

    private void animateAnimation(Player player) {
        BukkitRunnable runnable = new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                switch(count) {
                    case 0:
                        player.sendTitle("", "☆ △ □", 0, 20, 0);
                        break;
                    case 1:
                        player.sendTitle("", "★ △ □", 0, 20, 0);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1f);
                        break;
                    case 2:
                        player.sendTitle("", "★ ▲ □", 0, 20, 0);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1f);
                        break;
                    case 3:
                        player.sendTitle("", "★ ▲ ■", 0, 20, 4);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1f);
                        break;
                    case 4:
                        this.cancel();
                }
                count += 1;
            }
        };
        runnable.runTaskTimer(J_Enchant.getInstance(), 0, 10);
        runnable.run();
    }
}
