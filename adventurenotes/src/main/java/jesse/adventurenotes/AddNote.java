/*
 * name: Note.java
 * Description: Note.java is responsible for [ Create, Read, Modify, Delete ] rules on the database for notes that players make! 
 * Example usage: (in game): /an:note <msg> -> (srver): Create a new note under a player file somewhere in server.
 * Example usage: (in game): /an:read <id> -> (srver):  Shouts out the note to other players (makes public note, this is done defaulty when note is created too unless stated otherwise)
 * Example usage: (in game): /an:edit <id> -> (srver):  GUI opens up to modify/edit the message that is stored for easier access
 * Example usage: (in game): /an:del <id> -> (srver):   Delete an entire note by ID entry
 */
package jesse.adventurenotes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.models.Note;

//import com.google.gson.Gson;

public class AddNote implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(command.toString());
            sender.sendMessage(label);
            sender.sendMessage(Integer.toString(args.length));
            sender.sendMessage("Note added in journal.");
        } else {
            sender.sendMessage("HEY! You aren't a player! There are a different set of commands you can run as console.");
        }

        return false;
    }

    public static Note createNote(Player p, String note) {
        
    }
}
