package me.InfoPaste.NullMob.command;

import me.InfoPaste.NullMob.api.MobAI;
import me.InfoPaste.NullMob.core.Config;
import me.InfoPaste.NullMob.util.TextUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MainCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("nullmob")) {
            if (args.length == 0) {
                if (sender.hasPermission("nullmob.commands")) {
                    helpMessage(sender);
                    return true;
                } else {
                    sender.sendMessage(TextUtil.colorize(Config.config.getString("PermissionDenied")));
                    return true;
                }
            } else if (args.length > 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (player.hasPermission("nullmob.commands")) {
                        if (args[0].equalsIgnoreCase("removeai")) {


                            int r = 20;

                            if (args.length >= 2) {
                                r = Integer.valueOf(args[1].replaceAll("[^\\d.]", ""));
                            }

                            int counter = 0;
                            for (Entity entity : player.getNearbyEntities(r, r, r)) {
                                MobAI.removeAI(entity);
                                if (!(entity instanceof Player)) {
                                    counter++;
                                }
                            }

                            player.sendMessage(TextUtil.colorize("&cRemoved &7" + counter + "&c AI's from mobs, within &7" + r + "&c blocks of you."));
                            return true;
                        } else if (args[0].equalsIgnoreCase("addai")) {

                            int r = 20;

                            if (args.length == 2) {
                                r = Integer.valueOf(args[1].replaceAll("[^\\d.]", ""));
                            }

                            int counter = 0;
                            for (Entity entity : player.getNearbyEntities(r, r, r)) {
                                MobAI.addAI(entity);
                                if (!(entity instanceof Player)) {
                                    counter++;
                                }
                            }

                            player.sendMessage(TextUtil.colorize("&cGave back &7" + counter + "&c AI's to mobs, within &7" + r + "&c blocks of you."));
                            return true;

                        } else {
                            helpMessage(sender);
                            return true;
                        }

                    } else {
                        player.sendMessage(TextUtil.colorize(Config.config.getString("PermissionDenied")));
                        return true;
                    }

                }
            }
            return true;
        }

        return false;
    }

    private void helpMessage(CommandSender sender) {
        sender.sendMessage(TextUtil.colorize("&7+ &8- - - &fNull Mob &8- - - &7+"));
        sender.sendMessage(TextUtil.colorize("&f/nm removeai <radius>&8 - &7removes the AI of mobs within 20 blocks"));
        sender.sendMessage(TextUtil.colorize("&f/nm addai <radius>&8 - &7gives back the AI of mobs within 20 blocks"));
    }

}
