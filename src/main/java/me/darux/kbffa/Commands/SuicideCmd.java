package me.darux.kbffa.Commands;

import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if((commandSender instanceof Player)){

            Player player=(Player) commandSender;
            if(new JugadorUtils().getJugador(commandSender.getName()).isJugando()){
                player.setHealth(0);
            }else commandSender.sendMessage(Utils.translate("&cDebes estar jugando para ejecutar este comando"));


        }
        return false;
    }
}
