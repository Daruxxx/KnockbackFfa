package me.darux.kbffa.Item;

import me.darux.kbffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnderPearlManager implements Listener {
    private List<String> cooldown=new ArrayList<>();

    @EventHandler
    public void onTrhow(ProjectileLaunchEvent e){
        if(e.getEntity().getType().equals(EntityType.ENDER_PEARL)){
            Player p=(Player) e.getEntity().getShooter();

            if(cooldown.contains(p.getName())){
                p.getItemInHand().setAmount(p.getItemInHand().getAmount()+1);
                e.setCancelled(true);
            }else{
                p.getItemInHand().setAmount(1);
                cooldown.add(p.getName());
                CooldownManager(p);
            }
        }
    }
    public void CooldownManager(Player p){
        final int[] cuenta = {0};
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                cuenta[0]++;
                if(cuenta[0]==Main.getInstance().getConfig().getInt("COOLDOWNS.ENDERPEARL")){
                    cooldown.remove(p.getName());
                }else if(cuenta[0]<Main.getInstance().getConfig().getInt("COOLDOWNS.ENDERPEARL")){
                    for(int i=0;i<10;i++){
                        if(p.getInventory().getItem(i)!=null && p.getInventory().getItem(i).getType().equals(Material.ENDER_PEARL)){
                            p.getInventory().setItem(i,new ItemStack(Material.ENDER_PEARL,Main.getInstance().getConfig().getInt("COOLDOWNS.ENDERPEARL")-cuenta[0]));
                        }
                    }
                }
            }
        },0,20);
    }

}
