package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Main;
import me.ry4nn00b.orbs.Managers.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Variables_____________________________________________________________________________________________________
        Main plugin = Main.plugin;
        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");


        //Command_______________________________________________________________________________________________________
        if(cmd.getName().equalsIgnoreCase("orbsreload")){
            if(sender.hasPermission("rstore.reload")){

                YamlConfiguration.loadConfiguration(FileManager.configFile);
                try {
                    FileManager.config.load(FileManager.configFile);
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                plugin.reloadConfig();

                sender.sendMessage(prefix + "Config recarregada com sucesso!");

            }else sender.sendMessage(prefix + "Você não possui permissão!");
        }

        return false;
    }

}
