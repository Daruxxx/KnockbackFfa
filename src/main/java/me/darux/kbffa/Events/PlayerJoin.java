package me.darux.kbffa.Events;

import me.darux.kbffa.Arena.Arena;
import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.List;

public class PlayerJoin implements Listener {

    @EventHandler
    public void MagicSword1(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) || e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.WORKBENCH)) {
                return;
            }

            if (e.getItem() != null && e.getItem().getType() == Material.SHEARS) {
                ItemStack item = e.getItem();
                Vector v;

                    if(new JugadorUtils().getJugador(e.getPlayer().getName()).getTntrestante()<1) return;


                    v = p.getLocation().getDirection();
                    TNTPrimed tnt = (TNTPrimed)p.getWorld().spawn(p.getEyeLocation(), TNTPrimed.class);
                    tnt.setVelocity(v.multiply(1.3D));
                    tnt.setFuseTicks(15);
                ItemMeta meta=e.getPlayer().getItemInHand().getItemMeta();
                new JugadorUtils().getJugador(e.getPlayer().getName()).setTntrestante(new JugadorUtils().getJugador(e.getPlayer().getName()).getTntrestante()-1);
                meta.setDisplayName(Utils.translate("&cLanza tnt &8(&e"+new JugadorUtils().getJugador(e.getPlayer().getName()).getTntrestante() +"&8)"));
                e.getPlayer().getItemInHand().setItemMeta(meta);

            }
        }

    }
    @EventHandler
    public void a(EntityExplodeEvent e){
        e.blockList().clear();
    }




}
