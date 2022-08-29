package ml.savdev.spasboysync.commands;

import ml.savdev.spasboysync.SpasboySync;
import ml.savdev.spasboysync.db.Database;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Link extends Command {

    private Database database;

    public Link(Database database) {
        super("Link");
        this.database = database;
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;

            int Code = (int)(Math.random()*9000)+1000;

            try {
                database.newCode(p.getUniqueId().toString(), String.valueOf(Code));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            p.sendMessage(new ComponentBuilder("[SpasboySync] Your unique code is " + String.valueOf(Code) + "!").color(ChatColor.RED).create());
            p.sendMessage(new ComponentBuilder("[SpasboySync] Please DM this to the SpasboyManager Bot!").color(ChatColor.RED).create());
            p.sendMessage(new ComponentBuilder("[SpasboySync] Please note, leaving the game will delete the code and you will need to run this command again!").color(ChatColor.RED).create());
        } else {
            ProxyServer.getInstance().getLogger().warning("[SpasboySync] Only players can use this command!");
            return;
        }

    }

}
