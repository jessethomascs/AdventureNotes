package jesse.adventurenotes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import jesse.adventurenotes.models.Note;
import jesse.adventurenotes.utils.AdventureNotesUtil;
import net.md_5.bungee.api.ChatColor;

public class ReadNote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Debug purposes only: spill out file contents into chat so we can get a note ID to test
        try {
            // Let's attempt to pull all notes (lol rip chat)
            for (Note note : AdventureNotesUtil.notes) {
                sender.sendMessage("---------------------------------");
                sender.sendMessage(note.getName());
                sender.sendMessage(note.getNote());
                sender.sendMessage(note.getUniqueUUID());
                sender.sendMessage("---------------------------------");
            }
            sender.sendMessage("You entered: " + ChatColor.RED + args[0]);
            sender.sendMessage(AdventureNotesUtil.retrieveNote(args[0]).getNote());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
