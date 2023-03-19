package jesse.adventurenotes.utils;

import org.bukkit.entity.Player;

import jesse.adventurenotes.models.Note;

public class AdventureNotesUtil {
    // Will Create, Read, Edit, and Remove notes

    public static Note createNote(Player p, String newNote) {
        Note note = new Note(p.getDisplayName(), newNote);
    }
}
