package com.untistore.utils;

import com.untistore.utils.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderManager {

    private static PlaceholderManager instance;

    public static PlaceholderManager getInstance() {
        if (instance == null) {
            instance = new PlaceholderManager();
        }
        return instance;
    }

    public String replacePlaceholders(Player player, String message) {
        message = message.replace("%player%", player.getName());
        message = message.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
        message = message.replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        return message;
    }
}
