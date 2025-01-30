package com.untistore.utils.commands;

import com.untistore.utils.managers.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class TpCommands implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target = player.getServer().getPlayer(args[0]);

        if (args.length == 1) {
            if (isPlayerOnline(target)) return false;
            TeleportManager.teleportPlayer(player, target);
        }

        if (args.length == 2) {
            Player target1 = player.getServer().getPlayer(args[0]);
            Player target2 = player.getServer().getPlayer(args[1]);

            if (isPlayerOnline(target1) || isPlayerOnline(target2)) return false;

            TeleportManager.teleportPlayer(target1, target2);
        }

        if (args.length == 3) {
            try {
                double x = (Double.parseDouble(args[0]));
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                TeleportManager.teleportPlayer(player, (int) x, (int) y, (int) z);
            } catch (NumberFormatException e) {
                player.sendMessage("§cInvalid coordinates! example: /tp <x> <y> <z>");
            }
        }

        if (args.length == 4) {
            Player target1 = player.getServer().getPlayer(args[0]);
            try {
                double x = Double.parseDouble(args[1]);
                double y = Double.parseDouble(args[2]);
                double z = Double.parseDouble(args[3]);

                if (isPlayerOnline(target1)) return false;

                TeleportManager.teleportPlayer(player, target1, (int) x, (int) y, (int) z);
            } catch (NumberFormatException e) {
                player.sendMessage("§cInvalid coordinates!");
            }
        }

        player.sendMessage("§cInvalid arguments! example: /tp <player> or /tp <player> <player> or /tp <x> <y> <z> or /tp <player> <x> <y> <z>");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }


    public boolean isPlayerOnline(Player player) {
        boolean isPlayerNull = player == null;
        if (isPlayerNull) {
            return true;
        }

        player.sendMessage("§cPlayer not found!");
        return false;
    }
}
