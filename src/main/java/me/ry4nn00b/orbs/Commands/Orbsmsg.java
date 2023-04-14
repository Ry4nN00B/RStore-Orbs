package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Orbsmsg implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");

        if(cmd.getName().equalsIgnoreCase("orbsmsg")){

            if(Constructs.PlayerMessage(p).equals("true")){

                Constructs.setPlayerMessage(p, "false");
                p.sendMessage(prefix + "Você desabilitou a mensagem do recebimento de orbs.");

            }else if(Constructs.PlayerMessage(p).equals("false")){

                Constructs.setPlayerMessage(p, "true");
                p.sendMessage(prefix + "Você habilitou a mensagem do recebimento de orbs.");

            }

        }

        return false;
    }
}
