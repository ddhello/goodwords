package icu.xiangpi.goodwords;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class reload implements CommandExecutor {
    public static boolean reloaded = false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("goodwords.reload")){
            sender.sendMessage(ChatColor.GREEN+"[好言]好言配置已重新加载");
            main.getRuntime().reloadConfig();
            reloaded = true;
            return true;
        }
        else{
            sender.sendMessage(ChatColor.RED+"您没有执行该命令的权限");
        }
        return false;
    }



}
