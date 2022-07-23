package me.darux.kbffa.Item;

import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class SpeedManager implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) ){
            if(!(e.getPlayer().getItemInHand().getType().equals(Material.FEATHER))) return;

            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,60,2));
            e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
            cooldown(e.getPlayer());


        }
    }

    private void cooldown(Player p){
        final int[] cuenta = {0};

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                cuenta[0]++;
                if(cuenta[0]==Main.getInstance().getConfig().getInt("COOLDOWNS.SPEED")){
                    if(new JugadorUtils().getJugador(p.getName()).isJugando()){
                        p.getInventory().setItem(new JugadorUtils().getJugador(p.getName()).getSpeedslot(),ItemUtils.getSpeed());
                    }

                }else if(cuenta[0]<Main.getInstance().getConfig().getInt("COOLDOWNS.SPEED")){
                    int amount=Main.getInstance().getConfig().getInt("COOLDOWNS.SPEED")-cuenta[0];
                    if(new JugadorUtils().getJugador(p.getName()).isJugando()){
                        p.getInventory().setItem(new JugadorUtils().getJugador(p.getName()).getSpeedslot(),ItemUtils.getSpeedCooldown(amount));
                    }

                }
            }
        },0,20);
    }
}
