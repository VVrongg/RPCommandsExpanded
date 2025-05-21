package com.arisnight.rpcommands.commands;

import com.arisnight.rpcommands.utils.Config;
import com.arisnight.rpcommands.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MeCommand implements CommandExecutor {

    public String getCommandMe(){
        return Config.MeCommandFormat.getString();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.hasPermission("rpcommand.me")) {
            return true;
        }

        if (args.length == 0) {
            return true;
        }

        String message = String.join(" ", args);

        String finalMessage = String.format(getCommandMe(), player.getName(), message);

        Utils.sendMessage(player, Config.Message_Radius.getInt(), finalMessage);

        return true;
    }
}