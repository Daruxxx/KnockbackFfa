package me.darux.kbffa.Jugador;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.darux.kbffa.Enums.LargaDistancia;
import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Item.ItemUtils;
import me.darux.kbffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class JugadorUtils {

    public void crearJugador(Player p){
        Jugador jugador=new Jugador(p.getUniqueId().toString(),p.getName());
        Main.getInstance().getOnline().add(jugador);
    }

    public void cargarJugador(Player p){
        FileCreator data=Main.getInstance().getData();
        Jugador jugador=new Jugador(p.getUniqueId()+"",p.getName());
        jugador.setKills(data.getInt("Jugadores."+jugador.getUUID().toString()+".kills"));
        jugador.setMuertes(data.getInt("Jugadores."+jugador.getUUID().toString()+".muertes"));
        jugador.setBloques(Material.matchMaterial(data.getString("Jugadores."+jugador.getUUID().toString()+".bloques")));
        jugador.setLargaDistancia(LargaDistancia.valueOf(data.getString("Jugadores."+jugador.getUUID().toString()+".largaDistancia")));
        jugador.setMonedas(data.getInt("Jugadores."+jugador.getUUID().toString()+".monedas"));
        jugador.setPlaytime(data.getInt("Jugadores."+jugador.getUUID().toString()+".playtime"));
        jugador.setScoreboard(data.getBoolean("Jugadores."+jugador.getUUID().toString()+".scoreboard"));

        jugador.setPaloslot(data.getInt("Jugadores."+jugador.getUUID().toString()+".palo"));
        jugador.setPearlslot(data.getInt("Jugadores."+jugador.getUUID().toString()+".ender"));
        jugador.setTrhowslot(data.getInt("Jugadores."+jugador.getUUID().toString()+".throw"));
        jugador.setSpeedslot(data.getInt("Jugadores."+jugador.getUUID().toString()+".speed"));
        jugador.setBloquesslot(data.getInt("Jugadores."+jugador.getUUID().toString()+".bloquesslot"));

        String[] materiales=data.getString("Jugadores."+jugador.getUUID()+".bloquescomprados").split(";");
        List<Material> materials=new ArrayList<>();
        for(String a : materiales){
            materials.add(Material.valueOf(a));
        }
        jugador.setBloquescomprados(materials);
        jugador.setNick(p.getName());

        Main.getInstance().getOnline().add(jugador);
    }

    public Jugador getJugador(String nick){
        for(Jugador jugador:Main.getInstance().getOnline()){
            if(jugador.getNick().equals(nick)){
                return jugador;
            }
        }
        return null;
    }

    public static void comenzarJugar(Player p){
        Jugador jugador=new JugadorUtils().getJugador(p.getName());
        jugador.setJugando(true);
        p.getInventory().clear();
        p.getInventory().setItem(jugador.getPaloslot(), ItemUtils.getStick());
        p.getInventory().setItem(jugador.getBloquesslot(),new ItemStack(jugador.getBloques(),32));
        p.getInventory().setItem(jugador.getPearlslot(),new ItemStack(Material.ENDER_PEARL,1));
        p.getInventory().setItem(jugador.getSpeedslot(),ItemUtils.getSpeed());
        p.getInventory().setItem(30,new ItemStack(Material.ARROW,64));
        p.getInventory().setItem(jugador.getTrhowslot(),ItemUtils.largadistance(jugador.getLargaDistancia()));


    }
}
