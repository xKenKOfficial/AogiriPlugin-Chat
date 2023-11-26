package com.github.xKenKOfficial.Chat.Listeners;

import com.github.xKenKOfficial.Chat.Basic.Main;
import com.github.xKenKOfficial.Chat.Commands.ChatCommand;
import com.github.xKenKOfficial.Chat.Utils.Chat.ChatUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener extends ChatCommand implements Listener
{
    @EventHandler
    public void onChat(final PlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String wiadomosc = Main.getPlugin().getConfig().getString("chat.message.blocked");
        final String prefix = Main.getPlugin().getConfig().getString("prefix");
        if(!(ChatCommand.muted) != true) {
            if(!p.hasPermission("aogiriplugin.chat.admin.chat")) {
                e.setCancelled(true);
                p.sendMessage(ChatUtil.fixColor(prefix + " " + wiadomosc));
            }
        }
    }
}
