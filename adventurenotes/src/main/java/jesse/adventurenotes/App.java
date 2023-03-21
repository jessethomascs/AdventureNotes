package jesse.adventurenotes;

import org.bukkit.plugin.java.JavaPlugin;

import jesse.adventurenotes.utils.EventListeners;
public class App extends JavaPlugin {
    
    public static App myPlugin;

    @Override
    public void onEnable() {
        myPlugin = this;
        getLogger().info("AdventureNotes rolled a nat 20");
        getServer().getPluginManager().registerEvents(new EventListeners(), getPlugin());
        this.getCommand("note").setExecutor(new AddNote());
        this.getCommand("read").setExecutor(new ReadNote());
        this.getCommand("edit").setExecutor(new EditNote());
        this.getCommand("delete").setExecutor(new DeleteNote());
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