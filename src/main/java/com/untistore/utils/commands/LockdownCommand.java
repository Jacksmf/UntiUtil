package com.untistore.utils.commands;

import com.untistore.utils.managers.LockdownManager;
import com.untistore.utils.managers.PermissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockdownCommand implements CommandExecutor {
    private final boolean isServerInLockdown = LockdownManager.isServerInLockdown();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;


        if (!(PermissionManager.doesPlayerHasPermission(player, "untiutils.lockdown"))) return true;

        if (args.length == 0) {
            LockdownManager.toggleLockdown();
            player.sendMessage("§aLockdown has been toggled to §6" + LockdownManager.isServerInLockdown());
        }

        if (args.length == 1) {
            handleOptions(player, args[0]);
        }
        return true;
    }



    private void handleOptions(Player player, String option) {
        if (option.equalsIgnoreCase("on")) {
            if (isServerInLockdown) {
                player.sendMessage("Server is already in Lockdown");
                return;
            }

            player.sendMessage("Switching server to Lockdown");
            LockdownManager.toggleLockdown();
        }

        if (option.equalsIgnoreCase("off")) {
            if (!isServerInLockdown) {
                player.sendMessage("Server is already not in Lockdown");
                return;
            }

            player.sendMessage("Switching Lockdown off");
            LockdownManager.toggleLockdown();
        }
    }
}
