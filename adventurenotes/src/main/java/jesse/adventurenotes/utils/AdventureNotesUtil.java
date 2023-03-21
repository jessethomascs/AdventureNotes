package jesse.adventurenotes.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Struct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;

import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
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

    // Retrieve all notes from persistant data storage (should only run on starting server)
    public static void retrieveGlobalNotes(Player player) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(globalNotebook);
        Type type = new TypeToken<HashMap<Integer, Note>>(){}.getType();
        Map<Integer, Note> clone = gson.fromJson(json, type); // This seems inherintly janky. Is there not a better way to do this?
        
        
        player.sendMessage("--------------------------------");
        player.sendMessage(json);
        player.sendMessage(clone.toString());
    }
}