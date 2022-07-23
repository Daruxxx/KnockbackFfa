package me.darux.kbffa.Scoreboard;

import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.File.FileCreator;
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
                    actualizarScoreboard(player,config);
                }
            }
        },0,plugin.getConfig().getInt("SCOREBOARD.ticks"));
    }

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
    }
}
