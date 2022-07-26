package me.darux.kbffa.Rewards;

import me.darux.kbffa.Events.PlayerJoin;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sun.net.www.protocol.jar.JarURLConnection;

public class RankRewardManager implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(!(Main.getInstance().getData().getStringList("reclamados").contains(e.getPlayer().getUniqueId().toString()))){
            for(String key : Main.getInstance().getConfig().getConfigurationSection("REWARDS").getKeys(false)){
                if(e.getPlayer().hasPermission(key)){
                    int dinero=Main.getInstance().getConfig().getInt("REWARDS."+key);
                    Utils.añadirmonedas(new JugadorUtils().getJugador(e.getPlayer().getName()),dinero);
                }
            }
        }
    }
}
