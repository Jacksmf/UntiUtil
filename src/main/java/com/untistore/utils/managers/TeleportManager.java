package com.untistore.utils.managers;

import org.bukkit.entity.Player;

public class TeleportManager {


    public static void teleportPlayer(Player player, Player target) {
        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.tp")) return;

        player.teleport(target);
        player.sendMessage("§aYou have been teleported to §6" + target.getName());
    }

    public static void teleportPlayer(Player player, int x, int y, int z) {
        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.tp")) return;

        player.teleport(player.getWorld().getHighestBlockAt(x, y).getLocation());

        player.sendMessage("§aYou have been teleported to §6" + x + " " + y + " " + z);
    }

    public static void teleportPlayer(Player player, Player target, int x, int y, int z) {
        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.tp")) return;

        target.teleport(target.getWorld().getHighestBlockAt(x, y).getLocation());


        target.sendMessage("§aYou have been teleported to §6" + target.getName() + " " + x + " " + y + " " + z);
        player.sendMessage("§aYou have teleported §6" + target.getName() + " to §6" + x + " " + y + " " + z);

    }
}
