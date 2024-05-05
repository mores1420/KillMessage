package top.desolate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class KillMessage extends JavaPlugin {

    @Override
    public void onEnable(){
        //加载配置文件
        File configFile=new File(getDataFolder(),"config.yml");
        if (!configFile.exists()) {
            boolean isCreateDir = configFile.getParentFile().mkdirs();
            //添加一个文件夹创建判断
            if (!isCreateDir) {
                getLogger().warning("Failed to create config.yml!");
                return;
            }
            saveResource("config.yml", false);
        }
        getLogger().info("KillMessage Enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("Disabled!");
    }

    //获取配置文件
    public FileConfiguration getConfig(){
        FileConfiguration config=this.getConfig();
        return config;
    }
}
