package me.darux.kbffa.Utils;

import me.darux.kbffa.Jugador.Jugador;
import org.bukkit.ChatColor;

import java.util.List;

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

    public static List<String[]> kills(List<String[]> lista){
        for(int i=0;i<lista.size();i++){
            String[] persona=lista.get(i);
            int ikills=Integer.parseInt(persona[0]);
            for(int c=0;c<lista.size();c++){
                if(c<i){
                    String[] cpersona=lista.get(c);
                    int ckills= Integer.parseInt(cpersona[0]);
                    if(ikills>ckills){
                        lista.set(i,cpersona);
                        lista.set(c,persona);
                    }
                }
            }


        }
        return lista;
    }
}
