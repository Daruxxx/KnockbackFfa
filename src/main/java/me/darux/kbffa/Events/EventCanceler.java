package me.darux.kbffa.Events;

import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.inventivetalent.particle.ParticleEffect;

public class EventCanceler implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) e.setCancelled(true);
    }
    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void weather(WeatherChangeEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntityType().equals(EntityType.PLAYER) && e.getDamager().getType().equals(EntityType.PLAYER)){

            Player damager= (Player) e.getDamager();
            Player damaged= (Player) e.getEntity();
            damaged.setHealth(20);

            Jugador dañado= new JugadorUtils().getJugador(damaged.getName());
            Jugador dañante=new JugadorUtils().getJugador(damager.getName());

            if(!dañado.isJugando() || !dañante.isJugando()) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getPlayer().getLocation().getY()>100 && e.getTo().getY()<=100){
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_PLING,10,10);
            JugadorUtils.comenzarJugar(e.getPlayer());
        }
        if(e.getPlayer().getLocation().getY()>76 && e.getTo().getY()<=76){
            e.getPlayer().setHealth(0);

        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent e){

        if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE) && e.getInventory().getName().startsWith("container."))return;
        if(e.getInventory().getName().equals(Utils.translate("&bOrdenar Inventario"))) return;
        e.setCancelled(true);
    }



    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }
}
