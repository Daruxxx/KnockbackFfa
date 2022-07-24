package me.darux.kbffa.Trails;

import me.darux.kbffa.Main;
import org.bukkit.entity.Player;
import org.inventivetalent.particle.ParticleEffect;

public class ParticleUtils {

    public static void setParticle(Player p, ParticleEffect particleEffect){
        if(particleEffect==null){
            Main.getInstance().getData().set("Jugadores."+p.getUniqueId()+".particle","none");
        }else{
            Main.getInstance().getData().set("Jugadores."+p.getUniqueId()+".particle",particleEffect.toString());
        }

        Main.getInstance().getData().save();
    }

    public static ParticleEffect getParticle(Player p){
        if(!(Main.getInstance().getData().contains("Jugadores."+p.getUniqueId()+".particle"))) return null;
        if(Main.getInstance().getData().getString("Jugadores."+p.getUniqueId()+".particle").equals("none")) return null;
        if(Main.getInstance().getData().contains("Jugadores."+p.getUniqueId()+".particle")){
            return ParticleEffect.valueOf(Main.getInstance().getData().getString("Jugadores."+p.getUniqueId()+".particle"));
        }else return null;


    }

    public static int getPrice(ParticleEffect particleEffect){
        return Main.getInstance().getConfig().getInt("Particles.prices."+particleEffect.toString());
    }

}
