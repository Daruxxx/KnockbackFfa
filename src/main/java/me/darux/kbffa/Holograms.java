package me.darux.kbffa;

import com.gmail.filoghost.holograms.api.HolographicDisplaysAPI;
import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Holograms {
    public static void setup() {
        if (Main.getInstance().getServer().getPluginManager().getPlugin("HolographicDisplays") != null) {
       /*     List<String[]> killssinorden=new ArrayList<>();
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){

                String nick=Main.getInstance().getData().getString("Jugadores."+key+".nick");
                int kills=Main.getInstance().getData().getInt("Jugadores."+key+".kills");
                killssinorden.add(new String[]{kills+"",nick});



            }



            for(int i=0;i<6;i++){
              try {


                  String nombre = killssinorden.get(i)[1];
                  int kills = Integer.parseInt(killssinorden.get(i)[0]);

                  HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + i + "_name%", 20, new PlaceholderReplacer() {
                      @Override
                      public String update() {
                          return nombre;

                      }
                  });

                  HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + i + "_kills%", 20, new PlaceholderReplacer() {
                      @Override
                      public String update() {
                          return kills + "";

                      }
                  });
              }catch (IndexOutOfBoundsException e){
                  HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + i + "_name%", 20, new PlaceholderReplacer() {
                      @Override
                      public String update() {
                          return "nadie";

                      }
                  });
                  HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + i + "_kills%", 20, new PlaceholderReplacer() {
                      @Override
                      public String update() {
                          return "0";

                      }
                  });
              }

            }
        }

*/

            List<String[]> killssinorden=new ArrayList<>();
            for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(false)){

                String nick=Main.getInstance().getData().getString("Jugadores."+key+".nick");
                int kills=Main.getInstance().getData().getInt("Jugadores."+key+".kills");
                killssinorden.add(new String[]{kills+"",nick});



            }

            List<String[]> topkills=Utils.kills(killssinorden);


            for(int i=0;i<=5;i++){

                try{
                    String a=topkills.get(i)[1];;
                    int kills=Integer.parseInt(topkills.get(i)[0]);
                    int c=i+1;
                    HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + c + "_name%", 20, new PlaceholderReplacer() {
                        @Override
                        public String update() {
                            return a;
                        }
                    });

                    HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + c + "_kills%", 20, new PlaceholderReplacer() {
                        @Override
                        public String update() {
                            return kills+"";
                        }
                    });
                }catch (IndexOutOfBoundsException e){
                    int c=i+1;
                    while(c<6){
                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + c + "_name%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return "nadie";
                            }
                        });

                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topkills_" + c + "_kills%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return 0+"";
                            }
                        });
                        c++;
                    }

                    return;
                }

            }














         killssinorden = new ArrayList<>();
            for (String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(true)) {
                if (key.endsWith(".muertes")) {
                    String nick = Main.getInstance().getData().getString("Jugadores." + key.replaceAll(".muertes", ".nick"));
                    killssinorden.add(new String[]{Main.getInstance().getData().getInt("Jugadores." + key) + "", nick});

                }
            }
 topkills = Utils.kills(killssinorden);

            for (int i = 0; i < 6; i++) {
                try{
                String nombre = killssinorden.get(i)[1];
                int kills = Integer.parseInt(killssinorden.get(i)[0]);
                int c=i+1;
                HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topdeaths_" + c + "_name%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return nombre;

                    }
                });

                HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topdeaths_" + c + "_deaths%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return kills + "";

                    }
                });
            }catch (IndexOutOfBoundsException e){

                    int c=i+1;
                    while(c<6){
                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topdeaths_" + c + "_name%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return "nadie";
                            }
                        });

                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%kbffa_topdeaths_" + c + "_deaths%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return 0+"";
                            }
                        });
                        c++;
                    }

                    return;
                }
                }
            }
        }
    }
