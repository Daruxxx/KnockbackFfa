package me.darux.kbffa.Jugador;

import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JugadorJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(Utils.translate(Main.getInstance().getConfig().getString("JOIN-MESSAGE").replaceAll("%player%",e.getPlayer().getName())));
        Main plugin=Main.getInstance();
        JugadorUtils jugadorUtils=new JugadorUtils();

        if(plugin.getData().contains("Jugadores."+e.getPlayer().getUniqueId()+".nick")){
            jugadorUtils.cargarJugador(e.getPlayer());
        }else{
            jugadorUtils.crearJugador(e.getPlayer());
        }

    }

     @EventHandler
    public void onquit(PlayerQuitEvent e){
        e.setQuitMessage(Utils.translate(Main.getInstance().getConfig().getString("QUIT-MESSAGE").replaceAll("%player%",e.getPlayer().getName())));
         FileCreator data=Main.getInstance().getData();
         Jugador jugador=new JugadorUtils().getJugador(e.getPlayer().getName());

         data.set("Jugadores."+jugador.getUUID().toString()+".nick",jugador.getNick());
         data.set("Jugadores."+jugador.getUUID().toString()+".kills",jugador.getKills());
         data.set("Jugadores."+jugador.getUUID().toString()+".muertes",jugador.getMuertes());
         data.set("Jugadores."+jugador.getUUID().toString()+".bloques",jugador.getBloques().toString());
         data.set("Jugadores."+jugador.getUUID().toString()+".largaDistancia",jugador.getLargaDistancia().toString());
         data.set("Jugadores."+jugador.getUUID().toString()+".monedas",jugador.getMonedas());
         data.set("Jugadores."+jugador.getUUID().toString()+".playtime",jugador.getPlaytime());
         data.set("Jugadores."+jugador.getUUID().toString()+".scoreboard",jugador.isScoreboard());
         data.set(("Jugadores."+jugador.getUUID().toString()+".palo"),jugador.getPaloslot());
         data.set("Jugadores."+jugador.getUUID().toString()+".ender",jugador.getPearlslot());
         data.set("Jugadores."+jugador.getUUID().toString()+".throw",jugador.getTrhowslot());
         data.set("Jugadores."+jugador.getUUID().toString()+".speed",jugador.getSpeedslot());
         data.set("Jugadores."+jugador.getUUID().toString()+".bloquesslot",jugador.getBloquesslot());
         String bloquescomprados="";
         for(int i=0;i<jugador.getBloquescomprados().size();i++){
             if(i==0){
                 bloquescomprados=jugador.getBloquescomprados().get(0).toString();
             }else{
                 bloquescomprados=bloquescomprados+";"+jugador.getBloquescomprados().get(i).toString();
             }
         }
         data.set("Jugadores."+jugador.getUUID().toString()+".bloquescomprados",bloquescomprados);
         data.save();

         Main.getInstance().getOnline().remove(jugador);
    }
    @EventHandler
    public void a(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)){
            if(e.getEntity() instanceof Player){
                Player p= (Player) e.getEntity();
                if(!(new JugadorUtils().getJugador(p.getName()).isJugando())){
                    e.setCancelled(true);
                }
            }
        }
    }
}
