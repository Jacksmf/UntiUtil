package com.untistore.utils.managers;

import com.untistore.utils.utils.ActionbarUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {
    public static List<UUID> vanishedPlayers = new ArrayList<>();

    /**
     * If the player isn't vanished, it will put them in vanish
     *
     * @param player The player that will be vanished
     */
    public static void toggleVanish(Player player) {
        if (vanishedPlayers.contains(player.getUniqueId())) {
            vanishedPlayers.remove(player.getUniqueId());
            player.sendMessage("§aYou are no longer vanished!");
            ActionbarUtils.sendActionbarMessage(player, "§aYou are no longer vanished!");
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(player);
            }
        } else {
            vanishedPlayers.add(player.getUniqueId());
            player.sendMessage("§aYou are now vanished!");
            ActionbarUtils.sendActionbarMessage(player, "§aYou are now vanished!");
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                onlinePlayer.hidePlayer(player);
            }
        }
    }

    /**
     * Hides all vanished players from the player (if they are online)
     * This is used when a player joins the server
     *
     * @param player The player that joined the server
     */
    public static void hideAllPlayersInVanish(Player player) {
        for (UUID uuid : vanishedPlayers) {
            if (!player.isOnline()) return;

            Player vanishedPlayer = player.getServer().getPlayer(uuid);
            player.hidePlayer(vanishedPlayer);
        }
    }

    /**
     * Sends an action bar message to all vanished players every second (Reminding them that they are vanished)
     */
    public static void sendActionBarToVanishedPlayersEverySecond() {
        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("UntiUtils"), () -> {
            for (UUID uuid : vanishedPlayers) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null && player.isOnline()) {
                    ActionbarUtils.sendActionbarMessage(player, "§aYou are vanished!");
                }
            }
        }, 0L, 20L);
    }
}
