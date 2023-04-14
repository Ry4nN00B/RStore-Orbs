package me.ry4nn00b.orbs.Managers;

import me.ry4nn00b.orbs.Events.Orbs;
import me.ry4nn00b.orbs.Events.OrbsPhysical;
import me.ry4nn00b.orbs.Events.RegisterPlayer;
import me.ry4nn00b.orbs.Main;
import org.bukkit.Bukkit;

public class EventManager {

    public static void getEvents(Main plugin){

        Bukkit.getPluginManager().registerEvents(new RegisterPlayer(), plugin);
        Bukkit.getPluginManager().registerEvents(new Orbs(), plugin);
        //Bukkit.getPluginManager().registerEvents(new OrbsPhysical(), plugin);

    }

}
