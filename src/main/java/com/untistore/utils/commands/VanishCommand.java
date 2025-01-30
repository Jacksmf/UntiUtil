package com.untistore.utils.commands;

import com.untistore.utils.managers.PermissionManager;
import com.untistore.utils.managers.VanishManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.vanish")) return true;

        if (args.length == 1) {
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cPlayer not found!");
                return true;
            }
            VanishManager.toggleVanish(target);
        }


        VanishManager.toggleVanish(player);
        return true;
    }
}
