package com.github.xKenKOfficial.Chat.Commands;

import com.github.xKenKOfficial.Chat.Basic.Main;
import com.github.xKenKOfficial.Chat.Utils.Chat.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor
{
    public static boolean muted = false;

    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String l, final String[] args) {
        final String prefix = Main.getPlugin().getConfig().getString("prefix");
        final String brakPerm = Main.getPlugin().getConfig().getString("no_permissions");
        final String popUzycie = Main.getPlugin().getConfig().getString("correct_use.chat");
        if(!Sender.hasPermission("aogiriplugin.chat.admin.chat")) {
            Sender.sendMessage(ChatUtil.fixColor(prefix + " " + brakPerm));
            return false;
        }
        if(args.length < 1) {
            Sender.sendMessage(ChatUtil.fixColor(prefix + " " + popUzycie));
            return false;
        }
        final String chatOn = Main.getPlugin().getConfig().getString("chat.message.enable").replace("{ADMIN}", Sender.getName());
        final String chatOff = Main.getPlugin().getConfig().getString("chat.message.disable").replace("{ADMIN}", Sender.getName());
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("on")) {
                muted = false;
                for(final Player online : Bukkit.getOnlinePlayers()) {
                    for(int i = 0; i < 300; i++) {
                        online.sendMessage(ChatUtil.fixColor(" "));
                    }
                    online.sendMessage(ChatUtil.fixColor(prefix + " " + chatOn));
                }
            } else if(args[0].equalsIgnoreCase("off")) {
                muted = true;
                for(final Player online : Bukkit.getOnlinePlayers()) {
                    for(int i = 0; i < 300; i++) {
                        online.sendMessage(ChatUtil.fixColor(" "));
                    }
                    online.sendMessage(ChatUtil.fixColor(prefix + " " + chatOff));
                }
            }
        }
        return false;
    }
}
