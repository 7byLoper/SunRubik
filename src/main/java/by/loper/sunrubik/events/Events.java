package by.loper.sunrubik.events;

import by.loper.sunrubik.SunRubik;
import by.loper.sunrubik.utils.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public class Events implements Listener {
    @EventHandler
    public void placeRubik(BlockPlaceEvent e){
        if(e.getItemInHand().getItemMeta() == null) return;
        if(!e.getItemInHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SunRubik.getInstance(), "cubeRubik"), PersistentDataType.STRING)) return;
        e.setCancelled(true);

    }

    @EventHandler
    public void useRubik(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SunRubik.getInstance(), "cubeRubik"), PersistentDataType.STRING)) {
                    int reward = Utils.random(SunRubik.getInstance().getConfig().getInt("cubeRubik.reward.minReward"),SunRubik.getInstance().getConfig().getInt("cubeRubik.reward.maxReward"));
                    e.setCancelled(true);
                    ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
                    item.setAmount(item.getAmount() - 1);
                    p.sendMessage(Utils.cfgMessage("messages.openRubik").replace("%reward%", String.valueOf(reward)));
                    SunRubik.ppAPI.give(p.getUniqueId(), reward);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10,10);

                }
            } else if (p.getInventory().getItemInOffHand().getItemMeta() != null) {
                if (e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(SunRubik.getInstance(), "cubeRubik"), PersistentDataType.STRING)) {
                    int reward = Utils.random(SunRubik.getInstance().getConfig().getInt("cubeRubik.reward.minReward"),SunRubik.getInstance().getConfig().getInt("cubeRubik.reward.maxReward"));
                    e.setCancelled(true);
                    ItemStack item = e.getPlayer().getInventory().getItemInOffHand();
                    item.setAmount(item.getAmount() - 1);
                    p.sendMessage(Utils.cfgMessage("messages.openRubik").replace("%reward%", String.valueOf(reward)));
                    SunRubik.ppAPI.give(p.getUniqueId(), reward);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 10,10);

                }
            }
        }
    }
}
