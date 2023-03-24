package jesse.adventurenotes.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.models.Note;
import jesse.adventurenotes.utils.AdventureNotesUtil;

public class EditNote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*
         * Structure of command should be as entered:
         * /edit <unique_note_id> <new_msg>
         */
        Player player = (Player) sender;


        String newMsg = Arrays.toString(args).split(" ", 2)[1];
        sender.sendMessage("HEY! String split occured into this: ");
        sender.sendMessage("Original: " + Arrays.toString(args));
        sender.sendMessage("New: " + newMsg);
        
        Note newNote = new Note(sender.getName(), newMsg, player.getUniqueId().toString());

        try {
            AdventureNotesUtil.ModifyNote(newNote, args[1]);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
