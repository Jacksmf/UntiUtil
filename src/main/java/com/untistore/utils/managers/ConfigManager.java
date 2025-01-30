package com.untistore.utils.managers;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {
    FileConfiguration config;
    public static ConfigManager instance;

    public ConfigManager(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        this.config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void saveConfig(String file) {
        try {
            this.config.save(new File("plugins/UntiUtils/" + file));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        this.config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new File("plugins/UntiStore/config.yml"));
    }

    public void set(String path, Object value) {
        this.config.set(path, value);
    }

    public Object get(String path) {
        return this.config.get(path);
    }

    public boolean contains(String path) {
        return this.config.contains(path);
    }

    public void remove(String path) {
        this.config.set(path, null);
    }

    synchronized public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager(new File("plugins/UntiUtils/config.yml"));
        }
        return instance;
    }


    public void createConfig() {
        if (!new File("plugins/UntiUtils/config.yml").exists()) {
            set("join.enabled", true);
            set("join.message", "§aWelcome back to the server, §6%player_name%§a!");
            set("join.firstJoin.enabled", true);
            set("join.firstJoin.message", "§aWelcome to the server, §6%player_name%§a! This is your first time joining the server!");

            set("chat.enabled", true);
            set("chat.prefix", "§7§%player%§7 ");

            set("lobby.enabled", false);
            set("lobby.world", "world");
            set("lobby.x", 0);
            set("lobby.y", 100);
            set("lobby.z", 0);

            saveConfig("config.yml");
        }
    }
}
