package jesse.adventurenotes;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin {
    
    public static App myPlugin;

    @Override
    public void onEnable() {
        getLogger().info("AdventureNotes rolled a nat 20");
        this.getCommand("note").setExecutor(new AddNote());
        this.getCommand("read").setExecutor(new ReadNote());
        this.getCommand("edit").setExecutor(new EditNote());
        this.getCommand("delete").setExecutor(new DeleteNote());

        myPlugin = this;
    }

    @Override
    public void onDisable() {
        getLogger().info("AdventureNotes rolled a nat 1");
    }

    public static App getPlugin() {
        return myPlugin;
    }
}