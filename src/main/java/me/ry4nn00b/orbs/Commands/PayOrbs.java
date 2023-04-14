package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayOrbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String off_player = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");
        String no_orbs = FileManager.config.getString("Mensagens.NoOrbs").replace("&", "§");
        String pay_orbs_player = FileManager.config.getString("Mensagens.PayOrbsPlayer");
        String pay_orbs_target = FileManager.config.getString("Mensagens.PayOrbsTarget");

        if(cmd.getName().equalsIgnoreCase("payorbs")){
            if(args.length == 0){

                p.sendMessage(prefix + "§fUse: /payorbs <quantia> <player>");
                return true;

            }

            if(args.length == 1){

                int args_amount = Integer.parseInt(args[0]);
                p.sendMessage(prefix + "§fUse: /payorbs <quantia> <player>");
                return true;

            }

            if(args.length == 2){

                Player target = Bukkit.getPlayer(args[1]);

                if(target == null){

                    p.sendMessage(prefix + off_player);
                    return false;

                }

                if(target == p){

                    p.sendMessage(prefix + " Você não pode escolher você mesmo.");
                    return false;

                }

                String args_amount = args[0];
                long player_amount = Constructs.getPlayerOrbs(p);
                long target_amount = Constructs.getPlayerOrbs(target);

                if(Integer.parseInt(args_amount) > player_amount || Integer.parseInt(args_amount) == 0){

                    p.sendMessage(prefix + no_orbs);
                    return true;

                }

                if(Integer.parseInt(args_amount) <= player_amount){

                    long player_total = player_amount - Integer.parseInt(args_amount);
                    long target_total = target_amount + Integer.parseInt(args_amount);

                    Constructs.setPlayerOrbs(p, player_total);
                    Constructs.setPlayerOrbs(target, target_total);

                    p.sendMessage(prefix + pay_orbs_player
                            .replace("&", "§")
                            .replace("{orbs}", args_amount)
                            .replace("{player}", p.getName())
                            .replace("{target}", target.getName()));
                    target.sendMessage(prefix + pay_orbs_target
                            .replace("&", "§")
                            .replace("{orbs}", args_amount)
                            .replace("{player}", p.getName())
                            .replace("{target}", target.getName()));

                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                    target.playSound(target.getLocation(), Sound.LEVEL_UP, 1, 1);
                    return true;

                }

            }

        }

        return false;
    }
}
