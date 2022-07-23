package me.darux.kbffa.Item;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {
    public ItemManager() {
    }

    public static ItemStack crearSkull(String textura) {
        ItemStack item = null;

        item = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short)3);


        if (textura.isEmpty()) {
            return item;
        } else {
            SkullMeta skullMeta = (SkullMeta)item.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
            profile.getProperties().put("textures", new Property("textures", textura));

            try {
                Field profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, profile);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException var5) {
                var5.printStackTrace();
            }

            item.setItemMeta(skullMeta);
            return item;
        }
    }


}
