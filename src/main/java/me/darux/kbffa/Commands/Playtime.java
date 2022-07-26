package me.darux.kbffa.Commands;

import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.TimeManager;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Playtime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        int playtime=0;
        if(strings.length==0){
            playtime=new JugadorUtils().getJugador(commandSender.getName()).getPlaytime();
            commandSender.sendMessage(Utils.translate("&bHas jugado en KnockbackFFA un total de &e"+ TimeManager.timeformat(playtime)));
        }else{
            String jugador=strings[0];
            if(Bukkit.getOfflinePlayer(jugador).getName()!=null){
                playtime= Main.getInstance().getData().getInt("Jugadores."+Bukkit.getOfflinePlayer(jugador).getUniqueId()+".playtime");
                commandSender.sendMessage(Utils.translate("&bEl jugador &e"+jugador+" &bha jugado en KnockbackFFA &e"+TimeManager.timeformat(playtime)));
            }else commandSender.sendMessage(Utils.translate("&cNo se ha encontrado a ese jugador"));
        }
        return false;
    }
}
