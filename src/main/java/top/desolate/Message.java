package top.desolate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Message {

    KillMessage killMessage=new KillMessage();
    FileConfiguration config= killMessage.getConfig();

    /**
     *
     * @param player 玩家
     */
    public void SendMessages(ItemStack item, Player player){
        ItemMeta meta=item.getItemMeta();
        String itemName= meta.getDisplayName();



    }
}
