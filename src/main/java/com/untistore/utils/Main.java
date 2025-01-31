package com.untistore.utils;

import com.untistore.utils.commands.*;
import com.untistore.utils.listeners.PlayerListeners;
import com.untistore.utils.managers.ConfigManager;
import com.untistore.utils.managers.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigManager.getInstance().createConfig();
        getLogger().info("UntiUtils v" + getDescription().getVersion() + " plugin enabled!");


        getCommand("gamemode").setExecutor(new GamemodeCommands());
        getCommand("tp").setExecutor(new TpCommands());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("lobby").setExecutor(new LobbyCommands());
        getCommand("lockdown").setExecutor(new LockdownCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);

        VanishManager.sendActionBarToVanishedPlayersEverySecond();
    }

    @Override
    public void onDisable() {
        getLogger().info("UntiUtils v" + getDescription().getVersion() + " plugin disabled!");
    }
}
