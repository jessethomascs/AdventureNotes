package jesse.adventurenotes.models;

import java.util.ArrayList;
import java.util.Date;

public class Note {

    private String playerName;
    private ArrayList<String> playerNotes;
    //private int playerNoteId; TODO: Add this in order to call notes by ID
    
    public Note(String playerName, ArrayList<String> playerNotes) {
        this.playerName = playerName;
        this.playerNotes = playerNotes;
    }

    public Note() {
        // Default constructor
        this.playerName = null;
        this.playerNotes = null;
    }

    public String getName() {
        return this.playerName;
    }

    public ArrayList<String> getNotes() {
        return this.playerNotes;
    }

    public boolean setName(String name) { 
        try {
            this.playerName = name;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addNote(String newNote) {
        try {
            this.playerNotes.add(newNote);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
