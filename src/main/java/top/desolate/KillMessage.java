package top.desolate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.desolate.Utils.NMS;
import top.desolate.Utils.YMLUtil;

import java.io.File;

public class KillMessage extends JavaPlugin {

    protected static FileConfiguration config;
    private KillListener killListener;
    protected static int mcVersion;
    YMLUtil ymlUtil = new YMLUtil();

    @Override
    public void onEnable() {
        //加载配置文件
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            boolean isCreateDir = configFile.getParentFile().mkdirs();
            //添加一个文件夹创建判断
            if (!isCreateDir) {
                getLogger().warning("Failed to create config.yml!");
                return;
            }
            saveResource("config.yml", false);
        }
        //获取版本
        mcVersion = Integer.parseInt(getServer().getBukkitVersion().replace('-', '.').split("\\.")[1]);
        //初始化配置文件
        config = getConfig();
        try {
            NMS nmsUtil = new NMS(this);
            Message message = new Message(nmsUtil,ymlUtil);
            killListener = new KillListener(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //初始化数据路径
        ymlUtil.setYmlFilePath(getConfig().getString("PVP数据路径"));
        //注册监听器
        getServer().getPluginManager().registerEvents(killListener, this);
        getLogger().info("KillMessage Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }
}
