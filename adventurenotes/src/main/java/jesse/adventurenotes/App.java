package jesse.adventurenotes;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getLogger().info("AdventureNotes rolled a nat 20");
        this.getCommand("note").setExecutor(new AddNote());
        this.getCommand("read").setExecutor(new ReadNote());
        this.getCommand("edit").setExecutor(new EditNote());
        this.getCommand("delete").setExecutor(new DeleteNote());
    }

    @Override
    public void onDisable() {
        getLogger().info("AdventureNotes rolled a nat 1");
    }
}