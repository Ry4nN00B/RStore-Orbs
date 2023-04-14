package me.ry4nn00b.orbs.Managers;

import me.ry4nn00b.orbs.Commands.*;
import org.bukkit.Bukkit;

public class CommandManager {

    public static void getCommands(){

        Bukkit.getPluginCommand("giveorbs").setExecutor(new GiveOrbs());
        Bukkit.getPluginCommand("orbs").setExecutor(new Orbs());
        Bukkit.getPluginCommand("payorbs").setExecutor(new PayOrbs());
        Bukkit.getPluginCommand("removeorbs").setExecutor(new RemoveOrbs());
        Bukkit.getPluginCommand("orbsmsg").setExecutor(new Orbsmsg());
        Bukkit.getPluginCommand("boostorbs").setExecutor(new BoostOrbs());
        Bukkit.getPluginCommand("boostorbsall").setExecutor(new BoostOrbs());
        //Bukkit.getPluginCommand("getorbs").setExecutor(new GetOrbs());

    }

}
