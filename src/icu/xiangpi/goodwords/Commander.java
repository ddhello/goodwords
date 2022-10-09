package icu.xiangpi.goodwords;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Commander implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            Bukkit.getPlayer(sender.getName()).sendMessage(ChatColor.RED + "正确使用方法：/goodwords [type],type中输入语句类型。例如:动画-a,漫画-b,游戏-c,文学-d,原创-e,来自网络-f,其他-g,影视-h,诗词-i,网易云-j,哲学-k,抖机灵-l");
            return true;
        } else {
            Bukkit.getPlayer(sender.getName()).sendMessage(getWords(args[0]));
            return true;
        }

    }
    public static String getWords(String type) {
        if (type.compareTo("a") >= 0 && type.compareTo("l") <= 0) {
            try {
                URL url = new URL("https://v1.hitokoto.cn/?c=" + type);
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(3000);
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String result = br.readLine();
                JSONObject jsonMap = JSON.parseObject(result);
                Object author = jsonMap.get("from");
                Object content = jsonMap.get("hitokoto");
                return ChatColor.GREEN + "[好言]" + ChatColor.AQUA + content + " --- " + author;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(type.compareTo("r") == 0){
            Random rand = new Random();
            int randtype = rand.nextInt(11);
            int typedo = 97+randtype;
            char jieguo = (char)typedo;
            try {
                URL url = new URL("https://v1.hitokoto.cn/?c=" + jieguo);
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String result = br.readLine();
                JSONObject jsonMap = JSON.parseObject(result);
                Object author = jsonMap.get("from");
                Object content = jsonMap.get("hitokoto");
                return ChatColor.GREEN + "[好言]" + ChatColor.AQUA + content + " --- " + author;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "参数错误";
    }


}
