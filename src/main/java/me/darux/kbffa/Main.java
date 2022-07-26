package me.darux.kbffa;

import me.darux.kbffa.Arena.Arena;
import me.darux.kbffa.Arena.ArenaCMD;
import me.darux.kbffa.Arena.ArenaUtils;
import me.darux.kbffa.Arena.KbFfaCommand;
import me.darux.kbffa.Commands.Playtime;
import me.darux.kbffa.Commands.SuicideCmd;
import me.darux.kbffa.Events.EventCanceler;
import me.darux.kbffa.Events.PlayerJoin;
import me.darux.kbffa.Events.SpawnTeleportListener;
import me.darux.kbffa.Events.StatiscManager;
import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Item.EnderPearlManager;
import me.darux.kbffa.Item.LanzaNieve.SnowBallThrower;
import me.darux.kbffa.Item.SpawnItemsManager;
import me.darux.kbffa.Item.SpeedManager;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorJoin;
import me.darux.kbffa.Rewards.RankRewardManager;
import me.darux.kbffa.Scoreboard.ScoreboardAdmin;
import me.darux.kbffa.Trails.ParticleManager;

import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private FileCreator data;
    private FileCreator config;
    private List<Jugador> online=new ArrayList<>();
    private static Main instance;
    private List<Arena> arenas=new ArrayList<>();
    List<Block> bloques=new ArrayList<>();



    @Override
    public void onEnable() {

        instance=this;
        registerFiles();
        registerEvents();
        registerCommands();
        registerRunnables();
        Holograms.setup();
        ParticleManager.run();

        ArenaUtils.cargarArenas(data);
        ScoreboardAdmin scoreboardAdmin=new ScoreboardAdmin();
        scoreboardAdmin.crearScore();
        TimeManager timeManager=new TimeManager();
        timeManager.tnt();
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()){
            player.kickPlayer(Utils.translate("&cReiniciando arena"));
        }
        for(Jugador jugador : online){
            data.set("Jugadores."+jugador.getUUID().toString()+".nick",jugador.getNick());
            data.set("Jugadores."+jugador.getUUID().toString()+".kills",jugador.getKills());
            data.set("Jugadores."+jugador.getUUID().toString()+".muertes",jugador.getMuertes());
            data.set("Jugadores."+jugador.getUUID().toString()+".bloques",jugador.getBloques().toString());
            data.set("Jugadores."+jugador.getUUID().toString()+".largaDistancia",jugador.getLargaDistancia().toString());
            data.set("Jugadores."+jugador.getUUID().toString()+".monedas",jugador.getMonedas());
            data.set("Jugadores."+jugador.getUUID().toString()+".playtime",jugador.getPlaytime());
            data.set("Jugadores."+jugador.getUUID().toString()+".scoreboard",jugador.isScoreboard());
            data.set(("Jugadores."+jugador.getUUID().toString()+".palo"),jugador.getPaloslot());
            data.set("Jugadores."+jugador.getUUID().toString()+".ender",jugador.getPearlslot());
            data.set("Jugadores."+jugador.getUUID().toString()+".throw",jugador.getTrhowslot());
            data.set("Jugadores."+jugador.getUUID().toString()+".speed",jugador.getSpeedslot());
            data.set("Jugadores."+jugador.getUUID().toString()+".bloquesslot",jugador.getBloquesslot());
            String bloquescomprados="";
            for(int i=0;i<jugador.getBloquescomprados().size();i++){
                if(i==0){
                    bloquescomprados=jugador.getBloquescomprados().get(0).toString();
                }else{
                    bloquescomprados=bloquescomprados+";"+jugador.getBloquescomprados().get(i).toString();
                }
            }
            data.set("Jugadores."+jugador.getUUID().toString()+".bloquescomprados",bloquescomprados);


            for(Block block : bloques){
                block.setType(Material.AIR);
            }
        }


        for(Arena arena : arenas){
            data.set("Arenas."+arena.getNombre()+".spawn.x",arena.getSpawn().getX());
            data.set("Arenas."+arena.getNombre()+".spawn.y",arena.getSpawn().getY());
            data.set("Arenas."+arena.getNombre()+".spawn.z",arena.getSpawn().getZ());
            data.set("Arenas."+arena.getNombre()+".spawn.world",arena.getSpawn().getWorld().getName());
        }
        data.save();
    }

    private void registerEvents(){
        SnowBallThrower snowBallThrower=new SnowBallThrower();
        snowBallThrower.run();
        this.getServer().getPluginManager().registerEvents(new JugadorJoin(),this);
        this.getServer().getPluginManager().registerEvents(new SpawnTeleportListener(),this);
        this.getServer().getPluginManager().registerEvents(new StatiscManager(),this);
        this.getServer().getPluginManager().registerEvents(new EventCanceler(),this);
        this.getServer().getPluginManager().registerEvents(new EnderPearlManager(),this);
        this.getServer().getPluginManager().registerEvents(new SpeedManager(),this);
        this.getServer().getPluginManager().registerEvents(new SpawnItemsManager(),this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        this.getServer().getPluginManager().registerEvents(new SnowBallThrower(),this);
        this.getServer().getPluginManager().registerEvents(new RankRewardManager(),this);
        this.getServer().getPluginManager().registerEvents(new RankRewardManager(),this);

    }
    private void registerCommands(){
        this.getCommand("arena").setExecutor(new ArenaCMD());
    }
    private void registerFiles(){
        data=new FileCreator(this,"data");
        this.getCommand("kbffa").setExecutor(new KbFfaCommand());
        this.getCommand("suicide").setExecutor(new SuicideCmd());
        this.getCommand("playtime").setExecutor(new Playtime());
        config=new FileCreator(this,"config");
    }


    public FileCreator getData() {
        return data;
    }


    public List<Jugador> getOnline() {
        return online;
    }

    public static Main getInstance(){
        return instance;
    }

    @Override
    public FileCreator getConfig() {
        return config;
    }

    public List<Arena> getArenas() {
        return arenas;
    }
    public void registerRunnables(){
        TimeManager.run();
    }

    public List<Block> getBloques() {
        return bloques;
    }
}

