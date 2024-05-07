package top.desolate;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Message {

    private final NMS nmsUtil;

    public Message(NMS nmsUtil) {
        this.nmsUtil = nmsUtil;
    }

    /**
     * @param item   手上的物品
     * @param player 获取信息的玩家
     */
    public void SendMessages(ItemStack item, Player player, Player killer) {
        //判断空手
        if (item.getType() != Material.AIR) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                String itemNBT = nmsUtil.getItemNBT(item);
                ComponentBuilder builder = new ComponentBuilder();

                //构建消息文本
                String playerName = player.getName();
                String killerName = killer.getName();

                TextComponent killerText = new TextComponent(killerName);
                killerText.setColor(ChatColor.GOLD);
                killerText.setBold(true);
                killerText.setItalic(true);
                builder.append(killerText);
                TextComponent forText = new TextComponent("使用");
                forText.setColor(ChatColor.WHITE);
                builder.append(forText);

                TextComponent itemInfo = new TextComponent("[");
                //是否有显示名称
                if (meta.hasDisplayName()) {
                    for (BaseComponent component : TextComponent.fromLegacyText(meta.getDisplayName())) {
                        itemInfo.addExtra(component);
                    }
                } else {
                    boolean tag = true;
                    if (tag) {
                        if (itemNBT.contains("Enchantments:[")) {
                            itemInfo.setColor(ChatColor.AQUA);
                        } else {
                            itemInfo.setColor(ChatColor.WHITE);
                        }
                    } else {
                        if (item.getType().isRecord() || itemNBT.contains("ench:[")) {
                            itemInfo.setColor(ChatColor.AQUA);
                        } else {
                            itemInfo.setColor(ChatColor.WHITE);
                        }
                    }
                }
                itemInfo.addExtra("]");
                itemInfo.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new BaseComponent[]{new TextComponent(itemNBT)}));

                builder.append(itemInfo);
                TextComponent andText = new TextComponent("击杀了");
                andText.setColor(ChatColor.WHITE);
                builder.append(andText);
                TextComponent playerText = new TextComponent(playerName);
                playerText.setColor(ChatColor.DARK_RED);
                playerText.setItalic(true);
                playerText.setStrikethrough(true);
                builder.append(playerText);
                BaseComponent[] message = builder.create();

                //判断世界
                boolean onlyWorld = KillMessage.config.getBoolean("只给相同世界的玩家广播");
                if (onlyWorld) {
                    //获取击杀者所在的世界
                    World playerWorld = killer.getWorld();
                    Player[] onWorldPlayer = playerWorld.getPlayers().toArray(new Player[0]);
                    for (Player player1 : onWorldPlayer) {
                        player1.spigot().sendMessage(message);
                    }
                } else {
                    Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                    for (Player player1 : onlinePlayers) {
                        player1.spigot().sendMessage(message);
                    }
                }
            }
        }
    }
}
