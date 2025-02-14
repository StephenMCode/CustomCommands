package me.stephenmcode.customCommands;

import me.stephenmcode.customCommands.commands.FeedCommand;
import me.stephenmcode.customCommands.commands.GodCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Custom Commands plugin by StephenMCode has started!");

        Objects.requireNonNull(getCommand("god")).setExecutor(new GodCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
    }
}
