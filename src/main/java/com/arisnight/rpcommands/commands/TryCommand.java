package com.arisnight.rpcommands.commands;

import com.arisnight.rpcommands.utils.Config;
import com.arisnight.rpcommands.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TryCommand implements CommandExecutor {

    public String getFormat(){
        return Config.TryCommandFormat.getString();
    }
    public String getSuccessOutput(){
        return Config.TryCommandSuccessful.getString();
    }

    public String getUnSuccessOutput(){
        return Config.TryCommandUnsuccessful.getString();
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.hasPermission("rpcommand.try")) {
            return true;
        }

        if (args.length == 0) {
            return true;
        }

        String message = String.join(" ", args);

        Random random = new Random();

        boolean result = random.nextBoolean();

        String finalMessage = "";

        if (result){
            finalMessage = String.format(getFormat(), player.getName(), message, getSuccessOutput());
        } else {
            finalMessage = String.format(getFormat(), player.getName(), message, getUnSuccessOutput());
        }

        Utils.sendMessage(player, Config.Message_Radius.getInt(), finalMessage);

        return true;
    }
}
