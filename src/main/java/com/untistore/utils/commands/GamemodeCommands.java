package com.untistore.utils.commands;

import com.untistore.utils.managers.GamemodeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class GamemodeCommands implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String option;
        Player player = (Player) sender;

        if (label.length() == 3 || label.length() == 4) {
            option = label.substring(2);
            GamemodeManager.setGamemode(player, option);
        }


        if (args.length == 1) {
            option = args[0];
            Bukkit.getLogger().info("Option: " + option);
            GamemodeManager.setGamemode(player, option);
        }

        if (args.length == 2) {
            option = args[0];
            Player target = player.getServer().getPlayer(args[1]);
            GamemodeManager.setGamemode(player, option, target);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.equals("gamemode")) {
            if (args.length == 1) {
                return List.of("survival", "creative", "adventure", "spectator");
            }
        }
        return null;
    }
}
