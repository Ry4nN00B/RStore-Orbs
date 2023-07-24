package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Main;
import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;

public class BoostOrbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String no_permission = FileManager.config.getString("Mensagens.NoPermission").replace("&", "§");
        String off_player = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");
        String boost_sender = FileManager.config.getString("Mensagens.BoostSender").replace("&", "§");
        String boost_target = FileManager.config.getString("Mensagens.BoostTarget").replace("&", "§");
        String boostAllOn = FileManager.config.getString("Mensagens.BoostAllOn").replace("&", "§");
        String boostAllOff = FileManager.config.getString("Mensagens.BoostAllOff").replace("&", "§");
        String boostTargetOff = FileManager.config.getString("Mensagens.BoostTargetOff").replace("&", "§");

        if(cmd.getName().equalsIgnoreCase("boostorbs")){
            if(sender.hasPermission("rstore.orbs")){
                if(args.length == 0){

                    sender.sendMessage(prefix + "Use: /boostorbs <boost> <minutos> <player>");
                    return true;

                }

                int boost = Integer.parseInt(args[0]);
                int time = Integer.parseInt(args[1]);
                int finalTime = time*60;
                Player p = Bukkit.getPlayer(args[2]);
                BukkitScheduler scheduler = Main.scheduler;
                Main plugin = Main.plugin;

                if(p == null){

                    sender.sendMessage(prefix + off_player);
                    return false;

                }

                scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {

                    int timer = finalTime;

                    @Override
                    public void run() {

                        timer--;

                        if(timer == 0){

                            Constructs.setPlayerBoost(p, "1");
                            p.sendMessage(prefix + boostTargetOff);

                        }

                    }
                }, 20L, 20L);

                Constructs.setPlayerBoost(p, String.valueOf(boost));
                sender.sendMessage(prefix + boost_sender.replace("{boost}", String.valueOf(boost)).replace("{target}", p.getName()));
                p.sendMessage(prefix + boost_target.replace("{boost}", String.valueOf(boost)));

            }else sender.sendMessage(prefix + no_permission);
        }

        if(cmd.getName().equalsIgnoreCase("boostorbsall")){
            if(sender.hasPermission("rstore.orbs")){
                if(args.length == 0){

                    sender.sendMessage(prefix + "Use: /boostorbsall <boost, reset>");
                    return true;

                }

                if(args.length == 1) {

                    if(args[0].equalsIgnoreCase("reset")){

                        FileManager.config.set("Orbs.Boost", 1);
                        try {
                            FileManager.config.save(FileManager.configFile);
                            Main.plugin.reloadConfig();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bukkit.broadcastMessage(prefix + boostAllOff);
                        return true;

                    }

                    int boost = Integer.parseInt(args[0]);

                    FileManager.config.set("Orbs.Boost", boost);
                    try {
                        FileManager.config.save(FileManager.configFile);
                        Main.plugin.reloadConfig();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Bukkit.broadcastMessage(prefix + boostAllOn.replace("{boost}", args[0]));

                }

            }else sender.sendMessage(prefix + no_permission);
        }

        return false;
    }
}
