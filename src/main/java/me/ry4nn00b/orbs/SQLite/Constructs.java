package me.ry4nn00b.orbs.SQLite;

import me.ry4nn00b.orbs.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Constructs {

    public static String prefix = "[RStore-Orbs] ";
    public static Main plugin;

    public static void CreateTable(){

        PreparedStatement stm;

        try{

            stm = SQLiteConnect.con.prepareStatement("CREATE TABLE IF NOT EXISTS `orbsDatabase`(Player_UUID VARCHAR(36), Player_Name VARCHAR(24), Orbs TEXT, Boost TEXT, Message TEXT);");
            stm.executeUpdate();
            Bukkit.getConsoleSender().sendMessage(prefix + "A tabela do SQLite foi criada com sucesso!");

            stm.close();

        }catch (SQLException e){

            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(prefix + "Não foi possível criar a tabela no SQLite, desligando o plugin!");
            plugin.getPluginLoader().disablePlugin(plugin);

        }

    }

    //Get or Add Player-------------------------------------------------------------------------------------------------

    public static boolean hasPlayerTable(Player player){

        try {

            String hasPlayer = "SELECT `Player_UUID` FROM `orbsDatabase` WHERE `Player_UUID` = '" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(hasPlayer);
            ResultSet resultSet = statement.executeQuery();

            boolean result = resultSet.next();

            return result;

        }catch (Exception e){

            e.printStackTrace();

        }

        return false;

    }

    public static void addPlayerTable(Player player){

        try {

            String addPlayer = "INSERT INTO `orbsDatabase`(`Player_UUID`, `Player_Name`, `Orbs`, `Boost`, `Message`) VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "', '0', '1', 'true')";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(addPlayer);
            statement.executeUpdate();

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    //Orbs______________________________________________________________________________________________________________

    public static int getPlayerOrbs(Player player){

        try {

            String getPlayerOrbs = "SELECT `Orbs` FROM `orbsDatabase` WHERE `Player_UUID` = '" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(getPlayerOrbs);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.getInt("Orbs");

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public static void setPlayerOrbs(Player player, long orbs){

        try {

            String setPlayerOrbs = "UPDATE `orbsDatabase` SET `Orbs`='" + orbs + "' WHERE `Player_UUID`='" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(setPlayerOrbs);
            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Mensagens
    public static String PlayerMessage(Player player){

        try {

            String messages = "SELECT `Message` FROM `orbsDatabase` WHERE `Player_UUID` = '" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(messages);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.getString("Message");

        }catch (Exception e){

            e.printStackTrace();

        }

        return null;
    }

    public static void setPlayerMessage(Player player, String boolean_){

        try {

            String message = "UPDATE `orbsDatabase` SET `Message`='" + boolean_ + "' WHERE `Player_UUID`='" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(message);
            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Boost_Orbs
    public static int getPlayerBoost(Player player){

        try {

            String getPlayerOrbs = "SELECT `Boost` FROM `orbsDatabase` WHERE `Player_UUID` = '" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(getPlayerOrbs);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.getInt("Boost");

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public static void setPlayerBoost(Player player, String boost){

        try {

            String message = "UPDATE `orbsDatabase` SET `Boost`='" + boost + "' WHERE `Player_UUID`='" + player.getUniqueId().toString() + "'";

            Connection con = SQLiteConnect.con;
            PreparedStatement statement = con.prepareStatement(message);
            statement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
