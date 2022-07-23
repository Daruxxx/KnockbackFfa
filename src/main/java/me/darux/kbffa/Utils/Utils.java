package me.darux.kbffa.Utils;

import me.darux.kbffa.Jugador.Jugador;
import org.bukkit.ChatColor;

public class Utils {

    public static String translate(String a){
        return ChatColor.translateAlternateColorCodes('&',a);
    }

    public static void añadirmonedas(Jugador jugador,int monedas){
        jugador.setMonedas(jugador.getMonedas()+monedas);
    }

    public static void quitar(Jugador jugador,int monedas){
        jugador.setMonedas(jugador.getMonedas()-monedas);
    }

    public static int getMonedas(Jugador jugador){
        return jugador.getMonedas();
    }
}
