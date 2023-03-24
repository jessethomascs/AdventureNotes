package jesse.adventurenotes.models;

import java.util.ArrayList;
import java.util.UUID;

public class Note {

    private String playerUUID; // Unique identifier
    private String playerName;
    private String playerNote;
    private String noteId;
    
    public Note(String playerName, String playerNote, String playerUUID) {
        this.playerName = playerName;
        this.playerNote = playerNote;
        this.playerUUID = playerUUID;
        this.noteId = UUID.randomUUID().toString();
    }

    public Note() {
        // Default constructor
        this.playerName = null;
        this.playerNote = null;
        this.playerUUID = null;
        this.noteId = null;
    }

    public String getName() {
        return this.playerName;
    }

    public String getNote() {
        return this.playerNote;
    }

    public String getPlayerUUID() {
        return this.playerUUID;
    }

    public String getUniqueUUID() {
        return this.noteId;
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

    public Boolean setNote(String note) {
        try {
            this.playerNote = note;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
