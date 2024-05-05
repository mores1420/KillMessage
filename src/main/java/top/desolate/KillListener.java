package top.desolate;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class KillListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player=event.getEntity();
        if (player.getKiller() != null){
            Player killer=player.getKiller();

            //获取物品
            ItemStack itemStack=killer.getInventory().getItemInMainHand();
            ItemMeta itemMetaOnMainHand= itemStack.getItemMeta();
            assert itemMetaOnMainHand != null;
            String itemName= itemMetaOnMainHand.getDisplayName();
            List<String> itemLore=itemMetaOnMainHand.getLore();

        }
    }
}
