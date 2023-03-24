/*
 * name: Note.java
 * Description: Note.java is responsible for [ Create, Read, Modify, Delete ] rules on the database for notes that players make! 
 * Example usage: (in game): /an:note <msg> -> (srver): Create a new note under a player file somewhere in server.
 * Example usage: (in game): /an:read <id> -> (srver):  Shouts out the note to other players (makes public note, this is done defaulty when note is created too unless stated otherwise)
 * Example usage: (in game): /an:edit <id> -> (srver):  GUI opens up to modify/edit the message that is stored for easier access
 * Example usage: (in game): /an:del <id> -> (srver):   Delete an entire note by ID entry
 */
package jesse.adventurenotes.commands;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.utils.AdventureNotesUtil;

//import com.google.gson.Gson;

public class AddNote implements CommandExecutor{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String newNote = Arrays.toString(args);

            try {
                AdventureNotesUtil.createNote((Player) sender, newNote);
                sender.sendMessage(ChatColor.DARK_AQUA + "Your note has been added.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            sender.sendMessage("HEY! You aren't a player! There are a different set of commands you can run as console.");
        }
        return false;
    }
}
