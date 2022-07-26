package me.darux.kbffa;

import me.darux.kbffa.Arena.Arena;
import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.Enums.LargaDistancia;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

public class TimeManager {

    public static void run(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if((ArenaUtils.getEnabledArena()!=null)){
                    ArenaUtils.getEnabledArena().sumartiempoactiva();
                    if(ArenaUtils.getEnabledArena().getTiempoactiva()==600){
                        ArenaUtils.cambiararena();
                    }
                    for(Jugador jugador:Main.getInstance().getOnline()){
                        for(int i=0;i<10;i++){
                            if(jugador.getPlayer().getInventory().getItem(i) != null){
                                if(jugador.getPlayer().getInventory().getItem(i).getType().equals(jugador.getBloques()) && jugador.getPlayer().getInventory().getItem(i).getAmount()<32){
                                    jugador.getPlayer().getInventory().getItem(i).setAmount(jugador.getPlayer().getInventory().getItem(i).getAmount()+1);
                                }
                            }

                        }
                        jugador.aumentartiempo();
                    }
                }

            }
        },0,20);
    }


    public static String timeformat(int sec) {
        int min = 0;
        int horas = 0;
        while (sec > 59) {
            sec = sec - 60;
            min++;
        }
        while (min > 59) {
            min = min - 60;
            horas++;
        }
        if(horas>=1){
            return horas+"h "+min+"min "+sec+"s";
        }else if(min>=1){
            return min+"min "+sec+"s";
        }else return sec+"s";

    }


    public static void eliminarbloque(Block bloque){
        final int[] tiempo = {0};

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                tiempo[0]++;
                if(tiempo[0]==0){
                    Main.getInstance().getBloques().add(bloque);
                }
                if(tiempo[0]==Main.getInstance().getConfig().getInt("ELIMINAR-BLOQUES")){
                    bloque.setType(Material.AIR);
                    Main.getInstance().getBloques().remove(bloque);

                }

            }
        },0,20);

    }

    public void tnt(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Jugador jugador: Main.getInstance().getOnline()){
                    if(jugador.getLargaDistancia().equals(LargaDistancia.LANZATNT) && jugador.isJugando()){
                        for(int i=0;i<jugador.getPlayer().getInventory().getSize();i++){
                            if(jugador.getPlayer().getInventory().getItem(i) != null){
                                if(jugador.getPlayer().getInventory().getItem(i).getType().equals(Material.SHEARS)){
                                    if(jugador.getTntrestante()<2){
                                        jugador.setTntrestante(jugador.getTntrestante()+1);
                                        ItemMeta meta=jugador.getPlayer().getInventory().getItem(i).getItemMeta();
                                        meta.setDisplayName(Utils.translate("&cLanza tnt &8(&e"+jugador.getTntrestante()+"&8)"));
                                        jugador.getPlayer().getInventory().getItem(i).setItemMeta(meta);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },0,200);
    }

}
