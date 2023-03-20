package jesse.adventurenotes.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import jesse.adventurenotes.models.Note;

public class AdventureNotesUtil {
    // Will Create, Read, Edit, and Remove notes
    public static Map<Integer, Note> globalNotebook = new HashMap<Integer, Note>();

    public static void createNote(Player p, String newNote) {
        Note note = new Note(p.getDisplayName(), newNote);

        int stackTop = Collections.max(globalNotebook.keySet()); // Grabs current highest value (in theory)
        globalNotebook.put(++stackTop, note); // Puts onto stack
    }

    public static Note readNote(int index) {
        Note ret = globalNotebook.get(index);

        if (ret == null) return new Note();

        return ret;
    }

    public static void ModifyNote(int index) {
        // Do something with a GUI here...?
    }

    public static void DeleteNote(int index) {
        globalNotebook.remove(index);
    }
}