package me.darux.kbffa.Arena;

import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Item.ItemUtils;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;

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
    }

    public static Arena getArena(String name){
        for(Arena arena : Main.getInstance().getArenas()){
            if(arena.getNombre().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }


}
