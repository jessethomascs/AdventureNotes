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
import com.google.gson.GsonBuilder;

import jesse.adventurenotes.App;
import jesse.adventurenotes.models.Note;

public class AdventureNotesUtil {
    // Will Create, Read, Edit, and Remove notes
    public static Map<Integer, Note> globalNotebook = new HashMap<Integer, Note>();
    private static int topNoteIndex;

    public static void createNote(Player p, String newNote) throws IOException {
        Note note = new Note();
        note.addNote(newNote);
        note.setName(p.getDisplayName());

        if (globalNotebook.isEmpty()) {
            topNoteIndex = 1; // Start notes at 1
        } else {
            topNoteIndex = Collections.max(globalNotebook.keySet()) + 1;
        }  
        globalNotebook.put(topNoteIndex, note); // Puts onto stack

        try {
            storeNote(note); // TODO: This is completely debugging to test persistant data storage
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public static void storeNote(Player player, String note) {
        
    }

    // Retrieve all notes from persistant data storage (should only run on starting server)
    public static void retrieveGlobalNotes() {

    }

    // TODO: Fix to return a proper JSON Object
    private Object readFromJSONFile(String fileName) throws IOException {
        // will return JSON Object
        return false;
    }

    private Boolean writeToJSONFile(String fileName) throws IOException {


        return false;
    }

    // Make a JSON file
    private Boolean createJSONFile(String fileName) throws IOException {
        try {
            if (exists(fileName, "json")) { return true; }
            File file = new File(App.getPlugin().getDataFolder().getAbsolutePath() + "/" + fileName);
            file.getParentFile().mkdir();
            file.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Generic check to see if file exists
    private Boolean exists(String fileName, String ext) {
        File file = new File(App.getPlugin().getDataFolder().getAbsolutePath() + "/" + fileName + "." + ext);
        
        return file.exists();
    }
}