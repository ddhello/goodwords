package icu.xiangpi.goodwords;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.javac.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class broadcast extends BukkitRunnable  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || !sender.hasPermission("goodwords.broadcast")) {
            Bukkit.getPlayer(sender.getName()).sendMessage(ChatColor.RED+"正确使用方法：/gwbroadcast [type],type中输入语句类型。例如:动画-a,漫画-b,游戏-c,文学-d,原创-e,来自网络-f,其他-g,影视-h,诗词-i,网易云-j,哲学-k,抖机灵-l");
            return true;
        } else {
            Bukkit.broadcastMessage(Commander.getWords(args[0]));
            return true;
        }
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(Commander.getWords(main.getRuntime().getConfig().getString("type")));
        if(reload.reloaded==true){
            reload.reloaded = false;
            this.cancel();
            BukkitTask autoBroadcast = new broadcast().runTaskTimer(main.getRuntime(), 0, main.getRuntime().getConfig().getInt("interval") * 20);
        }
    }
}
