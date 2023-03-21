package jesse.adventurenotes.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.google.gson.Gson;

import jesse.adventurenotes.App;
import jesse.adventurenotes.models.Note;

public class AdventureNotesUtil {
    // Will Create, Read, Edit, and Remove notes
    public static Map<Integer, Note> globalNotebook = new HashMap<Integer, Note>();

    public static int createNote(Player p, String newNote) throws IOException {
        int stackTop;
        if (globalNotebook.isEmpty()) {
            stackTop = 1; // Start notes at 1
        } else {
            stackTop = Collections.max(globalNotebook.keySet()) + 1;
        }

        Note note = new Note(p.getDisplayName(), newNote);      
        globalNotebook.put(stackTop, note); // Puts onto stack

        try {
            storeNote(note); // TODO: This is completely debugging to test persistant data storage
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stackTop;
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

    // Store note to persistant data storage
    public static void storeNote(Note note) throws IOException {
        Gson gson = new Gson();

        try { 
            File file = new File(App.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
            file.getParentFile().mkdir();
            file.createNewFile();
            Writer writer = new FileWriter(file, true);
            gson.toJson(globalNotebook, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
           
    }
}