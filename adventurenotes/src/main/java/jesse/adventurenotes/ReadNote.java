package jesse.adventurenotes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.utils.AdventureNotesUtil;

public class ReadNote implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            /*
             * TODO: THIS MUST BE FIXED TO BE A SAFELY CHECKED CAST. IT IS VOLATILE
             */
            Integer test = (int) args[1].charAt(0); // This is a DANGEROUS cast!!

            sender.sendMessage(AdventureNotesUtil.readNote(test).getNote());
            return true;
        }
        return false;
    }
    
}
