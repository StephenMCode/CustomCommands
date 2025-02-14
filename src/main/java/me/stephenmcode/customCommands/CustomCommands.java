package me.stephenmcode.customCommands;

import me.stephenmcode.customCommands.commands.FeedCommand;
import me.stephenmcode.customCommands.commands.GodCommand;
import me.stephenmcode.customCommands.commands.SetTimeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Objects;

public final class CustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {

        System.out.println("Custom Commands plugin by StephenMCode has started!");

        try {
            Field commandMapField = getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(getServer());

            Command timeCommand = commandMap.getCommand("time");
            if (timeCommand != null) {
                timeCommand.unregister(commandMap);
            }
        } catch (Exception err) {
            getSLF4JLogger().error("An error has occurred: {}", String.valueOf(err));
        }

        Objects.requireNonNull(getCommand("god")).setExecutor(new GodCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(getCommand("time")).setExecutor(new SetTimeCommand(this));
    }
}
