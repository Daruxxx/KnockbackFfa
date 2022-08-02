package me.darux.kbffa.HolographicDisplaysApi;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import me.darux.kbffa.Main;
import me.darux.kbffa.TimeManager;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class PlaytimeHologram {
    public static void run(){
List<String[]> playtimes=Utils.topplaytime();



        if(playtimes.size()<5){
            if(playtimes.size()>=1){
                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 1 + "_name%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return playtimes.get(0)[1];
                    }
                });

                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 1 + "_playtime%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return TimeManager.timeformat(Integer.parseInt(playtimes.get(0)[0]));
                    }
                });


                if(playtimes.size()>=2){
                    HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 2 + "_name%", 20, new PlaceholderReplacer() {
                        @Override
                        public String update() {
                            return playtimes.get(1)[1];
                        }
                    });

                    HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 2 + "_playtime%", 20, new PlaceholderReplacer() {
                        @Override
                        public String update() {
                            return TimeManager.timeformat(Integer.parseInt(playtimes.get(1)[0]));
                        }
                    });

                    if(playtimes.size()>=1){
                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 3 + "_name%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {

                                    return playtimes.get(2)[1];


                            }
                        });

                        HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 3 + "_playtime%", 20, new PlaceholderReplacer() {
                            @Override
                            public String update() {
                                return TimeManager.timeformat(Integer.parseInt(playtimes.get(2)[0]));
                            }
                        });

                        if(playtimes.size()>=4){
                            HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 1 + "_name%", 20, new PlaceholderReplacer() {
                                @Override
                                public String update() {
                                    return playtimes.get(3)[1];
                                }
                            });

                            HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + 4 + "_playtime%", 20, new PlaceholderReplacer() {
                                @Override
                                public String update() {
                                    return TimeManager.timeformat(Integer.parseInt(playtimes.get(3)[0]));
                                }
                            });

                        }

                    }
                }

            }
            for(int i=playtimes.size()+1;i<6;i++){
                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + i + "_name%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return "nadie";
                    }
                });

                HologramsAPI.registerPlaceholder(Main.getInstance(), "%playtime_" + i + "_playtime%", 20, new PlaceholderReplacer() {
                    @Override
                    public String update() {
                        return "0s";
                    }
                });
            }
            return;
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

