package me.darux.kbffa.Arena;

import me.darux.kbffa.Jugador.Jugador;
import org.bukkit.Location;


import java.util.ArrayList;
import java.util.List;

public class Arena {

    private Location spawn;
    private boolean activa;
    private String nombre;
    private int tiempoactiva;

    public Arena(Location spawn, String nombre) {
        this.spawn = spawn;
        this.nombre = nombre;
        this.activa=false;
        this.tiempoactiva=0;
    }

    public int getTiempoactiva() {
        return tiempoactiva;
    }

    public void setTiempoactiva(int tiempoactiva) {
        this.tiempoactiva = tiempoactiva;
    }



    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void sumartiempoactiva(){
        tiempoactiva++;
    }


}
