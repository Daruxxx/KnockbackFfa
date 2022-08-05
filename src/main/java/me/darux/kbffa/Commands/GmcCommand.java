package me.darux.kbffa.Commands;

import me.darux.kbffa.Utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
       if(commandSender.hasPermission("kbffa.gmc")){
           Player p=(Player) commandSender;
           p.setGameMode(GameMode.CREATIVE);
           commandSender.sendMessage(Utils.translate("&8[&b&lKnockbackFFA&8] &7Tu modo de juego ha cambiado a creativo"));

       }
        return false;
    }
}
