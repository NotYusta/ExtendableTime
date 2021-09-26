package id.raznar.extendedtime.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {
    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static void sendMessage(CommandSender sender, String text) {
        sender.sendMessage(colorize("&8[&dExtendedTime&8] &f" + text));
    }
    public static void sendMessage(CommandSender sender, Lang lang) {
        sender.sendMessage(colorize("&8[&dExtendedTime&8] &f" + lang));
    }

    public static void log(String text) {
        Bukkit.getLogger().info(colorize("&8[&dExtendedTime&8] &f" + text));
    }
}
