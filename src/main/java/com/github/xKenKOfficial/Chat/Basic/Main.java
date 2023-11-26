package com.github.xKenKOfficial.Chat.Basic;

import com.github.xKenKOfficial.Chat.Commands.ChatClearCommand;
import com.github.xKenKOfficial.Chat.Commands.ChatCommand;
import com.github.xKenKOfficial.Chat.Listeners.ChatListener;
import com.github.xKenKOfficial.Chat.Listeners.PlayerChat;
import com.github.xKenKOfficial.Chat.Listeners.PlayerJoin;
import com.github.xKenKOfficial.Chat.Utils.Updaters.UpdateChecker;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Main extends JavaPlugin
{
    private static Main plugin;

    @Override
    public void onEnable()
    {
        (plugin) = this;
        this.saveDefaultConfig();
        this.registerCommands();
        this.registerListeners();
        this.getUpdate();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wykryta wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Jakiekolwiek edycje i naruszenie praw autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    @Override
    public void onDisable()
    {
        this.saveDefaultConfig();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wykryta wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Jakiekolwiek edycje i naruszenie praw autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    private void registerCommands()
    {
        this.getLogger().info("Ladowanie komend z pluginu: " + this.getName());
        this.getCommand("chatclear").setExecutor(new ChatClearCommand());
        this.getCommand("chat").setExecutor(new ChatCommand());
    }

    private void registerListeners()
    {
        this.getLogger().info("Ladowanie eventow z pluginu: " + this.getName());
        Bukkit.getServer().getPluginManager().registerEvents((Listener)new ChatListener(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerChat(), (Plugin)this);
    }

    private void getUpdate()
    {
        new UpdateChecker(this, this.getDescription().getVersion()).checkUpdate(version -> {
            if(this.getDescription().getVersion().equalsIgnoreCase(version)) {
                this.getLogger().log(Level.WARNING, "Wykryto nowa aktualizacje pluginu!");
            } else {
                this.getLogger().log(Level.INFO, "Posiadasz aktualna wersje pluginu.");
            }
        });
    }

    public static Main getPlugin()
    {
        return plugin;
    }
}
