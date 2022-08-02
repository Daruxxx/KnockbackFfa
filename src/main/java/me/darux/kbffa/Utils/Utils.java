package me.darux.kbffa.Utils;

import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
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

    public static List<String[]> topplaytime(){
        List<String[]> playtimes=new ArrayList<>();
        if(Main.getInstance().getData().getConfigurationSection("Jugadores") !=null){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                String nombre=Main.getInstance().getData().getString("Jugadores."+key+".nick");
                int playtime=Main.getInstance().getData().getInt("Jugadores."+key+".playtime");
                playtimes.add(new String[]{playtime+"",nombre});
            }
        }



        List<Integer> lista=new ArrayList<>();
        for(String[] a: playtimes){
            lista.add(Integer.parseInt(a[0]));
        }
        Collections.sort(lista);
        Collections.reverse(lista);

        List<String[]> topplaytimes=new ArrayList<>();
        for(Integer a: lista){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                if(Main.getInstance().getData().getInt("Jugadores."+key+".playtime")==a){
                    topplaytimes.add(new String[]{a+"",Main.getInstance().getData().getString("Jugadores."+key+".nick")});
                }
            }
        }
        return topplaytimes;
    }


    public static List<String[]> topkills(){
        List<String[]> playtimes=new ArrayList<>();
        if(Main.getInstance().getData().getConfigurationSection("Jugadores") !=null){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                String nombre=Main.getInstance().getData().getString("Jugadores."+key+".nick");
                int playtime=Main.getInstance().getData().getInt("Jugadores."+key+".kills");
                playtimes.add(new String[]{playtime+"",nombre});
            }
        }



        List<Integer> lista=new ArrayList<>();
        for(String[] a: playtimes){
            lista.add(Integer.parseInt(a[0]));
        }
        Collections.sort(lista);
        Collections.reverse(lista);

        List<String[]> topplaytimes=new ArrayList<>();
        for(Integer a: lista){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                if(Main.getInstance().getData().getInt("Jugadores."+key+".kills")==a){
                    topplaytimes.add(new String[]{a+"",Main.getInstance().getData().getString("Jugadores."+key+".nick")});
                }
            }
        }
        return topplaytimes;
    }



    public static List<String[]> topmuertes(){
        List<String[]> playtimes=new ArrayList<>();
        if(Main.getInstance().getData().getConfigurationSection("Jugadores") !=null){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                String nombre=Main.getInstance().getData().getString("Jugadores."+key+".nick");
                int playtime=Main.getInstance().getData().getInt("Jugadores."+key+".muertes");
                playtimes.add(new String[]{playtime+"",nombre});
            }
        }



        List<Integer> lista=new ArrayList<>();
        for(String[] a: playtimes){
            lista.add(Integer.parseInt(a[0]));
        }
        Collections.sort(lista);
        Collections.reverse(lista);

        List<String[]> topplaytimes=new ArrayList<>();
        for(Integer a: lista){
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
                if(Main.getInstance().getData().getInt("Jugadores."+key+".muertes")==a){
                    topplaytimes.add(new String[]{a+"",Main.getInstance().getData().getString("Jugadores."+key+".nick")});
                }
            }
        }
        return topplaytimes;
    }
}
