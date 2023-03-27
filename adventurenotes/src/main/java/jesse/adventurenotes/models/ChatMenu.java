/* 
 * Intention: To build a chat menu in the Minecraft chat, and modify its parameters
 * Chat menu will be comprised of NOTE objects in a list that are stacked so that you can hover over them and read the text
*/
package jesse.adventurenotes.models;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jesse.adventurenotes.utils.AdventureNotesUtil;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ChatMenu implements CommandExecutor {
    private Integer itemsToDisplay;

    public ChatMenu() {
        this.itemsToDisplay = 10;
    }

    public ChatMenu(int n) {
        if (n < 1) { return; } // Invalid input
        this.itemsToDisplay = n;
    }

    public void printChatMenu(Player player, int pageNum) {
        /*
         * To get correct starting page number: Do (0 + pageNum) * 10
         * To get stopping page number, do i < i + 10 (ex: Page num = 2, so 0 + 2 = 20 go to 29 (so less < 30))
        */
        Integer totalEntries = AdventureNotesUtil.notes.size();
        Integer totalMenuPages = (int) Math.ceil(totalEntries / 10);
        int start = (0 + pageNum) * 10;
        int finish = start + 10;

        if (finish > totalEntries) { finish = start + (totalEntries - start); }
        if (totalEntries < 1) { player.sendMessage("There are no notes to display!"); }
        if (pageNum > totalMenuPages) { player.sendMessage("Invalid page number, there are only " + totalMenuPages + " pages!"); }

        player.sendMessage(">>>> " + ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "Adventure" + ChatColor.GOLD + "Notes" + ChatColor.WHITE + "]" + ChatColor.BOLD + " Page " + (pageNum + 1) + "/" + (totalMenuPages + 1) + " ---------");
        for (int i = start; i < finish; i++) {
            Text mouseHoverText = new Text(AdventureNotesUtil.notes.get(i).getNote());
            
            TextComponent newLine = new TextComponent();
            newLine.setText("Note " + (i + 1) + " - " + AdventureNotesUtil.notes.get(i).getName());
            newLine.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, mouseHoverText));

            player.spigot().sendMessage(newLine);
        }
        player.sendMessage("---------------------------------------");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int pageNum = 0;
        try {
            pageNum = Integer.parseInt(args[0]) - 1; // Subtract 1 for the math in the print function above. 
            if (pageNum < 1) {
                pageNum = 0;
            }
        } catch (NumberFormatException nfe) {
            sender.sendMessage("Hey! Correct utilization is " + ChatColor.RED + "/chatmenu <number>");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        printChatMenu((Player) sender, pageNum);

        return true;
    }
}
