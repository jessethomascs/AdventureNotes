package jesse.adventurenotes.models;

import java.util.Date;

public class Note {

    private String playerName;
    private String playerNote;
    private Date noteDate;
    //private int playerNoteId; TODO: Add this in order to call notes by ID
    
    public Note(String playerName, String playerNote) {
        this.playerName = playerName;
        this.playerNote = playerNote;
        this.noteDate = new Date();
    }

    public String getName() {
        return this.playerName;
    }

    public String getNote() {
        return this.playerNote;
    }

    public Date getDate() {
        return this.noteDate;
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

    public boolean setDate(Date date) {
        try {
            this.noteDate = date;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setNote(String note) {
        try {
            this.playerNote = note;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
