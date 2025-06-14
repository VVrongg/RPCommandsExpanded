package com.arisnight.rpcommands.commands;

import com.arisnight.rpcommands.RPCommands;
import com.arisnight.rpcommands.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s,  String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage("§aRPCommandsExpanded plugin by " + RPCommands.getInstance().getDescription().getAuthors() + " (" + RPCommands.getInstance().getDescription().getVersion() + ")");
            return true;
        }else if (strings.length > 1) {
            commandSender.sendMessage("Wrong input");
            return true;
        }else {
            if (strings[0].equalsIgnoreCase("reload")) {
                if (commandSender.hasPermission("rp.reload")) {
                    Config.reload(true);
                    commandSender.sendMessage("§aRPCommandsExpanded §eReloaded");
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> possibleTabs = new ArrayList<>();
        if(commandSender.hasPermission("rpcommand.reload")){
            possibleTabs.add("reload");
        }
        return possibleTabs;
    }
}
