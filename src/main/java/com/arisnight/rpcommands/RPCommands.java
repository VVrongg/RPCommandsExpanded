package com.arisnight.rpcommands;

import com.arisnight.rpcommands.commands.DoCommand;
import com.arisnight.rpcommands.commands.MeCommand;
import com.arisnight.rpcommands.commands.TryCommand;
import com.arisnight.rpcommands.utils.Config;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPCommands extends JavaPlugin {

    private static RPCommands INSTANCE;

    public static RPCommands getInstance(){ return INSTANCE;}

    @Override
    public void onEnable() {
        INSTANCE = this;

        Config.load();

        getCommand("me").setExecutor(new MeCommand());
        getCommand("do").setExecutor(new DoCommand());
        getCommand("try").setExecutor(new TryCommand());





        getLogger().info("RPCommands here");
    }

    @Override
    public void onDisable() {

    }
}
