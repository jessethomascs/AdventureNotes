package jesse.adventurenotes.models;

import java.util.ArrayList;

public class Note {

    private String playerUUID; // Unique identifier
    private String playerName;
    private ArrayList<String> playerNotes;
    //private int playerNoteId; TODO: Add this in order to call notes by ID
    
    public Note(String playerName, ArrayList<String> playerNotes, String playerUUID) {
        this.playerName = playerName;
        this.playerNotes = playerNotes;
        this.playerUUID = playerUUID;
    }

    public Note() {
        // Default constructor
        this.playerName = null;
        this.playerNotes = null;
        this.playerUUID = null;
    }

    public String getName() {
        return this.playerName;
    }

    public ArrayList<String> getNotes() {
        return this.playerNotes;
    }

    public String getUUID() {
        return this.playerUUID;
    }

    public Boolean setName(String name) { 
        try {
            this.playerName = name;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean addNote(String newNote) {
        try {
            this.playerNotes.add(newNote);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean setUUID(String UUID) {
        try {
            this.playerUUID = UUID;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
