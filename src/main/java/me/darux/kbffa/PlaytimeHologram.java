package me.darux.kbffa;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class PlaytimeHologram {
    public static void run(){

        List<String[]> playtimes=new ArrayList<>();
        for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){
            String nombre=Main.getInstance().getData().getString("Jugadores."+key+".nick");
            int playtime=Main.getInstance().getData().getInt("Jugadores."+key+".playtime");
            playtimes.add(new String[]{playtime+"",nombre});
        }
        List<String[]> topplaytime= Utils.kills(playtimes);


        for(int i=0;i<5;i++){
            int c=i+1;

                int finalI = i;
                if(finalI>=topplaytime.size()){
                    while(c<6){
                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_"+c+"_playtime%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return "0s";
                            }
                        });
                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_+"+c+"name%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return "nadie";
                            }
                        });}
                }
                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + c + "_playtime%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return TimeManager.timeformat(Integer.parseInt(topplaytime.get(finalI)[0]));
                    }
                });


                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + c + "_name%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return topplaytime.get(finalI)[1];
                    }
                });
            }
        }
    }

