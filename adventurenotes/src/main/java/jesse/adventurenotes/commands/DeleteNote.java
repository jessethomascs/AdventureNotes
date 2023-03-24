package jesse.adventurenotes.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import jesse.adventurenotes.App;
import jesse.adventurenotes.utils.AdventureNotesUtil;

public class DeleteNote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            AdventureNotesUtil.DeleteNote(args[1]);
            AdventureNotesUtil.saveNotes(App.pathToNotesJson);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}