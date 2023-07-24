package me.ry4nn00b.orbs.Managers;

import me.ry4nn00b.orbs.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileManager {

    public static FileConfiguration config;
    public static File configFile;

    public static void getFiles(Main plugin){

        configFile = new File(plugin.getDataFolder(), "Config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);

        plugin.saveResource("Config.yml", false);

    }

}
