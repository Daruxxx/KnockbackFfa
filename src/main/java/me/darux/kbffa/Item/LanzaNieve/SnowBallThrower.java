package me.darux.kbffa.Item.LanzaNieve;

import me.darux.kbffa.Item.ItemUtils;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SnowBallThrower implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
           if(e.getPlayer().getItemInHand().hasItemMeta() && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(
                   Utils.translate("&bLanza nieves")
            )){
               Player p = e.getPlayer();
               if(new JugadorUtils().getJugador(p.getName()).getBolasrestantes()==0) return;
               new JugadorUtils().getJugador(p.getName()).setBolasrestantes(new JugadorUtils().getJugador(p.getName()).getBolasrestantes()-1);
               Snowball ball = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
               ball.setShooter(p);
               ItemStack item=e.getPlayer().getItemInHand();
               ItemMeta meta=item.getItemMeta();
               meta.setDisplayName(Utils.translate("&bLanza nieves &8(&e"+new JugadorUtils().getJugador(p.getName()).getBolasrestantes()+"&8)"));
                item.setItemMeta(meta);
                e.getPlayer().getItemInHand().setItemMeta(meta);
               ball.setVelocity(p.getEyeLocation().getDirection().multiply(1.5));
           }
        }
    }


    public void run(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Jugador jugador : Main.getInstance().getOnline()){
                    if(jugador.getBolasrestantes()<15){
                        jugador.setBolasrestantes(jugador.getBolasrestantes()+1);
                        for(int i=0;i<jugador.getPlayer().getInventory()
                        .getSize();i++){
                            if(jugador.getPlayer().getInventory().getItem(i)!=null){
                                if(jugador.getPlayer().getInventory().getItem(i).getType().equals(Material.DIAMOND_HOE)){
                                ItemMeta meta=jugador.getPlayer().getInventory().getItem(i).getItemMeta();
                                meta.setDisplayName(Utils.translate("&bLanza nieves &8(&e"+jugador.getBolasrestantes()+"&8)"));
                                jugador.getPlayer().getInventory().getItem(i).setItemMeta(meta);
                                }
                            }
                        }
                    }
                }
            }
        },20,20);
    }
}
