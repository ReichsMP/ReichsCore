package me.nils.reichsmp.reichscore.main;

import me.nils.reichsmp.reichscore.logging.LogHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class ReichsCore extends JavaPlugin {


    @Override
    public void onEnable() {
        new LogHandler(this, "[ReichsCore]").init();
        getLogger().info("&aReichsCore has been enabled.");
    }


    @Override
    public void onDisable() {
        getLogger().info("&cReichsCore has been disabled.");
    }


}
