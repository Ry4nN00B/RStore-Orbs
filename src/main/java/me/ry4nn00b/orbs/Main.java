package me.ry4nn00b.orbs;

import me.ry4nn00b.orbs.Managers.CommandManager;
import me.ry4nn00b.orbs.Managers.EventManager;
import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.SQLiteConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static BukkitScheduler scheduler;

    @Override
    public void onEnable() {

        if(!getServer().getPluginManager().isPluginEnabled("PowerNBT")){
            Bukkit.getConsoleSender().sendMessage("§b§l[RStore-Orbs] §fNão foi encontrado a dependência do PowerNBT, desativando plugin.");
            getPluginLoader().disablePlugin(plugin);
        }

        //Initialization________________________________________________________________________________________________
        Bukkit.getConsoleSender().sendMessage("§b§l======================");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§f§lNome: §bRStore-Orbs");
        Bukkit.getConsoleSender().sendMessage("§f§lCriador: §bRy4nN00B");
        Bukkit.getConsoleSender().sendMessage("§f§lVersão: §b1.4");
        Bukkit.getConsoleSender().sendMessage("§f§lEstado: §aIniciando");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§b§l======================");

        plugin = this;
        SQLiteConnect.open();
        scheduler = Bukkit.getServer().getScheduler();

        //Managers______________________________________________________________________________________________________
        CommandManager.getCommands();
        EventManager.getEvents(plugin);
        FileManager.getFiles(plugin);

    }

    @Override
    public void onDisable() {

        //Closure_______________________________________________________________________________________________________
        Bukkit.getConsoleSender().sendMessage("§b§l======================");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§f§lNome: §bRStore-Orbs");
        Bukkit.getConsoleSender().sendMessage("§f§lCriador: §bRy4nN00B");
        Bukkit.getConsoleSender().sendMessage("§f§lVersão: §b1.4");
        Bukkit.getConsoleSender().sendMessage("§f§lEstado: §cDesligando");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§b§l======================");

        SQLiteConnect.close();

    }

}
