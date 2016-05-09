package me.InfoPaste.NullMob;

import me.InfoPaste.NullMob.command.MainCommands;
import me.InfoPaste.NullMob.core.Config;
import me.InfoPaste.NullMob.listener.SpawnListener;
import me.InfoPaste.NullMob.util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onDisable() {
        this.plugin = null;
    }

    @Override
    public void onEnable() {

        this.plugin = this;

        serverCompatibility();
        loadCommands();

        Config.setup(this);

        registerEvents(this, new SpawnListener());

    }

    private void loadCommands() {
        getCommand("nullmob").setExecutor(new MainCommands());
    }

    private void serverCompatibility() {
        String serverVersion = ReflectionUtil.getVersion();

        if (!serverVersion.contains("1_9")) {
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
