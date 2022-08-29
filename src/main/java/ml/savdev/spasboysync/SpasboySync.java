package ml.savdev.spasboysync;

import ml.savdev.spasboysync.commands.Link;
import ml.savdev.spasboysync.db.Database;
import ml.savdev.spasboysync.events.LeaveEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.SQLException;

public class SpasboySync extends Plugin {

    private Database database;

    @Override
    public void onEnable() {

        this.database = new Database();
        try {
            this.database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new Link(database));
        getProxy().getPluginManager().registerListener(this, new LeaveEvent(database));
        getLogger().info("Plugin v1.0.0-DEV Loaded!");
    }

}