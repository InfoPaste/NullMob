package me.InfoPaste.NullMob.util;

import org.bukkit.ChatColor;

public class TextUtil {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
