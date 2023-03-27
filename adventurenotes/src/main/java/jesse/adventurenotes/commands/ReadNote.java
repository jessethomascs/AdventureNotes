package jesse.adventurenotes.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.models.Note;
import jesse.adventurenotes.utils.AdventureNotesUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ReadNote implements CommandExecutor {

    private ArrayList<Note> read(String username) {
        return AdventureNotesUtil.retrieveNotes(username);
    }

    /* Returns the exat Note ID */
    private Note read(int id) {
        return new Note();
    }

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

            /* Delete this after, this is to test hovering over messages */
            // Audience - Grouping of 0+ viewers of some content
            // Content - Chat Components
            TextComponent textComponent = new TextComponent("Click me");
            textComponent.setFont("minecraft:uniform");
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click me")));
            //textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.);
            sender.spigot().sendMessage(textComponent);
            
            /* End */


            sender.sendMessage("You entered: " + ChatColor.RED + args[0]);
            sender.sendMessage(AdventureNotesUtil.retrieveNote(args[0]).getNote());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}