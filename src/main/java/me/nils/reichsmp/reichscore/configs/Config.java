package me.nils.reichsmp.reichscore.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Level;

public class Config {

    private JavaPlugin plugin;
    private String name;

    private File file;
    private FileConfiguration config;


    public Config(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }


    public void init() {
        file = new File(plugin.getDataFolder(), name);

        if(file.exists()) {
            try {
                reload(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                plugin.getLogger().log(Level.SEVERE, String.format("&c[%s] Failed to load FileInputStream.", name));
            }
            return;
        }

        InputStream in;
        try {
            if ((in = plugin.getResource("defaults/" + name)) != null) {
                plugin.getLogger().log(Level.INFO, String.format("&6[%s] Default config found.", name));
                OutputStream out = new FileOutputStream(file);
                byte[] buf = new byte[1024];

                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                out.close();
                in.close();
                plugin.getLogger().log(Level.INFO, String.format("&6[%s] Default config copied to data folder.", name));
                return;
            }
        } catch(IOException e) {
            plugin.getLogger().log(Level.WARNING, String.format("&c[%s] Failed to copy config to data folder.", name));
        }

        in = plugin.getResource("configs/" + name);
        if(in == null) {
            plugin.getLogger().log(Level.SEVERE, String.format("&c[%s] Config not found.", name));
            return;
        }

        reload(in);
    }


    public void reload(InputStream in) {
        config = YamlConfiguration.loadConfiguration(new InputStreamReader(in));
        plugin.getLogger().log(Level.INFO, String.format("&6[%s] Config reloaded.", name));
    }


    public FileConfiguration get() {
        return config;
    }


}
