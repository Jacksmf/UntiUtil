package com.untistore.utils.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeManager {


    public static void setGamemode(Player player, String gamemode) {
        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.gamemode.*")) return;

        handleGamemodeOptions(player, gamemode);
    }

    ;

    public static void setGamemode(Player player, String gamemode, Player target) {
        if (!PermissionManager.doesPlayerHasPermission(player, "untiutils.gamemode.others")) return;

        handleGamemodeOptions(target, gamemode);
    }


    public static void handleGamemodeOptions(Player player, String gamemode) {
        switch (gamemode.toLowerCase()) {
            case "survival", "s" -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§aYour gamemode has been set to §6Survival");
            }
            case "creative", "c" -> {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("§aYour gamemode has been set to §6Creative");
            }
            case "adventure", "a" -> {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("§aYour gamemode has been set to §6Adventure");
            }
            case "spectator", "sp" -> {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("§aYour gamemode has been set to §6Spectator");
            }
            default -> {
                player.sendMessage("§cInvalid gamemode! Please use one of the following: §6Survival, Creative, Adventure, Spectator");
            }
        }
    }
}
