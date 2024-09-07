package com.github.jorepong.jenchant;

import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class EnchantUtils {

    private static final Map<Enchantment, String> enchantmentNames = new HashMap<>();

    static {
        enchantmentNames.put(Enchantment.ARROW_DAMAGE, "화염");
        enchantmentNames.put(Enchantment.ARROW_FIRE, "발사체 보호");
        enchantmentNames.put(Enchantment.ARROW_INFINITE, "무한");
        enchantmentNames.put(Enchantment.ARROW_KNOCKBACK, "밀어내기");
        enchantmentNames.put(Enchantment.BINDING_CURSE, "귀속 저주");
        enchantmentNames.put(Enchantment.CHANNELING, "집전");
        enchantmentNames.put(Enchantment.DAMAGE_ALL, "날카로움");
        enchantmentNames.put(Enchantment.DAMAGE_ARTHROPODS, "살충");
        enchantmentNames.put(Enchantment.DAMAGE_UNDEAD, "강타");
        enchantmentNames.put(Enchantment.DEPTH_STRIDER, "물갈퀴");
        enchantmentNames.put(Enchantment.DIG_SPEED, "효율");
        enchantmentNames.put(Enchantment.DURABILITY, "내구성");
        enchantmentNames.put(Enchantment.FIRE_ASPECT, "발화");
        enchantmentNames.put(Enchantment.FROST_WALKER, "프로스트 워커");
        enchantmentNames.put(Enchantment.IMPALING, "관통");
        enchantmentNames.put(Enchantment.KNOCKBACK, "밀어내기");
        enchantmentNames.put(Enchantment.LOOT_BONUS_BLOCKS, "행운");
        enchantmentNames.put(Enchantment.LOOT_BONUS_MOBS, "약탈");
        enchantmentNames.put(Enchantment.LOYALTY, "충성");
        enchantmentNames.put(Enchantment.LUCK, "행운");
        enchantmentNames.put(Enchantment.LURE, "미끼");
        enchantmentNames.put(Enchantment.MENDING, "수선");
        enchantmentNames.put(Enchantment.MULTISHOT, "다중 발사");
        enchantmentNames.put(Enchantment.OXYGEN, "호흡");
        enchantmentNames.put(Enchantment.PIERCING, "관통");
        enchantmentNames.put(Enchantment.PROTECTION_ENVIRONMENTAL, "보호");
        enchantmentNames.put(Enchantment.PROTECTION_EXPLOSIONS, "폭발 보호");
        enchantmentNames.put(Enchantment.PROTECTION_FALL, "낙하 보호");
        enchantmentNames.put(Enchantment.PROTECTION_FIRE, "화염 보호");
        enchantmentNames.put(Enchantment.PROTECTION_PROJECTILE, "발사체 보호");
        enchantmentNames.put(Enchantment.QUICK_CHARGE, "빠른 장전");
        enchantmentNames.put(Enchantment.RIPTIDE, "급류");
        enchantmentNames.put(Enchantment.SILK_TOUCH, "섬세한 손길");
        enchantmentNames.put(Enchantment.SOUL_SPEED, "소울 속도");
        enchantmentNames.put(Enchantment.SWEEPING_EDGE, "휩쓸기");
        enchantmentNames.put(Enchantment.THORNS, "가시");
        enchantmentNames.put(Enchantment.VANISHING_CURSE, "소실 저주");
        enchantmentNames.put(Enchantment.WATER_WORKER, "친수성");
    }

    public static String getName(Enchantment enchantment) {
        return enchantmentNames.getOrDefault(enchantment, "알 수 없는 인챈트");
    }

}
