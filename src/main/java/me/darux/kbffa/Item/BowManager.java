package me.darux.kbffa.Item;

import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.List;

public class BowManager implements Listener {
    @EventHandler
    public void onShot(ProjectileLaunchEvent e){



        if(e.getEntity().getShooter() instanceof Player){
            ((Player) e.getEntity().getShooter()).getItemInHand().setDurability((short) 0);
           Jugador jugador=new JugadorUtils().getJugador(((Player) e.getEntity().getShooter()).getName());
           if(jugador.getBowcooldown()!=0){
               e.setCancelled(true);
               jugador.getPlayer().sendMessage(Utils.translate("&cDebes esperar "+jugador.getBowcooldown()+" para disparar de nuevo"));
           }else{
               jugador.setBowcooldown(Main.getInstance().getConfig().getInt("COOLDOWNS.BOW"));
           }
        }

    }

    public static void updateCooldowns(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Jugador jugador : Main.getInstance().getOnline()){
                    if(jugador.getBowcooldown()>0){
                        jugador.setBowcooldown(jugador.getBowcooldown()-1);
                    }
                }
            }
        },0,20);
    }
}
