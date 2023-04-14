package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveOrbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Messages______________________________________________________________________________________________________
        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String off_msg = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");
        String give_orbs_target = FileManager.config.getString("Mensagens.GiveOrbsTarget").replace("&", "§");
        String give_orbs_sender = FileManager.config.getString("Mensagens.GiveOrbsSender").replace("&", "§");
        String no_permission = FileManager.config.getString("Mensagens.NoPermission").replace("&", "§");
        String orbs_Limit = FileManager.config.getString("Mensagens.OrbsLimit").replace("&", "§");

        if(cmd.getName().equalsIgnoreCase("giveorbs")){
            if(sender.hasPermission("rstore.orbs")){
                if(args.length == 0){

                    sender.sendMessage(prefix + "§fUse: /giveorbs <quantia> <player>");
                    return true;

                }

                if(args.length == 1){

                    int args_amount = Integer.parseInt(args[0]);
                    sender.sendMessage(prefix + "§fUse: /giveorbs <quantia> <player>");

                }

                if(args.length == 2){

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target == null){

                        sender.sendMessage(prefix + off_msg);
                        return false;

                    }

                    long playerOrbs = Constructs.getPlayerOrbs(target);
                    long orbsLimit = FileManager.config.getInt("Orbs.OrbsLimit");

                    long orbsTotal = playerOrbs + Integer.parseInt(args[0]);

                    if(!(playerOrbs == orbsLimit)){

                        Constructs.setPlayerOrbs(target, orbsTotal);
                        if(Constructs.PlayerMessage(target).equals("true")){
                            target.sendMessage(prefix + give_orbs_target.replace("{orbs}", args[0]));
                        }

                        sender.sendMessage(prefix + give_orbs_sender.replace("{orbs}", args[0]).replace("{target}", target.getName()));

                        if(playerOrbs > orbsLimit){
                            Constructs.setPlayerOrbs(target, orbsLimit);
                            target.sendMessage(prefix + orbs_Limit);
                        }

                    }else sender.sendMessage(prefix + orbs_Limit);

                }

            }else sender.sendMessage(prefix + no_permission);
        }

        return false;
    }
}
