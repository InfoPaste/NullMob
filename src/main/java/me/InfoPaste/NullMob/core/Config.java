package me.InfoPaste.NullMob.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.*;

public class Config {

    static Config instance = new Config();
    static Plugin p;

    public static FileConfiguration config;
    static File cfile;

    public static FileConfiguration data;
    static File dfile;

    public static Config getInstance() {
        return instance;
    }

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

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte['?'];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            data.save(dfile);
        } catch (IOException e) {
            System.out.print("Error");
        }
    }

    public static void saveConfig() {
        try {
            config.save(cfile);
        } catch (IOException ignored) {

        }
    }

    public static void reloadConfigFile() {

        if (config == null) {
            cfile = new File(p.getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(cfile);

        try {
            // Look for defaults in the jar
            InputStream defConfigStream = p.getResource("config.yml");

            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                config.setDefaults(defConfig);
            }
        }catch (NullPointerException ignored){

        }

    }

    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }
}
