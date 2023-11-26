package com.github.xKenKOfficial.Chat.Commands;

import com.github.xKenKOfficial.Chat.Basic.Main;
import com.github.xKenKOfficial.Chat.Utils.Chat.ChatUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String l, final String[] args) {
        final String prefix = Main.getPlugin().getConfig().getString("prefix");
        final String brakPerm = Main.getPlugin().getConfig().getString("no_permissions");
        if(!Sender.hasPermission("aogiriplugin.chat.admin.chatclear")) {
            Sender.sendMessage(ChatUtil.fixColor(prefix + " " + brakPerm));
            return false;
        }
        final Player admin = (Player)Sender;
        if (args.length < 1) {
            final String wiadomosc = Main.getPlugin().getConfig().getString("message").replace("{ADMIN}", admin.getName());
            for(int i = 0; i < 400; i++) {
                for (final Player celowniki : Bukkit.getOnlinePlayers()) {
                    celowniki.sendMessage(" ");
                }
            }
            for(final Player cel : Bukkit.getOnlinePlayers()) {
                cel.sendMessage(ChatUtil.fixColor(prefix + " " + wiadomosc));
            }
            final String adminmsg = Main.getPlugin().getConfig().getString("message_admin");
            admin.sendMessage(ChatUtil.fixColor(prefix + " " + adminmsg));
        }
        return false;
    }
}
