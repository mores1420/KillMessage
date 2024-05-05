package top.desolate;

import org.bukkit.plugin.java.JavaPlugin;

public class KillMessage extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("KillMessage Enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("Disabled!");
    }
}
