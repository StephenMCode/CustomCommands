package me.stephenmcode.customCommands.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player) {

            if (!player.hasPermission("customcommands.operator")) {
                TextComponent message = Component.text("You do not have permission to run this command.")
                        .color(TextColor.color(162, 42, 42));

                player.sendMessage(message);

                return true;
            }

            player.setFoodLevel(20);
            player.setSaturation(20);

            TextComponent message = Component.text("You have been fed.")
                    .color(TextColor.color(191, 133, 24));

            player.sendMessage(message);

            return true;
        }

        return false;
    }
}
