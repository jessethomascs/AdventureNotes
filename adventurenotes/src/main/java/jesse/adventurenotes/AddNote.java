/*
 * name: Note.java
 * Description: Note.java is responsible for [ Create, Read, Modify, Delete ] rules on the database for notes that players make! 
 * Example usage: (in game): /an:note <msg> -> (srver): Create a new note under a player file somewhere in server.
 * Example usage: (in game): /an:read <id> -> (srver):  Shouts out the note to other players (makes public note, this is done defaulty when note is created too unless stated otherwise)
 * Example usage: (in game): /an:edit <id> -> (srver):  GUI opens up to modify/edit the message that is stored for easier access
 * Example usage: (in game): /an:del <id> -> (srver):   Delete an entire note by ID entry
 */
package jesse.adventurenotes;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.utils.AdventureNotesUtil;

//import com.google.gson.Gson;

public class AddNote implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("DEBUG ----");
            sender.sendMessage(command.toString());
            sender.sendMessage(label);
            sender.sendMessage(Integer.toString(args.length));
            sender.sendMessage("Note added in journal.");
            sender.sendMessage("--------------------------");

            String newNote = Arrays.toString(args);
            Integer temp = -1;

            try {
                temp = AdventureNotesUtil.createNote((Player) sender, newNote);
                sender.sendMessage("stackTop: " + temp.toString());
                AdventureNotesUtil.retrieveGlobalNotes((Player) sender);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        } else {
            sender.sendMessage("HEY! You aren't a player! There are a different set of commands you can run as console.");
        }
        return false;
    }

    
    
}
