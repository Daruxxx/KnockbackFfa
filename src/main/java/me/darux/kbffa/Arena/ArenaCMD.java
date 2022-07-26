package me.darux.kbffa.Arena;

import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Main plugin=Main.getInstance();

        if(!(sender instanceof Player)){
            sender.sendMessage(Utils.translate("&cEste comando solo puede ser ejecutado por un jugador"));
            return false;
        }
        Player p=(Player) sender;
        if(args[0].equalsIgnoreCase("create")){
            if(args.length<2){
                sender.sendMessage(Utils.translate("&3/arena create <name>"));
            }else{
                String name=args[1];
                if(p.getLocation().getY()<85){
                    sender.sendMessage(Utils.translate("&cEl spawn de la arena tiene que estar por arriba de la cordenada 85"));
                    return false;
                }

                Location spawn=p.getLocation();
                Arena arena=new Arena(spawn,name);
                plugin.getArenas().add(arena);
                sender.sendMessage(Utils.translate("&aLa arena ha sido creada correctamente"));
            }
        }else if(args[0].equalsIgnoreCase("setspawn")){
            if(args.length<2){
                sender.sendMessage(Utils.translate("&3/arena setspawn <arena>"));
            }else{
                Arena arena=ArenaUtils.getArena(args[1]);
                if(arena==null){
                    sender.sendMessage(Utils.translate("&cEsta arena no existe"));
                }else{
                    if(p.getLocation().getY()<85){
                        sender.sendMessage(Utils.translate("&cEl spawn de la arena tiene que estar por arriba de la cordenada 85"));
                    }else{
                        arena.setSpawn(p.getLocation());
                        sender.sendMessage(Utils.translate("&aHas cambiado el spawn de la arena &6"+arena.getNombre()+" &acorrectamente"));
                    }
                }
            }
        }
        return false;
    }
}
