package me.darux.kbffa.Item;

import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class LanzaFuegos implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getPlayer().getItemInHand().getType().equals(Material.SHEARS)){
                int balas=new JugadorUtils().getJugador(e.getPlayer().getName()).getTntrestante();
                if(balas==0){
                    return;
                }
                new JugadorUtils().getJugador(e.getPlayer().getName()).setTntrestante(balas-1);
                ItemMeta meta=e.getPlayer().getItemInHand().getItemMeta();
                int a=balas-1;
                meta.setDisplayName(Utils.translate("&cLanza fuego &8(&e"+a+"&8)"));
                e.getPlayer().getItemInHand().setItemMeta(meta);
               Fireball entity= e.getPlayer().getLocation().getWorld().spawn(e.getPlayer().getEyeLocation(), Fireball.class);
                entity.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(1.5));
                entity.setShooter(e.getPlayer());


            }
        }
    }


    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        e.blockList().clear();
    }
    @EventHandler
    public void Damage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            if(e.getEntity() instanceof Player){
                e.setDamage(0);
            }
        }
    }

}
