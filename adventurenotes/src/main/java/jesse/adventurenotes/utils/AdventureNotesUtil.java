package jesse.adventurenotes.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static ArrayList<Note> notes = new ArrayList<>();

    public static Note createNote(Player p, String newNote) throws IOException {
        Note note = new Note(p.getDisplayName(), newNote, p.getUniqueId().toString());
        notes.add(note);

        saveNotes(App.myPlugin.getDataFolder().getAbsolutePath() + "/notes.json");
        return note;
    }

    public static Note retrieveNote(String uniqueId) {
        for (Note note : notes) {
            if (note.getUniqueUUID().equals(uniqueId)) {
                return note;
            }
        }
        return null;
    }

    public static Note ModifyNote(Note updatedNote, String uniqueId) {
        for (Note note : notes) {
            if (note.getUniqueUUID().equals(uniqueId)) {
                note.setName(updatedNote.getName());
                note.setNote(updatedNote.getNote());
                return note;
            }
        }
        return null;
    }

    public static void DeleteNote(String uniqueId) {
        for (Note note : notes) {
            if (note.getUniqueUUID().equals(uniqueId)) {
                notes.remove(note);
                break;
            }
        }
    }

    public static void loadNotes(String filePath) throws IOException {
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            File file = new File(filePath);

            if (!file.exists()) { return; }

            Reader reader = new FileReader(file);
            Note[] n = gson.fromJson(reader, Note[].class);
            notes = new ArrayList<>(Arrays.asList(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveNotes(String filePath) throws IOException {
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            File file = new File(filePath);
            file.getParentFile().mkdir();
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(notes, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}