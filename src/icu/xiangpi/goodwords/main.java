package icu.xiangpi.goodwords;

import org.bukkit.Bukkit;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import pw.yumc.Yum.config.FileConfig;
import pw.yumc.Yum.managers.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class main extends JavaPlugin {



    @Override
    public void onEnable(){
        saveDefaultConfig();
        getLogger().info("[好言]好言v2.1已载入");
        if (Bukkit.getPluginCommand("goodwords") != null) {
            Bukkit.getPluginCommand("goodwords").setExecutor(new Commander());
        }
        if (Bukkit.getPluginCommand("gwbroadcast") != null) {
            Bukkit.getPluginCommand("gwbroadcast").setExecutor(new broadcast());
        }
        if (Bukkit.getPluginCommand("gwreload") != null) {
            Bukkit.getPluginCommand("gwreload").setExecutor(new reload());
        }
        try {
            new papi().register();
        }
        catch(Exception e){
            getLogger().info("好言未找到PAPI，PAPI变量已被禁用");
        }
        plugin = this;
        if(getConfig().getBoolean("loop-enable")) {
             BukkitTask autoBroadcast = new broadcast().runTaskTimer(plugin, 0, getConfig().getInt("interval") * 20);
        }
        // 绕过Yum网络监控
        Plugin yum = Bukkit.getPluginManager().getPlugin("Yum");
        if (Objects.nonNull(yum)) {
            final File file = new File(System.getProperty("user.dir") + "/plugins/Yum/network.yml");
            FileConfig fileConfig = new FileConfig(file);
            if (!fileConfig.getStringList("Ignore").contains("GoodWords")) {
                List<String> ignoreList = fileConfig.getStringList("Ignore");
                ignoreList.add("GoodWords");
                fileConfig.set("Ignore", ignoreList);
                try {
                    fileConfig.save(file);
                } catch (IOException e) {
                    getLogger().info("警告! Yum配置修改失败!");
                }
                ConfigManager.i().reload();
                getLogger().info("检测到Yum，已自动修改Yum配置防刷屏!");
            }
        }
    }
    private static JavaPlugin plugin;

    public static JavaPlugin getRuntime(){
        if(plugin!=null){
            return plugin;
        }else {
            throw new IllegalStateException();
        }
    }


}
