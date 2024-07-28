package by.loper.sunrubik.utils;

import by.loper.sunrubik.SunRubik;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemManager {
    private static ItemStack Rubik = ItemUtils.getPlayerHeadFromBase64(SunRubik.getInstance().getConfig().getString("cubeRubik.baseHead"));
    private static String displayName = Utils.cfgMessage("cubeRubik.displayName");
    private static List<String> lore = SunRubik.getInstance().getConfig().getStringList("cubeRubik.lore");
    public static ItemStack getRubik(int amount) {
        ItemMeta rubikMeta = Rubik.getItemMeta();
        if(rubikMeta == null) return Rubik;
        lore.replaceAll(s -> s.replace("&", "§"));
        if(displayName != null){
            rubikMeta.setDisplayName(displayName);
        }
        if(lore != null){
            rubikMeta.setLore(lore);
        }
        Rubik.setAmount(amount);
        rubikMeta.getPersistentDataContainer().set(new NamespacedKey(SunRubik.getInstance(), "cubeRubik"), PersistentDataType.STRING, "cubeRubik");
        Rubik.setItemMeta(rubikMeta);
        return Rubik;
    }
}
