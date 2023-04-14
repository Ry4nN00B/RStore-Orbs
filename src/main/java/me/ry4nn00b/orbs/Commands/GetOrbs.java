package me.ry4nn00b.orbs.Commands;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GetOrbs implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Messages______________________________________________________________________________________________________
        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String offMsg = FileManager.config.getString("Mensagens.OffPlayer").replace("&", "§");
        String getOrbsTarget = FileManager.config.getString("Mensagens.GiveOrbsTarget").replace("&", "§");
        String getOrbsSender = FileManager.config.getString("Mensagens.GiveOrbsSender").replace("&", "§");
        String noPermission = FileManager.config.getString("Mensagens.NoPermission").replace("&", "§");
        String orbsLimit = FileManager.config.getString("Mensagens.OrbsLimit").replace("&", "§");

        //Command_______________________________________________________________________________________________________
        if(cmd.getName().equalsIgnoreCase("getorbs")){
            if(sender.hasPermission("rstore.orbs")){
                if(args.length == 0){

                    sender.sendMessage(prefix + "Use: /getorbs <orb> <quantia> <player>");
                    return true;

                }

                for(String orbs : FileManager.orbs.getConfigurationSection("Orbs").getKeys(false)){

                    //Variables_________________________________________________________________________________________
                    Player p = Bukkit.getPlayer(args[2]);
                    Integer.parseInt(args[1]);

                    //Orbs Information__________________________________________________________________________________
                    String orbsName = FileManager.orbs.getString("Orbs." + orbs + ".Name").replace("&", "§");
                    int itemID = FileManager.orbs.getInt("Orbs." + orbs + ".ItemID");
                    int itemData = FileManager.orbs.getInt("Orbs." + orbs + ".ItemData");
                    int orbsValue = FileManager.orbs.getInt("Orbs." + orbs + ".Value");
                    int amount = Integer.parseInt(args[1]);
                    List<String> lore = FileManager.orbs.getStringList("Orbs." + orbs + ".Lore");
                    for (int i = 0; i < lore.size(); i++) {
                        lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
                    }

                    ItemStack orbsItem = new ItemStack(Material.getMaterial(itemID), amount, (short) itemData);
                    ItemMeta orbsMeta = orbsItem.getItemMeta();
                    orbsMeta.setDisplayName(orbsName);
                    orbsMeta.setLore(lore);
                    orbsMeta.addEnchant(Enchantment.DURABILITY, 10, true);
                    orbsItem.setItemMeta(orbsMeta);

                    //Command___________________________________________________________________________________________
                    if(args[0].equalsIgnoreCase(orbs)){

                        if(Constructs.PlayerMessage(p).equals("true")){
                            p.sendMessage(prefix + getOrbsTarget.replace("{orbs}", args[1]));
                        }
                        p.getInventory().addItem(orbsItem);

                        sender.sendMessage(prefix + getOrbsSender.replace("{target}", p.getName()).replace("{orbs}", args[1]));

                    }

                }

            }else sender.sendMessage(prefix + noPermission);
        }

        return false;
    }
}
