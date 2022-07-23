package me.darux.kbffa.Events;

import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class StatiscManager implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            new JugadorUtils().getJugador(e.getEntity().getName()).aumentarmuertes();
            new JugadorUtils().getJugador(e.getEntity().getName()).setRacha(0);
            new JugadorUtils().getJugador(e.getEntity().getName()).sumarmonedas(Main.getInstance().getConfig().getInt("Monedas.muerte"));
            e.setDroppedExp(0);
            e.getDrops().clear();


            if (e.getEntity().getKiller() != null) {
                new JugadorUtils().getJugador(e.getEntity().getKiller().getName()).aumentarkills();
                new JugadorUtils().getJugador(e.getEntity().getKiller().getName()).aumentarracha();
                new JugadorUtils().getJugador(e.getEntity().getKiller().getName()).sumarmonedas(Main.getInstance().getConfig().getInt("Monedas.kill"));
                for(String key: Main.getInstance().getConfig().getConfigurationSection("Monedas.rachas").getKeys(false)){
                    int racha=Integer.valueOf(key);
                    if(racha==new JugadorUtils().getJugador(e.getEntity().getKiller().getName()).getRacha()){
                        new JugadorUtils().getJugador(e.getEntity().getKiller().getName()).sumarmonedas(
                                Main.getInstance().getConfig().getInt("Monedas.rachas."+key)
                        );
                    }
                }
                e.setDeathMessage(Utils.translate("&6" + e.getEntity().getKiller().getName() + "&a ha matado a &6" + e.getEntity().getName()));
            } else e.setDeathMessage(Utils.translate("&6" + e.getEntity().getName() + "&a ha muerto"));

        }


    }



    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getPlayer().getLocation().getY()>0 && e.getTo().getY()<=0){
            e.getPlayer().setHealth(0);


        }
    }
}
