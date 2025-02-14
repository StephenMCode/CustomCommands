package me.stephenmcode.customCommands.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player) {

            if (!player.hasPermission("customcommands.operator")) {
                TextComponent message = Component.text("You do not have permission to run this command.")
                        .color(TextColor.color(162, 42, 42));

                player.sendMessage(message);

                return true;
            }

            if (player.isInvulnerable()) {
                player.setInvulnerable(false);

                TextComponent message = Component.text("Godmode disabled.")
                                .color(TextColor.color(145, 45, 39));

                player.sendMessage(message);

            } else {
                player.setInvulnerable(true);

                TextComponent message = Component.text("Godmode enabled.")
                        .color(TextColor.color(59, 145, 70));

                player.sendMessage(message);

            }

            return true;

        }

        return false;
    }
}
