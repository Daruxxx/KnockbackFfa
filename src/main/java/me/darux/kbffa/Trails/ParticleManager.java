package me.darux.kbffa.Trails;

import me.darux.kbffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.inventivetalent.particle.ParticleEffect;

import java.util.Locale;

public class ParticleManager {
    public static void run(){
        if(Main.getInstance().getServer().getPluginManager().getPlugin("ParticleLIB") ==null){
            return;
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){

                    try{
                        if(ParticleUtils.getParticle(p) != null && !(ParticleUtils.getParticle(p).toString().equals("none"))){
                            Location location=p.getEyeLocation();
                            location.setY(location.getY()+1);
                            ParticleUtils.getParticle(p).send(Bukkit.getOnlinePlayers(), location, 0, 0, 0, 0, 1);


                        }
                    }catch (IllegalArgumentException e){
                        return;
                    }


                }
            }
        },0,3);
    }
}
