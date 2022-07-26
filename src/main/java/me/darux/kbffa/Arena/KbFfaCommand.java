package me.darux.kbffa.Arena;

import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KbFfaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.isOp()){
            FileCreator data= Main.getInstance().getData();
            if(args.length==0){
                sender.sendMessage(Utils.translate("&c------------------------"));
                sender.sendMessage(Utils.translate("&6/kbffa money <set/add/take>"));
                sender.sendMessage(Utils.translate("&6/kbffa changemap"));
                sender.sendMessage(Utils.translate("&c------------------------"));
            }else if(args[0].equalsIgnoreCase("money")){
                Jugador jugador=new JugadorUtils().getJugador(args[2]);
                if(args[1].equals("set") && args.length>3){
                    int dinero=Integer.parseInt(args[3]);
                    String player=args[2];
                    if(data.contains("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas")){
                        data.set("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas",dinero);
                        if(jugador!=null) jugador.setMonedas(dinero);
                        sender.sendMessage(Utils.translate("&aOperación realizada correctamente"));
                    }else sender.sendMessage(Utils.translate("&cNo se ha encontrado a ese jugador"));

                }else if(args[1].equalsIgnoreCase("add") && args.length>3){
                    int dinero=Integer.parseInt(args[3]);
                    String player=args[2];
                    if(data.contains("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas")){
                        data.set("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas",dinero+data.getInt("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas"));
                        if(jugador!=null) jugador.setMonedas(dinero+data.getInt("Jugadores."+ Bukkit.getOfflinePlayer(player).getUniqueId()+".monedas"));
                        sender.sendMessage(Utils.translate("&aOperación realizada correctamente"));

                    }else sender.sendMessage(Utils.translate("&cNo se ha encontrado a ese jugador"));
                }else if(args[1].equalsIgnoreCase("take")){
                    int dinero=Integer.parseInt(args[3]);
                    String nick=args[2];
                    if(jugador == null){
                        if(data.contains("Jugadores."+Bukkit.getOfflinePlayer(nick).getUniqueId()+".monedas")){
                            data.set("Jugadores."+Bukkit.getOfflinePlayer(nick).getUniqueId()+".monedas",data.getInt("Jugadores."+Bukkit.getOfflinePlayer(nick).getUniqueId()+".monedas")-dinero);
                            sender.sendMessage(Utils.translate("&aOperación realizada correctamente"));
                        }else sender.sendMessage(Utils.translate("&cEste jugador no existe"));
                    }else{
                        int tiene=(jugador.getMonedas());
                        jugador.setMonedas(tiene-dinero);
                        sender.sendMessage(Utils.translate("&aOperación realizada correctamente"));

                    }
                }else sender.sendMessage(Utils.translate("&c/kbffa money <set/add/take> <player> <amount>"));
            }else if(args[0].equalsIgnoreCase("changemap")){
                ArenaUtils.cambiararena();
            }else if(args[0].equals("reload")){
                Main.getInstance().getConfig().reload();
                sender.sendMessage(Utils.translate("&aSe ha recargado la config"));
            }
        }else{
            sender.sendMessage(Utils.translate("&aKnockFfa core, creada por D4RUX :)"));
        }
        return false;
    }
}
