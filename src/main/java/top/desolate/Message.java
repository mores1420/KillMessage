package top.desolate;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Message {

    KillMessage killMessage=new KillMessage();
    FileConfiguration config= killMessage.getConfig();

    NMS nmsUtil;

    /**
     *
     * @param item 手上的物品
     * @param player 获取信息的玩家
     *
     */
    public void SendMessages(ItemStack item, Player player){
        //判断空手
        if (item.getType()!= Material.AIR){
            ItemMeta meta=item.getItemMeta();
            if (meta!=null){
                String itemNBT= nmsUtil.getItemNBT(item);
                ComponentBuilder builder=new ComponentBuilder();
                TextComponent itemInfo=new TextComponent("[");
                //是否有显示名称
                if (meta.hasDisplayName()){
                    for (BaseComponent component:TextComponent.fromLegacyText(meta.getDisplayName())){
                        itemInfo.addExtra(component);
                    }
                }else {
                    boolean tag=true;
                    if (tag){
                        if (itemNBT.contains("Enchantments:[")){
                            itemInfo.setColor(ChatColor.AQUA);
                        }else {
                            itemInfo.setColor(ChatColor.WHITE);
                        }
                    }else {
                        if (item.getType().isRecord()||itemNBT.contains("ench:[")){
                            itemInfo.setColor(ChatColor.AQUA);
                        }else {
                            itemInfo.setColor(ChatColor.WHITE);
                        }
                    }
                }
                itemInfo.addExtra("]");
                itemInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM,new BaseComponent[]{new TextComponent(itemNBT)}));
                builder.append(itemInfo);
                BaseComponent[] message=builder.create();
            }
        }



    }
}
