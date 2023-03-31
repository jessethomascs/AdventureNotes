package jesse.adventurenotes;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import jesse.adventurenotes.commands.ActiveChatListener;
import jesse.adventurenotes.commands.AddNote;
import jesse.adventurenotes.commands.DeleteNote;
import jesse.adventurenotes.commands.EditNote;
import jesse.adventurenotes.commands.ReadNote;
import jesse.adventurenotes.models.ChatMenu;
import jesse.adventurenotes.utils.AdventureNotesUtil;
import jesse.adventurenotes.utils.EventListeners;
public class App extends JavaPlugin {
    
    public static App myPlugin;
    public static String pathToNotesJson;
    public static boolean activeChatListener;

    @Override
    public void onEnable() {

        myPlugin = this;
        getLogger().info("AdventureNotes rolled a nat 20");
        pathToNotesJson = this.getDataFolder().getAbsolutePath() + "/notes.json";
        getServer().getPluginManager().registerEvents(new EventListeners(), getPlugin());
        this.getCommand("note").setExecutor(new AddNote());
        this.getCommand("addnote").setExecutor(new AddNote());
        this.getCommand("edit").setExecutor(new EditNote());
        this.getCommand("delete").setExecutor(new DeleteNote());
        this.getCommand("acl").setExecutor(new ActiveChatListener());
        this.getCommand("read").setExecutor(new ReadNote());
        this.getCommand("chatmenu").setExecutor(new ChatMenu());

        activeChatListener = false;

        try {
            AdventureNotesUtil.loadNotes(pathToNotesJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("AdventureNotes rolled a nat 1");
    }

    // For dependency injection
    public static App getPlugin() {
        return myPlugin;
    }
}