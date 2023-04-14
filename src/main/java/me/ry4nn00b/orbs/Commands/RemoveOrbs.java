package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveOrbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String off_player = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");
        String no_orbs = FileManager.config.getString("Mensagens.NoOrbs").replace("&", "§");
        String remove_orbs_target = FileManager.config.getString("Mensagens.RemoveOrbsTarget");
        String remove_orbs_sender = FileManager.config.getString("Mensagens.RemoveOrbsSender");
        String no_permission = FileManager.config.getString("Mensagens.NoPermission").replace("&", "§");

        if(cmd.getName().equalsIgnoreCase("removeorbs")){
            if(sender.hasPermission("rstore.orbs")){
                if(args.length == 0){

                    sender.sendMessage(prefix + "§fUse: /removeorbs <quantia> <player>");
                    return true;

                }

                if(args.length == 1){

                    int args_amount = Integer.parseInt(args[0]);
                    sender.sendMessage(prefix + "§fUse: /removeorbs <quantia> <player>");

                }

                if(args.length == 2){

                    Player target = Bukkit.getPlayer(args[1]);

                    if(target == null){

                        sender.sendMessage(prefix + off_player);
                        return false;

                    }

                    String args_amount = args[0];
                    long player_amount = Constructs.getPlayerOrbs(target);

                    if(Integer.parseInt(args_amount) > player_amount || Integer.parseInt(args_amount) == 0){

                        sender.sendMessage(prefix + no_orbs);
                        return true;

                    }

                    if(Integer.parseInt(args_amount) <= player_amount){

                        long total_amount = player_amount - Integer.parseInt(args_amount);
                        Constructs.setPlayerOrbs(target, total_amount);

                        target.sendMessage(prefix + remove_orbs_target
                                .replace("&", "§")
                                .replace("{orbs}", args_amount)
                                .replace("{target}", target.getName()));
                        sender.sendMessage(prefix + remove_orbs_sender
                                .replace("&", "§")
                                .replace("{orbs}", args_amount)
                                .replace("{target}", target.getName()));

                        return true;

                    }

                }

            }else {
                sender.sendMessage(prefix + no_permission);
            }
        }

        return false;
    }
}
