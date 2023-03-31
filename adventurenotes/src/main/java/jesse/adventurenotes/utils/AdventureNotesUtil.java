package jesse.adventurenotes.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jesse.adventurenotes.App;
import jesse.adventurenotes.models.Note;

public class AdventureNotesUtil {
    // Will Create, Read, Edit, and Remove notes
    //public static Map<Integer, Note> globalNotebook = new HashMap<Integer, Note>();
    public static ArrayList<Note> notes = new ArrayList<>();

    public static Note createNote(Player p, String newNote) throws IOException {
        Note note = new Note(p.getDisplayName(), newNote, p.getUniqueId().toString());
        notes.add(note);

        saveNotes(App.myPlugin.getDataFolder().getAbsolutePath() + "/notes.json");

        //createPlayerNoteParticle(p);
        drawPencilArray(p);
        return note;
    }

    /* Returns all created notes stored in json */
    public static Note retrieveNote(String uniqueId) {
        for (Note note : notes) {
            if (note.getUniqueUUID().equals(uniqueId)) {
                return note;
            }
        }
        return null;
    }

    /* Overload: Returns only notes from specific player */
    public static ArrayList<Note> retrieveNotes(String playerName) {
        ArrayList<Note> filteredList = new ArrayList<>();
        for (Note note : notes) {
            if (note.getName().equals(playerName)) {
                filteredList.add(note);
            }
        }
        return filteredList;
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

    public static Boolean DeleteNote(String uniqueId) {
        for (Note note : notes) {
            if (note.getUniqueUUID().equals(uniqueId)) {
                notes.remove(note);
                return true;
            }
        }
        return false;
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

    public static void createPlayerNoteParticle(Player player) {
        App.myPlugin.getLogger().info("Attempting to create on player " + player.getDisplayName());
        Particle.DustOptions highColor = new Particle.DustOptions(Color.WHITE, 3.0f);
        Particle.DustOptions lowColor = new Particle.DustOptions(Color.RED, 3.0f);

        double x = player.getLocation().getX();
        double y = player.getLocation().getY() + (double) 3.0; // Original offset to get above head
        double z = player.getLocation().getZ();

        player.spawnParticle(Particle.REDSTONE, x, y + 0.5f, z, 20, highColor);
        player.spawnParticle(Particle.REDSTONE, x, y, z, 20, lowColor);
    }

    public static void drawPencilArray(Player player) {

        App.myPlugin.getLogger().info("[AdventureNotes] attempting to draw a giant pencil");

        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY() + (double) 6.5;
        double playerZ = player.getLocation().getZ();

        int[][] pencil = {
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 3, 3, 0, 0},
          {0, 0, 0, 0, 0, 3, 3, 3, 0, 0},
          {0, 0, 0, 0, 2, 2, 3, 0, 0, 0},
          {0, 0, 0, 2, 2, 2, 0, 0, 0, 0},
          {0, 0, 2, 2, 2, 0, 0, 0, 0, 0},  
          {0, 1, 1, 2, 0, 0, 0, 0, 0, 0},  
          {1, 1, 1, 0, 0, 0, 0, 0, 0, 0},  
          {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (pencil[i][j] == 0) {
                    Particle.DustOptions zeroColor = new Particle.DustOptions(Color.NAVY, 1.0f);
                    //player.spawnParticle(Particle.REDSTONE, x, y + 0.5f, z, 20, zeroColor);

                    double x = (playerX + (float) j / 2.0f) - 2.1f; // Subtract ALL the x values by 2.1 (this is an eye-ball adjustment to make this shape center above player head)
                    double y = (playerY - (double) i / 3.0f);
                    double z = playerZ;

                    player.spawnParticle(Particle.REDSTONE, x, y, z, 15, zeroColor);


                } else if (pencil[i][j] == 1) {

                    double x = (playerX + (float) j / 2.0f) - 2.1f;
                    double y = (playerY - (double) i / 3.0f);
                    double z = playerZ;

                    Particle.DustOptions oneColor = new Particle.DustOptions(Color.BLACK, 2.0f);

                    player.spawnParticle(Particle.REDSTONE, x, y, z, 15, oneColor);

                } else if (pencil[i][j] == 2) {

                    double x = (playerX + (float) j / 2.0f) - 2.1f;
                    double y = (playerY - (double) i / 3.0f);
                    double z = playerZ;

                    Particle.DustOptions twoColor = new Particle.DustOptions(Color.YELLOW, 2.0f);

                    player.spawnParticle(Particle.REDSTONE, x, y, z, 15, twoColor);

                } else if (pencil[i][j] == 3) {

                    double x = (playerX + (float) j / 2.0f) - 2.0f;
                    double y = (playerY - (double) i / 3.0f);
                    double z = playerZ;

                    Particle.DustOptions threeColor = new Particle.DustOptions(Color.PURPLE, 2.0f);

                    player.spawnParticle(Particle.REDSTONE, x, y, z, 15, threeColor);

                } else {
                    // only god knows what ends up here
                    continue;
                }
            }
        }
    }
}