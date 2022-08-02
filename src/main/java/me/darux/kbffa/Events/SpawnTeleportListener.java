package me.darux.kbffa.Events;

import me.darux.kbffa.Arena.Arena;
import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.Item.ItemUtils;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.TimeManager;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import javax.xml.ws.WebEndpoint;

public class SpawnTeleportListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Arena arena= ArenaUtils.getEnabledArena();
        if(arena!=null){
            e.getPlayer().teleport(arena.getSpawn());
            ItemUtils.setDefaultInv(e.getPlayer());
            new JugadorUtils().getJugador(e.getPlayer().getName()).setJugando(false);
        }else{
            if(e.getPlayer().isOp()){
                e.getPlayer().sendMessage(Utils.translate("&cHa ocurrido un error y no hay ninguna arena activa, crea una arena con /arena create"));
            }else e.getPlayer().kickPlayer(Utils.translate("&cEl servidor está en mantenimiento"));
        }
    }

@EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Arena arena= ArenaUtils.getEnabledArena();

            e.setRespawnLocation(arena.getSpawn());
            new JugadorUtils().getJugador(e.getPlayer().getName()).setJugando(false);
            ItemUtils.setDefaultInv(e.getPlayer());

    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        TimeManager.eliminarbloque(e.getBlockPlaced());
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if(e.getPlayer().getItemInHand().getAmount()==1){
            e.setCancelled(true);
            return;
        }

        if(e.getBlock().getLocation().getY()>100) e.setCancelled(true);

;    }
    @EventHandler
    public void onBlockBrea(BlockBreakEvent e){
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        e.setCancelled(true);
        if(e.getBlock().getLocation().getY()>100) e.setCancelled(true);
    }
    @EventHandler
    public void onGunder(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

}
