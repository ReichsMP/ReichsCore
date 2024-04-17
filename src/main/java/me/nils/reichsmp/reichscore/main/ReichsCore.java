package me.nils.reichsmp.reichscore.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class ReichsCore extends JavaPlugin {


    @Override
    public void onEnable() {
        if(!getDataFolder().exists())
            getDataFolder().mkdirs();
        new LogHandler(this, "ReichsCore").init();
        getLogger().log(Level.INFO, "&aReichsCore has been enabled.");

        /*
        Testing
         */
    }


    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "&cReichsCore has been disabled.");
    }


}
