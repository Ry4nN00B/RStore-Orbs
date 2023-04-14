package me.ry4nn00b.orbs.SQLite;

import me.ry4nn00b.orbs.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnect {

    public static Connection con = null;

    public static String prefix = "[RStore-Orbs] ";

    public static void open() {
        if(!Main.plugin.getDataFolder().exists()) {
            Main.plugin.getDataFolder().mkdir();
        }
        File file = new File(Main.plugin.getDataFolder(), "Orbs.db");

        String url = "jdbc:sqlite:" + file;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);
            Constructs.CreateTable();
            Bukkit.getConsoleSender().sendMessage(prefix + "A conexão com o SQLite foi um sucesso!");
        } catch (Exception e) {
            Main.plugin.getPluginLoader().disablePlugin(JavaPlugin.getPlugin(Main.class));
            Bukkit.getConsoleSender().sendMessage(prefix + "Não foi efetuar a conexão com o SQLite!");
        }
    }

    public static void close() {
        if(con != null) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(prefix + "A conexão com o SQLite foi fechada!");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(prefix + "Não foi possível encerrar a conexão com o SQLite!");
                e.printStackTrace();
            }
        }
    }

}
