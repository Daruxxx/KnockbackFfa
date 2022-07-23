package me.darux.kbffa.Item;

import me.darux.kbffa.Enums.LargaDistancia;
import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Jugador.Jugador;
import me.darux.kbffa.Jugador.JugadorUtils;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnItemsManager implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(new JugadorUtils().getJugador(e.getPlayer().getName()).isJugando()) return;
            if(e.getPlayer().getItemInHand().getType().equals(Material.PAPER)){
                Inventory inv= Bukkit.createInventory(null,9*3, Utils.translate("&bTops"));
                ItemStack item=ItemManager.crearSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U0MGRkYWUzNTFjNjY3NGI4OGQ2YzZjOTc4ZDE4OGU0YmJlNTY5NGIyNWMxMjk4NWY0ZDA5NzZmMDY5MGU1ZSJ9fX0=");
                ItemMeta meta=item.getItemMeta();
                meta.setDisplayName(Utils.translate("&bTop kills"));
                List<String[]> killssinorden=new ArrayList<>();
                for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(true)){
                    if(key.endsWith(".kills")){
                        String nick=Main.getInstance().getData().getString("Jugadores."+key.replaceAll(".kills",".nick"));
                        killssinorden.add(new String[]{Main.getInstance().getData().getInt("Jugadores."+key)+"",nick});

                    }
                }
                List<String[]> topkills=getJugadoresKills(killssinorden);

                List<String> lore=new ArrayList<>();
                List<String> loret=new ArrayList<>();
                lore.add("");
                lore.add("&c--------------------");
                lore.add("");

                for(int i=0;i<topkills.size();i++){
                    if (i>5) return;
                    int b=i+1;
                    lore.add("&e"+b+" &8- &b"+topkills.get(i)[1]+" &8(&a"+topkills.get(i)[0]+"&8)");
                }
                lore.add("");
                lore.add("&c--------------------");
                for(String a :lore){
                    loret.add(Utils.translate(a));
                }
                meta.setLore(loret);
                item.setItemMeta(meta);
                inv.setItem(9+2,item);



                item=ItemManager.crearSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWFlMzg1NWY5NTJjZDRhMDNjMTQ4YTk0NmUzZjgxMmE1OTU1YWQzNWNiY2I1MjYyN2VhNGFjZDQ3ZDMwODEifX19");
                meta=item.getItemMeta();
                lore.clear();
                loret.clear();
                meta.setDisplayName(Utils.translate("&bTop Muertes"));























               killssinorden=new ArrayList<>();
                for(String key : Main.getInstance().getData().getConfigurationSection("Jugadores").getKeys(true)){
                    if(key.endsWith(".muertes")){
                        String nick=Main.getInstance().getData().getString("Jugadores."+key.replaceAll(".muertes",".nick"));
                        killssinorden.add(new String[]{Main.getInstance().getData().getInt("Jugadores."+key)+"",nick});

                    }
                }
               topkills=getJugadoresKills(killssinorden);

                lore=new ArrayList<>();
                loret=new ArrayList<>();
                lore.add("");
                lore.add("&c--------------------");
                lore.add("");

                for(int i=0;i<topkills.size();i++){
                    if (i>5) return;
                    int b=i+1;
                    lore.add("&e"+b+" &8- &b"+topkills.get(i)[1]+" &8(&a"+topkills.get(i)[0]+"&8)");
                }
                lore.add("");
                lore.add("&c--------------------");
                for(String a :lore){
                    loret.add(Utils.translate(a));
                }
                meta.setLore(loret);
                item.setItemMeta(meta);
                inv.setItem(15,item);
                e.getPlayer().openInventory(inv);
            }else if(e.getPlayer().getItemInHand().getType().equals(Material.BLAZE_ROD)){
                Inventory inv=Bukkit.createInventory(null,9,Utils.translate("&bOrdenar Inventario"));
                Jugador jugador=new JugadorUtils().getJugador(e.getPlayer().getName());
                inv.setItem(jugador.getBloquesslot(),new ItemStack(jugador.getBloques(),1));
                inv.setItem(jugador.getPaloslot(),ItemUtils.getStick());
                inv.setItem(jugador.getPearlslot(),new ItemStack(Material.ENDER_PEARL,1));
                inv.setItem(jugador.getSpeedslot(),ItemUtils.getSpeed());
                inv.setItem(jugador.getTrhowslot(),new ItemStack(ItemUtils.largadistance(jugador.getLargaDistancia())));
                e.getPlayer().openInventory(inv);
            }else if(e.getPlayer().getItemInHand().getType().equals(Material.EXPLOSIVE_MINECART)){
                Inventory inv=Bukkit.createInventory(null,27,Utils.translate("&bAjustes"));
                ItemStack item=new ItemStack(Material.SANDSTONE);
                ItemMeta meta=item.getItemMeta();
                meta.setDisplayName(Utils.translate("&6Bloques"));
                List<String> lore=new ArrayList<>();
                lore.add("");
                lore.add("&8Click para cambiar los");
                lore.add("&8Los bloques con los que construyes");
                lore.add("");
                List<String> loret=new ArrayList<>();
                for(String a : lore){
                    loret.add(Utils.translate(a));
                }
                meta.setLore(loret);
                item.setItemMeta(meta);
                inv.setItem(11,item);


                item=new ItemStack(Material.BOW);
                meta=item.getItemMeta();
                meta.setDisplayName(Utils.translate("&6Gadget"));
                lore.clear();
                lore.add("");
                lore.add("&8Click para cambiar tu");
                lore.add("&8Gadget");
                lore.add("");
                loret.clear();
                for(String a : lore){
                    loret.add(Utils.translate(a));
                }
                meta.setLore(loret);
                item.setItemMeta(meta);
                inv.setItem(15,item);


                e.getPlayer().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getInventory().getName().equals(Utils.translate("&bAjustes"))){
            if(e.getInventory().getItem(e.getSlot())==null) return;
            if(e.getInventory().getItem(e.getSlot()).getType().equals(Material.SANDSTONE)){
                Inventory inv=Bukkit.createInventory(null,27,Utils.translate("&6Bloques"));
                FileCreator config=Main.getInstance().getConfig();
                for(String key : Main.getInstance().getConfig().getConfigurationSection("BLOQUES-PRECIO").getKeys(false)){

                    ItemStack item=new ItemStack(Material.valueOf(key));
                    ItemMeta meta=item.getItemMeta();
                    List<String> lore=new ArrayList<>();
                    lore.add("");
                    lore.add("&ePrecio: "+"&b"+config.getInt("BLOQUES-PRECIO."+key)+"$");
                    if(new JugadorUtils().getJugador(e.getWhoClicked().getName()).tienebloque(item.getType())){
                        lore.add("&eEstado: &aCOMPRADO");
                    }else{
                        lore.add("&eEstado: &CNO COMPRADO");
                    }
                    lore.add("");
                    List<String> loret=new ArrayList<>();
                    for(String a :lore){
                        loret.add(Utils.translate(a));
                    }
                    meta.setLore(loret);
                    if(item.getType().equals(new JugadorUtils().getJugador(e.getWhoClicked().getName()).getBloques())){
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        meta.addEnchant(Enchantment.DURABILITY,1,false);
                    }
                    item.setItemMeta(meta);
                    inv.addItem(item);
                }





                e.getWhoClicked().openInventory(inv);
            }else if(e.getInventory().getItem(e.getSlot()).getType().equals(Material.BOW)){
                Inventory inv=Bukkit.createInventory(null,27,Utils.translate("&6Gadgets"));
                ItemStack item=new ItemStack(Material.BOW);
                ItemMeta meta=item.getItemMeta();
                meta.setDisplayName(Utils.translate("&6Arco"));
                List<String> lore=new ArrayList<>();
                lore.add("");
                lore.add(Utils.translate("&eEstado: &aADQUIRIDO"));
                lore.add(Utils.translate("&ePrecio: &b0$"));
                lore.add("");
                if(new JugadorUtils().getJugador(e.getWhoClicked().getName()).getLargaDistancia().equals(LargaDistancia.ARCO)){
                    meta.addEnchant(Enchantment.ARROW_KNOCKBACK,1,false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(11,item);

                item=new ItemStack(Material.DIAMOND_HOE);
                meta=item.getItemMeta();
                meta.setDisplayName(Utils.translate("&6LanzaNieve"));
                int precio=Main.getInstance().getConfig().getInt("Lanza-Nieve.precio");
                lore.clear();
                lore.add("");
                if(new JugadorUtils().getJugador(e.getWhoClicked().getName()).getBloquescomprados().contains(Material.DIAMOND_HOE)){
                    lore.add(Utils.translate("&eEstado: &aADQUIRIDO"));
                }else{
                    lore.add(Utils.translate("&eEstado: &cNO ADQUIRIDO"));
                }
                lore.add(Utils.translate("&ePrecio: &b"+precio+"$"));
                if(new JugadorUtils().getJugador(e.getWhoClicked().getName()).getLargaDistancia().equals(LargaDistancia.LANZANIEVE)){
                    meta.addEnchant(Enchantment.ARROW_KNOCKBACK,1,false);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                meta.setLore( lore);
                item.setItemMeta(meta);
                inv.setItem(13,item);

                item=new ItemStack(Material.SHEARS);
                meta=item.getItemMeta();
                precio=Main.getInstance().getConfig().getInt("Lanza-tnt.precio");
                lore.clear();
                meta.setDisplayName(Utils.translate(Main.getInstance().getConfig().getString("Lanza-tnt.name")));
                lore.add("");
                if(new JugadorUtils().getJugador(e.getWhoClicked().getName()).getBloquescomprados().contains(Material.SHEARS)){
                    lore.add(Utils.translate("&eEstado: &aADQUIRIDO"));
                }else{
                    lore.add(Utils.translate("&eEstado: &cNO ADQUIRIDO"));
                }
                lore.add(Utils.translate("&ePrecio: &b"+precio+"$"));
                lore.add("");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(15,item);

                e.getWhoClicked().openInventory(inv);



            }

        }else if(e.getInventory().getTitle().equals(Utils.translate("&6Bloques"))){
            if(e.getInventory().getItem(e.getSlot()) != null){
                ItemStack item=e.getInventory().getItem(e.getSlot());
                Jugador jugador=new JugadorUtils().getJugador(e.getWhoClicked().getName());
                if(jugador.tienebloque(item.getType())){
                    // seleccionarlo
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage(Utils.translate("&aHas cambiado tus bloques de construcción"));
                    jugador.setBloques(item.getType());


                }else{
                    // comprar
                    int dinero=Main.getInstance().getConfig().getInt("BLOQUES-PRECIO."+item.getType().toString());
                    if(jugador.getMonedas()>=dinero){
                        Utils.quitar(jugador,dinero);
                        List<Material> materials=jugador.getBloquescomprados();
                        materials.add(item.getType());
                        jugador.setBloquescomprados(materials);
                        jugador.getPlayer().closeInventory();
                        jugador.setBloques(item.getType());
                        jugador.getPlayer().sendMessage(Utils.translate("&aHas comprado un nuevo bloque"));


                    }else{
                        jugador.getPlayer().sendMessage(Utils.translate("&cNo tienes suficiente dinero"));
                        jugador.getPlayer().closeInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().getName().equals(Utils.translate("&bOrdenar Inventario"))){
            int bloques=1;
            int perla=8;
            int arco=4;
            int speed=7;
            int stick=0;
            for(int i=0;i<e.getInventory().getSize();i++){
                if(e.getInventory().getItem(i) !=null){
                    if(e.getInventory().getItem(i).getType().equals(new JugadorUtils().getJugador(e.getPlayer().getName()).getBloques())){
                        bloques=i;
                    }else if(e.getInventory().getItem(i).getType().equals(Material.ENDER_PEARL)){
                        perla=i;
                    }else if(e.getInventory().getItem(i).getType().equals(ItemUtils.largadistance(new JugadorUtils().getJugador(e.getPlayer().getName()).getLargaDistancia()))){
                        arco=i;
                    }else if(e.getInventory().getItem(i).getType().equals(Material.FEATHER)){
                        speed=i;
                    }else if(e.getInventory().getItem(i).getType().equals(Material.STICK)){
                        stick=i;
                    }
                }

            }
            Jugador jugador=new JugadorUtils().getJugador(e.getPlayer().getName());
            jugador.setBloquesslot(bloques);
            jugador.setPearlslot(perla);
            jugador.setTrhowslot(arco);
            jugador.setSpeedslot(speed);
            jugador.setPaloslot(stick);
            jugador.getPlayer().sendMessage(Utils.translate("&aHas cambiado el orden de tu inventario correctamente"));
        }
    }



    public List<String[]> getJugadoresKills(List<String[]> jugadores){
       for(int i=0;i<jugadores.size();i++){
           int ikills=Integer.parseInt(jugadores.get(i)[0]);
           for(int c=i+1;c<jugadores.size();c++){
               int ckills=Integer.parseInt(jugadores.get(c)[0]);
               if(ikills < ckills){
                   jugadores.set(i,jugadores.get(c));
                   jugadores.set(c,jugadores.get(i));
               }
           }
       }
       return jugadores;

    }

    @EventHandler
    public void onClaick(InventoryClickEvent e){
        if(e.getInventory().getName().equals(Utils.translate("&6Gadgets"))){
            Jugador jugador=new JugadorUtils().getJugador(e.getWhoClicked().getName());
            if(e.getSlot()==11){
                jugador.setLargaDistancia(LargaDistancia.ARCO);
                jugador.getPlayer().closeInventory();
                jugador.getPlayer().sendMessage(Utils.translate("&aHas seleccionado el arco"));
            }else if(e.getSlot()==13){
                if(jugador.getBloquescomprados().contains(Material.DIAMOND_HOE)){
                    jugador.setLargaDistancia(LargaDistancia.LANZANIEVE);
                    jugador.getPlayer().sendMessage(Utils.translate("&aHas seleccionado el lanza nieve"));
                    jugador.getPlayer().closeInventory();
                }else{
                    int precio=Main.getInstance().getConfig().getInt("Lanza-Nieve.precio");
                    if(jugador.getMonedas()>=precio){
                        jugador.getBloquescomprados().add(Material.DIAMOND_HOE);
                        jugador.setLargaDistancia(LargaDistancia.LANZANIEVE);
                        jugador.getPlayer().closeInventory();
                        jugador.sumarmonedas(-precio);
                        jugador.getPlayer().sendMessage(Utils.translate("&aHas comprado correctamente el lanza nieve"));
                    }else{
                        jugador.getPlayer().closeInventory();
                        jugador.getPlayer().sendMessage(Utils.translate("&cNo tienes suficiente dinero para comprar este gadget"));
                    }
                }
            }else if(e.getSlot()==15){
                if(jugador.getBloquescomprados().contains(Material.SHEARS)){
                    jugador.setLargaDistancia(LargaDistancia.LANZATNT);
                    jugador.getPlayer().closeInventory();
                    jugador.getPlayer().sendMessage(Utils.translate("&aHas  seleccionado correctamente el lanza tnt"));
                }else{
                    int precio=Main.getInstance().getConfig().getInt("Lanza-tnt.precio");
                    if(jugador.getMonedas()>=precio){
                        jugador.getBloquescomprados().add(Material.SHEARS);
                        jugador.setLargaDistancia(LargaDistancia.LANZATNT);
                        jugador.getPlayer().closeInventory();
                        jugador.sumarmonedas(-precio);
                        jugador.getPlayer().sendMessage(Utils.translate("&aHas comprado correctamente el lanza tnt"));
                    }else{
                        jugador.getPlayer().closeInventory();
                        jugador.getPlayer().sendMessage(Utils.translate("&cNo tienes suficiente dinero para comprar este gadget"));
                    }
                }

            }
        }
    }
}
