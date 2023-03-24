package jesse.adventurenotes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import jesse.adventurenotes.App;

public class ActiveChatListener implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        App.activeChatListener = !App.activeChatListener;
        return true;
    }
}
