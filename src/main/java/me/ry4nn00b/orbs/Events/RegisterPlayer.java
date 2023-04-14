package me.ry4nn00b.orbs.Events;

import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RegisterPlayer implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        if(!Constructs.hasPlayerTable(e.getPlayer())){
            Constructs.addPlayerTable(e.getPlayer());
        }

    }

}
