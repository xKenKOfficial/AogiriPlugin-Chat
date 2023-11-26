package com.github.xKenKOfficial.Chat.Utils.Updaters;

import com.github.xKenKOfficial.Chat.Basic.Main;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker
{
    private Main plugin;
    private String version;

    public UpdateChecker(Main plugin, String version)
    {
        this.plugin = plugin;
        this.version = version;
    }

    public void checkUpdate(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://github.com/xKenKOfficial/xKenWhitelist/releases/tag/v" + this.version).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch(IOException ioe) {
                plugin.getLogger().info("Update Checker is broken, cant't find an update! " + ioe.getMessage());
            }
        });
    }
}
