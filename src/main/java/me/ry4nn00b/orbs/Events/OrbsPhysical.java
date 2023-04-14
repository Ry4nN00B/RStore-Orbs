package me.ry4nn00b.orbs.Events;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class OrbsPhysical implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        //Messages______________________________________________________________________________________________________
        String prefix = FileManager.config.getString("Mensagens.Prefix").replace("&", "§");
        String no_orbs = FileManager.config.getString("Mensagens.NoOrbs").replace("&", "§");
        String redeemOrbs = FileManager.config.getString("Mensagens.RedeemOrbs").replace("&", "§");
        String tpLimit = FileManager.config.getString("Mensagens.TPLimit").replace("&", "§");

        //Variables_____________________________________________________________________________________________________
        Player p = e.getPlayer();

        //NBTManager____________________________________________________________________________________________________
        NBTManager manager = NBTManager.getInstance();
        NBTCompound ForgeData = NBTManager.getInstance().readForgeData(p);
        NBTCompound playerData = (NBTCompound) ForgeData.get("PlayerPersisted");

        int PlayerTP = playerData.getInt("jrmcTpint");

        //Event_________________________________________________________________________________________________________
        for(String orbs : FileManager.orbs.getConfigurationSection("Orbs").getKeys(false)) {

            //Orbs Information__________________________________________________________________________________
            String orbsName = FileManager.orbs.getString("Orbs." + orbs + ".Name").replace("&", "§");
            int itemID = FileManager.orbs.getInt("Orbs." + orbs + ".ItemID");
            int itemData = FileManager.orbs.getInt("Orbs." + orbs + ".ItemData");
            int orbsValue = FileManager.orbs.getInt("Orbs." + orbs + ".Value");
            List<String> lore = FileManager.orbs.getStringList("Orbs." + orbs + ".Lore");
            for (int i = 0; i < lore.size(); i++) {
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            }

            ItemStack orbsItem = new ItemStack(Material.getMaterial(itemID), 1, (short) itemData);
            ItemMeta orbsMeta = orbsItem.getItemMeta();
            orbsMeta.setDisplayName(orbsName);
            orbsMeta.setLore(lore);
            orbsMeta.addEnchant(Enchantment.DURABILITY, 10, true);
            orbsItem.setItemMeta(orbsMeta);

            //TP Variables______________________________________________________________________________________________
            long tp_value = FileManager.config.getInt("Orbs.TP");
            long orbs_boost = FileManager.config.getInt("Orbs.Boost");
            int playerOrbs = Constructs.getPlayerOrbs(p);
            long totalTP = tp_value * orbs_boost;
            long orbsTP = playerOrbs * totalTP;

            //1 Billion_________________________________________________________________________________________________
            long maxTP1B = 1000000000;
            long different1B = maxTP1B - PlayerTP;
            long finalTP1B = PlayerTP + different1B;
            long delOrbs1B = orbsTP - different1B;
            long finalOrbs1B = delOrbs1B/totalTP;
            long teste1B = PlayerTP + orbsValue;
            //2 Billion_________________________________________________________________________________________________
            long maxTP2B = 2000000000;
            long different2B = maxTP2B - PlayerTP;
            long finalTP2B = PlayerTP + different2B;
            long delOrbs2B = orbsTP - different2B;
            long finalOrbs2B = delOrbs2B/totalTP;
            long teste2B = PlayerTP + orbsValue;

            //Event_____________________________________________________________________________________________________
            if(e.getItem() == null || e.getItem().getType().equals(Material.AIR)) return;
            if(!e.getItem().getItemMeta().hasDisplayName()) return;

            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(orbsName)){
                    if(FileManager.config.getString("Orbs.TPLimit").equals("2B")){
                        if(PlayerTP < maxTP2B){
                            if(orbsValue <= different2B){

                                playerData.put("jrmcTpint", teste2B);
                                ForgeData.put("PlayerPersisted", playerData);
                                manager.writeForgeData(p, ForgeData);
                                p.getInventory().removeItem(orbsItem);

                                p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(orbsValue)));
                                return;

                            }else if(orbsValue > different2B){

                                p.sendMessage(prefix + tpLimit);
                                return;

                            }

                        }else p.sendMessage(prefix + tpLimit);
                    }
                }
            }

            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(orbsName)){

                    if(FileManager.config.getString("Orbs.TPLimit").equals("2B")){

                        long tpHandTotal = p.getItemInHand().getAmount()*orbsValue;
                        long tpmaisPlayer = PlayerTP + tpHandTotal;

                        if(PlayerTP == maxTP2B) p.sendMessage(prefix + tpLimit);

                        if(PlayerTP < maxTP2B){
                            if(tpHandTotal <= different2B){

                                playerData.put("jrmcTpint", tpmaisPlayer);
                                ForgeData.put("PlayerPersisted", playerData);
                                manager.writeForgeData(p, ForgeData);

                                p.sendMessage(prefix + redeemOrbs.replace("{tp}", String.valueOf(tpHandTotal)));
                                p.getInventory().remove(p.getItemInHand());
                                return;

                            }else if(tpHandTotal > different2B){

                                playerData.put("jrmcTpint", maxTP2B);
                                ForgeData.put("PlayerPersisted", playerData);
                                manager.writeForgeData(p, ForgeData);

                                p.sendMessage(prefix + tpLimit);
                                p.getInventory().removeItem(p.getItemInHand());
                                return;

                            }
                        }

                    }
                }
            }

        }

    }

}
