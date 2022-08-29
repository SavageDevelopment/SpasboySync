package ml.savdev.spasboysync.events;

import ml.savdev.spasboysync.SpasboySync;
import ml.savdev.spasboysync.db.Database;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaveEvent implements Listener {

    private Database database;

    public LeaveEvent(Database database) {
        this.database = database;
    }

    @EventHandler
    public void PlayerDisconnectEvent(ProxiedPlayer player)
    {
        ProxyServer.getInstance().getLogger().warning("HERE IS THE UUID TO BE REMOVED! -- " + player.getUniqueId().toString());
        try {
            database.delCode(player.getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
