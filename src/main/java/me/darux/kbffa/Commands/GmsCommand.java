package me.darux.kbffa.Commands;

import me.darux.kbffa.Utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("kbffa.gms")){
            Player p=(Player) commandSender;
            p.setGameMode(GameMode.SURVIVAL);
            commandSender.sendMessage(Utils.translate("&8[&b&lKnockbackFFA&8] &7Tu modo de juego ha cambiado a supervivencia"));

        }
        return false;
    }
}
