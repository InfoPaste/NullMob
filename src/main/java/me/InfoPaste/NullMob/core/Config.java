package me.InfoPaste.NullMob.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;

public class Config {

    public static FileConfiguration config;
    static File cfile;

    public static FileConfiguration data;
    static File dfile;

    public static void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        dfile = new File(p.getDataFolder(), "data.yml");

        if (!cfile.exists()) {
            cfile.getParentFile().mkdirs();
            p.saveResource("config.yml", false);
        }

        if (!dfile.exists()) {
            dfile.getParentFile().mkdirs();
            p.saveResource("data.yml", false);
        }

        config = new YamlConfiguration();
        data = new YamlConfiguration();

        try {
            config.load(cfile);
            data.load(dfile);

            config = YamlConfiguration.loadConfiguration(cfile);
            data = YamlConfiguration.loadConfiguration(dfile);

            config.options().copyDefaults(true);
            data.options().copyDefaults(true);

            p.saveDefaultConfig();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
