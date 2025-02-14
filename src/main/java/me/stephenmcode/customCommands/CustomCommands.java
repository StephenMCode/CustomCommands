package me.stephenmcode.customCommands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class CustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Custom Commands plugin by StephenMCode has started!");

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        // /die - kills the player
        if (command.getName().equalsIgnoreCase("die")) {

            if (sender instanceof Player player) {

                player.setHealth(0);

                TextComponent message = Component.text("You have opted to die.")
                                        .color(TextColor.color(174, 62, 62));
                player.sendMessage(message);

            }

        }

        return true;
    }
}
