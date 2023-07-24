package me.ry4nn00b.orbs.Panels;

import me.ry4nn00b.orbs.Managers.FileManager;
import me.ry4nn00b.orbs.SQLite.Constructs;
import me.ry4nn00b.orbs.Utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Orbs {

    public static Inventory orbsMenu(Player p){

        //Calculations__________________________________________________________________________________________________
        int tpValue = FileManager.config.getInt("Orbs.TP");
        int orbsBoost = FileManager.config.getInt("Orbs.Boost");
        int playerBoost = Constructs.getPlayerBoost(p);
        int totalBoost = orbsBoost*playerBoost;
        long TPTotal = ((long) tpValue * totalBoost);

        long playerOrbs = Constructs.getPlayerOrbs(p);
        long TPFinal = playerOrbs*TPTotal;

        String TPLimit = FileManager.config.getString("Orbs.TPLimit");

        //Lore's________________________________________________________________________________________________________
        List<String> orbsLore = new ArrayList<>();
        orbsLore.add("§bInformações:");
        orbsLore.add(" ");
        orbsLore.add("§f§l・§aOrbs: §f" + playerOrbs);
        orbsLore.add("§f§l・§aTP: §f" + TPFinal);
        orbsLore.add("§f§l・§aBoost: §f" + totalBoost + "x");

        List<String> redeem = new ArrayList<>();
        redeem.add("§7Clique aqui para resgatar seus TP's.");
        redeem.add(" ");
        if(TPLimit.equals("1B")){
            redeem.add("§7Limitamos a quantia de TP's para resgatar em 1 bilhão.");
        }else if (TPLimit.equals("2B")){
            redeem.add("§7Limitamos a quantia de TP's para resgatar em 2 bilhões.");
        }

        //Inventory_____________________________________________________________________________________________________
        Inventory orbs = Bukkit.createInventory(null, 9*3, "§b§l§oOrbs");

        //WhiteGlass____________________________________________________________________________________________________
        orbs.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(20, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(22, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(24, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());
        orbs.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName("    ").build());

        //Item__________________________________________________________________________________________________________
        orbs.setItem(12, new ItemBuilder(Material.BOOK).setName("§6§lOrbs").setLore(orbsLore).build());
        orbs.setItem(14, new ItemBuilder(Material.PAPER).setName("§aResgatar").setLore(redeem).build());

        return orbs;

    }

}
