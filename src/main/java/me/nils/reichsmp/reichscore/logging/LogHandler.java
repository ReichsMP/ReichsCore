package me.nils.reichsmp.reichscore.logging;

import me.nils.reichsmp.reichscore.util.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogHandler extends Handler {

    private JavaPlugin plugin;
    private String logPrefix;


    public LogHandler(JavaPlugin plugin, String logPrefix) {
        this.plugin = plugin;
        this.logPrefix = logPrefix;
    }


    public void init() {
        Logger logger = plugin.getLogger();
        logger.setUseParentHandlers(false);
        Arrays.asList(logger.getHandlers()).forEach(logger::removeHandler);
        logger.addHandler(this);
    }


    @Override
    public void publish(LogRecord record) {
        plugin.getServer().getConsoleSender().sendMessage(Util.cc(String.format("[%s] %s", logPrefix, record.getMessage())));
    }


    @Override
    public void flush() {}


    @Override
    public void close() throws SecurityException {}


}
