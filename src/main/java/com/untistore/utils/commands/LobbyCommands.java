package com.untistore.utils.commands;

import com.untistore.utils.managers.ConfigManager;
import com.untistore.utils.managers.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class LobbyCommands implements CommandExecutor, TabCompleter {
    private final ConfigManager config = ConfigManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int x = config.get("lobby.x") != null ? (int) config.get("lobby.x") : 0;
        int y = config.get("lobby.y") != null ? (int) config.get("lobby.y") : 0;
        int z = config.get("lobby.z") != null ? (int) config.get("lobby.z") : 0;

        if (config.get("lobby.enabled").equals(false) || config.get("lobby.enabled") == null) return true;
        if (x == -1 || y == -1 || z == -1) {
            sender.sendMessage("§cLobby location is not set!");
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
