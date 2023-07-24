package me.ry4nn00b.orbs.Events;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Orbs implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){

        //Messages______________________________________________________________________________________________________
        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String no_orbs = FileManager.config.getString("Mensagens.NoOrbs").replace("&", "§");
        String redeemOrbs = FileManager.config.getString("Mensagens.RedeemOrbs").replace("&", "§");
        String tpLimit = FileManager.config.getString("Mensagens.TPLimit").replace("&", "§");

        //Variables_____________________________________________________________________________________________________
        Player p = (Player) e.getWhoClicked();

        //NBTManager____________________________________________________________________________________________________
        NBTManager manager = NBTManager.getInstance();
        NBTCompound ForgeData = NBTManager.getInstance().readForgeData(p);
        NBTCompound playerData = (NBTCompound) ForgeData.get("PlayerPersisted");

        int PlayerTP = playerData.getInt("jrmcTpint");

        //TP Variables__________________________________________________________________________________________________
        long tp_value = FileManager.config.getInt("Orbs.TP");
        long orbs_boost = FileManager.config.getInt("Orbs.Boost");
        int playerBoost = Constructs.getPlayerBoost(p);
        int finalBoost = (int) (orbs_boost * playerBoost);
        int playerOrbs = Constructs.getPlayerOrbs(p);
        long totalTP = (tp_value * finalBoost);
        long orbsTP = playerOrbs * totalTP;

        //1 Billion_____________________________________________________________________________________________________
        long maxTP1B = 1000000000;
        long different1B = maxTP1B - PlayerTP;
        long finalTP1B = PlayerTP + different1B;
        long delOrbs1B = orbsTP - different1B;
        long finalOrbs1B = delOrbs1B/totalTP;
        long teste1B = PlayerTP + orbsTP;
        //2 Billion_____________________________________________________________________________________________________
        long maxTP2B = 2000000000;
        long different2B = maxTP2B - PlayerTP;
        long finalTP2B = PlayerTP + different2B;
        long delOrbs2B = orbsTP - different2B;
        long finalOrbs2B = delOrbs2B/totalTP;
        long teste2B = PlayerTP + orbsTP;

        //Event_________________________________________________________________________________________________________
        if (e.getSlot() < 0)
            return;
        if (!e.getCurrentItem().hasItemMeta())
            return;

        if(e.getInventory().getTitle().equalsIgnoreCase("§b§l§oOrbs")){
            e.setCancelled(true);

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aResgatar")){
                e.setCancelled(true);
                if(playerOrbs <= 0){

                    p.sendMessage(prefix + no_orbs);
                    return;

                }

                if(FileManager.config.getString("Orbs.TPLimit").equals("2B")){
                    if(PlayerTP < 2000000000){
                        if(orbsTP <= different2B){

                            playerData.put("jrmcTpint", teste2B);
                            ForgeData.put("PlayerPersisted", playerData);
                            manager.writeForgeData(p, ForgeData);
                            Constructs.setPlayerOrbs(p, 0);

                            p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(orbsTP)));
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                            p.closeInventory();
                            return;

                        }else if (orbsTP > different2B){

                            playerData.put("jrmcTpint", finalTP2B);
                            ForgeData.put("PlayerPersisted", playerData);
                            manager.writeForgeData(p, ForgeData);
                            Constructs.setPlayerOrbs(p, finalOrbs2B);

                            p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(different2B)));
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                            p.closeInventory();
                            return;

                        }

                    }else p.sendMessage(prefix + tpLimit);
                }

                if(FileManager.config.getString("Orbs.TPLimit").equals("1B")){
                    if(PlayerTP < 1000000000){
                        if(orbsTP <= different1B){

                            playerData.put("jrmcTpint", teste1B);
                            ForgeData.put("PlayerPersisted", playerData);
                            manager.writeForgeData(p, ForgeData);
                            Constructs.setPlayerOrbs(p, 0);

                            p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(orbsTP)));
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                            p.closeInventory();
                            return;

                        }else if (orbsTP > different1B){

                            playerData.put("jrmcTpint", finalTP1B);
                            ForgeData.put("PlayerPersisted", playerData);
                            manager.writeForgeData(p, ForgeData);
                            Constructs.setPlayerOrbs(p, finalOrbs1B);

                            p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(different1B)));
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
                            p.closeInventory();
                            return;

                        }

                    }else p.sendMessage(prefix + tpLimit);
                }

            }

        }

    }

}
