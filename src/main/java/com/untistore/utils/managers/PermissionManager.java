package com.untistore.utils.managers;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class PermissionManager {
    private static Permission permission;


    public static boolean doesPlayerHasPermission(Player player, String permission) {
        if (!player.hasPermission(permission)) {
            player.sendMessage("§cYou don't have permission to use this command!");
            return false;
        }

        return true;
    }
}
