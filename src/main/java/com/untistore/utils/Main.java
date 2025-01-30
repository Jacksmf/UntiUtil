package com.untistore.utils;

import com.untistore.utils.commands.GamemodeCommands;
import com.untistore.utils.commands.TpCommands;
import com.untistore.utils.listeners.PlayerListeners;
import com.untistore.utils.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("UntiUtils plugin enabled!");


        getCommand("gamemode").setExecutor(new GamemodeCommands());
        getCommand("tp").setExecutor(new TpCommands());

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);

        ConfigManager.getInstance().createConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("UntiUtils plugin disabled!");
    }
}
