package me.darux.kbffa.Arena;

import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Item.ItemUtils;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class ArenaUtils {

    public static Arena getEnabledArena(){
        for(Arena arena : Main.getInstance().getArenas()){
            if(arena.isActiva()){
                return arena;
            }
        }
   return null;
    }

    public static void cargarArenas(FileCreator data){




       try{
           for(String key : data.getConfigurationSection("Arenas").getKeys(false)){
               Location location=new Location(Bukkit.getWorld("world"),data.getInt("Arenas."+key+".spawn.x"),data.getInt("Arenas."+key+".spawn.y"),data.getInt("Arenas."+key+".spawn.z"));
               Arena arena=new Arena(location,key);
               Main.getInstance().getArenas().add(arena);
           }
           int random= (int) Math.round(Math.random()*Main.getInstance().getArenas().size());
           try{
               Main.getInstance().getArenas().get(random).setActiva(true);
           }catch (IndexOutOfBoundsException e){
               Main.getInstance().getArenas().get(0).setActiva(true);
           }
       }catch (NullPointerException e){

       }




    }

    public static void cambiararena(){
        ArenaUtils.getEnabledArena().setTiempoactiva(0);

        Main.changes++;
        if(Main.changes==18){
            for(Player p : Bukkit.getOnlinePlayers()){
                p.kickPlayer(Utils.translate("&eReiniciando el servidor"));
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"restart");
        }

        Arena nuevaarena;
        ArenaUtils.getEnabledArena().setTiempoactiva(0);





        int random= (int) Math.round(Math.random()*Main.getInstance().getArenas().size());
        try{
            nuevaarena= Main.getInstance().getArenas().get(random);
        }catch (IndexOutOfBoundsException e){
           nuevaarena= Main.getInstance().getArenas().get(0);
        }
        if(nuevaarena.getNombre().equals(ArenaUtils.getEnabledArena().getNombre())) {
            cambiararena();
            return;
        }
        ArenaUtils.getEnabledArena().setActiva(false);
        nuevaarena.setActiva(true);
        for(Jugador jugador : Main.getInstance().getOnline()){
            jugador.setJugando(false);
            jugador.getPlayer().teleport(nuevaarena.getSpawn());
            jugador.getPlayer().sendTitle(Utils.translate("&a&lEl mapa ha sido cambiado"),"");
            ItemUtils.setDefaultInv(jugador.getPlayer());

        }
        Main.borrartodos=true;
    }


    public static void cambiararenapornombre(String nombre){
        for(Arena arena: Main.getInstance().getArenas()){
            arena.setTiempoactiva(0);
        }



        Arena nuevaarena=Main.getInstance().getArenas().get(0);
        for(Arena arena : Main.getInstance().getArenas()){
            if(arena.getNombre().equalsIgnoreCase(nombre)){
                nuevaarena=arena;
            }
        }






        ArenaUtils.getEnabledArena().setActiva(false);
        nuevaarena.setActiva(true);
        for(Jugador jugador : Main.getInstance().getOnline()){
            jugador.setJugando(false);
            jugador.getPlayer().teleport(nuevaarena.getSpawn());
            jugador.getPlayer().sendTitle(Utils.translate(Main.getInstance().getConfig().getString("ARENA-CHANGE.title")),Main.getInstance().getConfig().getString("ARENA-CHANGE.subtitle"));
            ItemUtils.setDefaultInv(jugador.getPlayer());

        }
        Main.borrartodos=true;
    }

    public static Arena getArena(String name){
        for(Arena arena : Main.getInstance().getArenas()){
            if(arena.getNombre().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }


}
