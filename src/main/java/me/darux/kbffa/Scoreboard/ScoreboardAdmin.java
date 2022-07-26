package me.darux.kbffa.Scoreboard;

import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;

import java.util.List;
import java.util.Objects;

public class ScoreboardAdmin {
    int taskid;

    public void crearScore(){
        Main plugin=Main.getInstance();
        BukkitScheduler scheduler= Bukkit.getServer().getScheduler();
        taskid=scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                FileCreator config= plugin.getConfig();
                for(Player player : Bukkit.getOnlinePlayers()){
                  //  actualizarScoreboard(player,config);
                    showScoreboard(player);
                }
            }
        },0,plugin.getConfig().getInt("SCOREBOARD.ticks"));
    }


/////////////////////
public static String getEntryFromScore(Objective o, int score) {
    if(o == null) return null;
    if(!hasScoreTaken(o, score)) return null;
    for (String s : o.getScoreboard().getEntries()) {
        if(o.getScore(s).getScore() == score) return o.getScore(s).getEntry();
    }
    return null;
}

    public static boolean hasScoreTaken(Objective o, int score) {
        for (String s : o.getScoreboard().getEntries()) {
            if(o.getScore(s).getScore() == score) return true;
        }
        return false;
    }

    public static void replaceScore(Objective o, int score, String name) {
        if(hasScoreTaken(o, score)) {
            if(getEntryFromScore(o, score).equalsIgnoreCase(name)) return;
            if(!(getEntryFromScore(o, score).equalsIgnoreCase(name))) o.getScoreboard().resetScores(getEntryFromScore(o, score));
        }
        o.getScore(name).setScore(score);
    }


    public void showScoreboard(Player p) {
        if(new JugadorUtils().getJugador(p.getName()).isScoreboard()){
            if(p.getScoreboard().equals(Main.getInstance().getServer().getScoreboardManager().getMainScoreboard())) p.setScoreboard(Main.getInstance().getServer().getScoreboardManager().getNewScoreboard()); //Per-player scoreboard, not necessary if all the same data, but we're personalizing the displayname and all
            Scoreboard score = p.getScoreboard(); //Personalized scoreboard
            Objective objective = score.getObjective(p.getName()) == null ? score.registerNewObjective(p.getName(), "dummy") : score.getObjective(p.getName()); //Per-player objectives, even though it doesn't matter what it's called since we're using per-player scoreboards.
            String displayName = Main.getInstance().getConfig().getString("SCOREBOARD.title");
            List<String> lineas=Main.getInstance().getConfig().getStringList("SCOREBOARD.lines");
            objective.setDisplayName(displayName);
            String arena="";
            String tiempo="";
            if(ArenaUtils.getEnabledArena() !=null){
                arena=Objects.requireNonNull(ArenaUtils.getEnabledArena()).getNombre();
                tiempo=TimeManager.timeformat(600-ArenaUtils.getEnabledArena().getTiempoactiva());

            }

            for(int i=0;i<lineas.size();i++) {

                replaceScore(objective,lineas.size()-i, lineas.get(i)

                        .replaceAll("%kills%", new JugadorUtils().getJugador(p.getName()).getKills() + "")
                        .replaceAll("%racha%", new JugadorUtils().getJugador(p.getName()).getRacha() + "")
                        .replaceAll("%muertes%", new JugadorUtils().getJugador(p.getName()).getMuertes() + "")
                        .replaceAll("%monedas%", new JugadorUtils().getJugador(p.getName()).getMonedas() + "")
                        .replaceAll("%online%", Bukkit.getOnlinePlayers().size() + "")
                        .replaceAll("%mapa%", arena)
                        .replaceAll("%tiempo%", tiempo));
            }



            if(objective.getDisplaySlot() != DisplaySlot.SIDEBAR) objective.setDisplaySlot(DisplaySlot.SIDEBAR); //Vital functionality
            p.setScoreboard(score);
        }else{
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }


//Vital functionality


    //////////////////






















/*
    private void actualizarScoreboard(Player jugador, FileCreator config){
        ScoreboardManager manager=Bukkit.getScoreboardManager();
        Scoreboard scoreboard=manager.getNewScoreboard();
        Objective objetivo=scoreboard.registerNewObjective("kbffa","dummy");
        objetivo.setDisplaySlot(DisplaySlot.SIDEBAR);
        objetivo.setDisplayName(config.getString("SCOREBOARD.title"));
        List<String> lineas=config.getStringList("SCOREBOARD.lines");
        String arena="";
        String tiempo="";
        if(ArenaUtils.getEnabledArena() !=null){
            arena=Objects.requireNonNull(ArenaUtils.getEnabledArena()).getNombre();
            tiempo=TimeManager.timeformat(600-ArenaUtils.getEnabledArena().getTiempoactiva());

        }
        for(int i=0;i<lineas.size();i++) {
            Score score=objetivo.getScore(lineas.get(i)

                    .replaceAll("%kills%", new JugadorUtils().getJugador(jugador.getName()).getKills()+"")
                    .replaceAll("%racha%", new JugadorUtils().getJugador(jugador.getName()).getRacha()+"")
                    .replaceAll("%muertes%", new JugadorUtils().getJugador(jugador.getName()).getMuertes()+"")
                    .replaceAll("%monedas%", new JugadorUtils().getJugador(jugador.getName()).getMonedas()+"")
                    .replaceAll("%online%", Bukkit.getOnlinePlayers().size()+"")
                    .replaceAll("%mapa%", arena)
                    .replaceAll("%tiempo%",tiempo )

            );
            score.setScore(lineas.size()-i);
        }
        jugador.setScoreboard(scoreboard);
 */   }
}
