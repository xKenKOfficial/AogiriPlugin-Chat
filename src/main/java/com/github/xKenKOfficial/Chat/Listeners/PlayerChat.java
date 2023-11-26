package com.github.xKenKOfficial.Chat.Listeners;

import com.github.xKenKOfficial.Chat.Basic.Main;
import com.github.xKenKOfficial.Chat.Utils.Chat.ChatUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;

public class PlayerChat implements Listener
{
    static HashMap<Player, Long> czas;

    static {
        czas = new HashMap<Player, Long>();
    }

    @EventHandler
    public void onChat(final PlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String prefix = Main.getPlugin().getConfig().getString("prefix");
        final String stopSpam = Main.getPlugin().getConfig().getString("antyspam.when_the_time_is_not_up");
        if(!p.isOp() || !p.hasPermission("aogiriplugin.chat.admin.antispam")) {
            if(czas.containsKey(p)) {
                if(czas.get(p) > System.currentTimeMillis()) {
                    p.sendMessage(ChatUtil.fixColor(prefix + " " + stopSpam));
                    e.setCancelled(true);
                } else {
                    czas.put(p, System.currentTimeMillis() + Main.getPlugin().getConfig().getInt("antyspam_timer"));
                }
            } else {
                czas.put(p, System.currentTimeMillis() + Main.getPlugin().getConfig().getInt("antyspam_timer"));
            }
        }
    }
}
