package com.untistore.utils.listeners;

import com.untistore.utils.managers.ConfigManager;
import com.untistore.utils.managers.VanishManager;
import com.untistore.utils.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {
    private final ConfigManager config = ConfigManager.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        handleJoinMessages(e);

        VanishManager.hideAllPlayersInVanish(e.getPlayer());
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String finalMessage = StringUtils.filterAll(e.getMessage());

        handleChatMessages(e, finalMessage);

        if (e.getMessage().contains("[item]")) e.setMessage(e.getMessage().replace("[item]", "§7[§6" + StringUtils.filterAll(e.getPlayer().getInventory().getItemInMainHand().getType().name() + "§7]" + "§r")));
    }



    private void handleChatMessages(AsyncPlayerChatEvent e, String message) {
        if (!config.contains("chat") || !config.get("chat.enabled").equals(true)) {
            e.setMessage(message);
            return;
        }

        e.setMessage(config.get("chat.prefix").toString() + message);
    }

    public void handleJoinMessages(PlayerJoinEvent e) {
        if (!config.contains("join")) return;
        if (config.get("join.enabled").equals(false)) return;

        if (e.getPlayer().hasPlayedBefore()) {
            String message = config.get("join.message").toString();
            String finalMessage = StringUtils.filterPlaceholders(message, e.getPlayer());

            e.getPlayer().sendMessage(finalMessage);
        }
        else {
            if (config.get("join.firstJoin.enabled").equals(true)) {
                String message = config.get("join.firstJoin.message").toString();
                String finalMessage = StringUtils.filterPlaceholders(message, e.getPlayer());

                e.getPlayer().sendMessage(finalMessage);
            }
        }
    }
}
