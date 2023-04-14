package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Orbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        Player p = (Player) sender;

        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String open_menu = FileManager.config.getString("Mensagens.OpenMenu").replace("&", "§");
        String offPlayer = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");

        if(cmd.getName().equalsIgnoreCase("orbs")){

            p.openInventory(me.ry4nn00b.orbs.Panels.Orbs.orbsMenu(p));
            p.sendMessage(prefix + open_menu);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);

            if(args.length == 1) {
                if (p.hasPermission("rstore.orbs")) {

                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {

                        p.sendMessage(prefix + offPlayer);
                        return false;

                    }

                    p.sendMessage(prefix + "§fJogador(a) §a" + target.getName() + "§f:");
                    p.sendMessage(prefix + "§fOrbs: §a" + Constructs.getPlayerOrbs(target) + "§f.");

                } else return true;
            }

        }

        return false;
    }
}
