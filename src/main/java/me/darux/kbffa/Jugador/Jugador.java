package me.darux.kbffa.Jugador;


import me.darux.kbffa.Enums.LargaDistancia;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Jugador {


    private int kills;
    private String UUID;
    private String nick;
    private int muertes;
    private Material bloques;
    private LargaDistancia largaDistancia;
    private int racha;
    private int monedas;
    private int playtime;
    private boolean scoreboard;
    private boolean jugando;

    private int paloslot;
    private int bloquesslot;
    private int pearlslot;
    private int trhowslot;
    private int speedslot;
    private int bolasrestantes;
    private List<Material> bloquescomprados;
    private int tntrestante;
    private int bowcooldown;


    public Jugador(String UUID, String nick) {
        this.kills=0;
        this.muertes=0;
        this.bloques= Material.STONE;
        this.largaDistancia=LargaDistancia.ARCO;
        this.racha=0;
        this.monedas=0;
        this.scoreboard=true;
        this.jugando=false;
        this.UUID=UUID;
        this.nick=nick;
        paloslot=0;
        bloquesslot=1;
        pearlslot=8;
        speedslot=7;
        trhowslot=4;
        bloquescomprados=new ArrayList<Material>();
        bloquescomprados.add(Material.STONE);
        bolasrestantes=15;
        tntrestante=2;
        bowcooldown=0;
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public Material getBloques() {
        return bloques;
    }

    public void setBloques(Material bloques) {
        this.bloques = bloques;
    }

    public LargaDistancia getLargaDistancia() {
        return largaDistancia;
    }

    public void setLargaDistancia(LargaDistancia largaDistancia) {
        this.largaDistancia = largaDistancia;
    }

    public int getRacha() {
        return racha;
    }

    public void setRacha(int racha) {
        this.racha = racha;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    public boolean isScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(boolean scoreboard) {
        this.scoreboard = scoreboard;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    public void aumentarmuertes(){
        muertes++;
    }
    public void aumentarkills(){
        kills++;
    }

    public void aumentarracha(){
        racha++;
    }
public void aumentartiempo(){
        playtime++;
}
    public Player getPlayer(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getName().equals(nick)){
                return player;
            }
        }
   return null;
    }

    public int getPaloslot() {
        return paloslot;
    }

    public void setPaloslot(int paloslot) {
        this.paloslot = paloslot;
    }

    public int getBloquesslot() {
        return bloquesslot;
    }

    public void setBloquesslot(int bloquesslot) {
        this.bloquesslot = bloquesslot;
    }

    public int getPearlslot() {
        return pearlslot;
    }

    public void setPearlslot(int pearlslot) {
        this.pearlslot = pearlslot;
    }

    public int getTrhowslot() {
        return trhowslot;
    }

    public void setTrhowslot(int trhowslot) {
        this.trhowslot = trhowslot;
    }

    public int getSpeedslot() {
        return speedslot;
    }

    public void setSpeedslot(int speedslot) {
        this.speedslot = speedslot;
    }

    public void sumarmonedas(int cuantas){
        monedas=monedas+cuantas;
    }

    public List<Material> getBloquescomprados() {
        return bloquescomprados;
    }

    public void setBloquescomprados(List<Material> bloquescomprados) {
        this.bloquescomprados = bloquescomprados;
    }

    public boolean tienebloque(Material block){
        for(Material material : bloquescomprados){
            if(block.equals(material)){
                return true;
            }
        }
        return false;
    }

    public int getBolasrestantes() {
        return bolasrestantes;
    }

    public void setBolasrestantes(int bolasrestantes) {
        this.bolasrestantes = bolasrestantes;
    }

    public int getTntrestante() {
        return tntrestante;
    }

    public void setTntrestante(int tntrestante) {
        this.tntrestante = tntrestante;
    }

    public int getBowcooldown() {
        return bowcooldown;
    }

    public void setBowcooldown(int bowcooldown) {
        this.bowcooldown = bowcooldown;
    }
}
