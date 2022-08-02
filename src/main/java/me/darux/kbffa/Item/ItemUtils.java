package me.darux.kbffa.Item;

import me.darux.kbffa.Enums.LargaDistancia;
import me.darux.kbffa.File.FileCreator;
import me.darux.kbffa.Main;
import me.darux.kbffa.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtils {

    public static ItemStack getStick(){
        FileCreator messages= Main.getInstance().getConfig();
        String nombre=messages.getString("STICK.name");
        List<String> lore=messages.getStringList("STICK.lore");
        ItemStack item=new ItemStack(Material.STICK,1);
        ItemMeta meta=item.getItemMeta();
        meta.setDisplayName(nombre);
        meta.setLore(lore);
        meta.addEnchant(Enchantment.KNOCKBACK,messages.getInt("STICK.empuje"),true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSpeed(){
        ItemStack item=new ItemStack(Material.FEATHER);
        ItemMeta meta=item.getItemMeta();
        meta.setDisplayName(Main.getInstance().getConfig().getString("SPEED.name"));
        meta.setLore(Main.getInstance().getConfig().getStringList("SPEED.lore"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSpeedCooldown(int cooldown){
        ItemStack item=new ItemStack(351,cooldown,(short)7);
        ItemMeta meta=item.getItemMeta();
        meta.setDisplayName(Utils.translate("&bVelocidad &7(Cooldown)"));
        item.setItemMeta(meta);
        return item;
    }
    public static void setDefaultInv(Player p){
        ItemStack item=new ItemStack(Material.BLAZE_ROD,1);
        ItemMeta meta=item.getItemMeta();
        meta.setDisplayName(Utils.translate("&6Ordenar Inventario"));
        item.setItemMeta(meta);
        p.getInventory().clear();
        p.getInventory().setItem(4,item);

        item=new ItemStack(Material.PAPER,1);
        meta=item.getItemMeta();
        meta.setDisplayName(Utils.translate("&bTops"));
        item.setItemMeta( meta);
        p.getInventory().setItem(0,item);

        item=new ItemStack(Material.EXPLOSIVE_MINECART,1);
        meta=item.getItemMeta();
        meta.setDisplayName(Utils.translate("&8Ajustes"));
        item.setItemMeta(meta);
        p.getInventory().setItem(8,item);
    }


    public static ItemStack largadistance(LargaDistancia a){
        if(a.equals(LargaDistancia.ARCO)){
            return getArco();
        }else if(a.equals(LargaDistancia.LANZANIEVE)){
            ItemStack item=new ItemStack(Material.DIAMOND_HOE,1);
            ItemMeta meta=item.getItemMeta();
            meta.setDisplayName(Utils.translate("&bLanza nieves"));
            meta.addEnchant(Enchantment.DURABILITY,1,false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);

            return item;
        }else if(a.equals(LargaDistancia.LANZATNT)){
           ItemStack item=new ItemStack(Material.SHEARS);
           ItemMeta meta=item.getItemMeta();
           meta.setDisplayName(Utils.translate("&cLanza fuego &8(&e3&8)"));
            meta.addEnchant(Enchantment.DURABILITY,1,false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);

            return item;
        }
        else{
            return null;
        }
    }

    public static ItemStack getArco(){
        ItemStack item=new ItemStack(Material.BOW);
        ItemMeta meta=item.getItemMeta();
        FileCreator config=Main.getInstance().getConfig();
        meta.setDisplayName(Utils.translate("&6Arco"));
        for(String key: config.getConfigurationSection("Encantamientos-arco").getKeys(false)){
            meta.addEnchant(Enchantment.getByName(key),config.getInt("Encantamientos-arco."+key),false);
        }

        item.setItemMeta(meta);
        return item;
    }
}
