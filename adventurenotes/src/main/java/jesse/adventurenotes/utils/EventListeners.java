package jesse.adventurenotes.utils;

import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import jesse.adventurenotes.models.Note;

public class EventListeners implements Listener {

    /* Special Case Variables */
    Boolean firstJoin = true;
    Boolean activeChatListener = false; // off by default

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        firstJoin = false;

        // TODO: In the future add these kinds of messages from a message_error.json or something. Should not be hard coded!!
        if (firstJoin && activeChatListener) { 
            Bukkit.broadcastMessage("[AdventureNotes] >> Hey! You have Active Chat Listening turned OFF! Do '/an:cl on' to turn on!");

        } else if (firstJoin && activeChatListener) {
            Bukkit.broadcastMessage("[AdventureNotes] >> Hey! You have Active Chat Listening turned ON! Do '/an:cl off' to turn off!");
        }
    }

    /* ACTIVE CHAT LISTENER FUNCTION */
    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent e) throws IOException {
        activeChatListener = true; // TODO: Make this a modifiable change by command (which alters config)
        if (activeChatListener) {
            // Plugin will log ALL messages into the GLOBAL NOTEBOOK. This is used as a seamless alternative to '/addnote <msg>'
            // Option is best used when players are in a separate voice chat application on their PC's - and only using chat for important stuff
            try {
                AdventureNotesUtil.createNote(e.getPlayer(), e.getMessage());

                /* TODO: This is some particle messing around. This can be moved to a separate area or removed altogether for later. Here for reminders
                Particle.DustOptions color = new Particle.DustOptions(Color.BLUE, 3.0f);

                double x = e.getPlayer().getLocation().getX();
                double y = e.getPlayer().getLocation().getY() + (double) 2.5;
                double z = e.getPlayer().getLocation().getZ();

                e.getPlayer().spawnParticle(Particle.REDSTONE, x, y, z, 1, color);
                */
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } else {
            return;
        }
    }
}
