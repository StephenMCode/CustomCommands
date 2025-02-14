package me.stephenmcode.customCommands.commands;

import me.stephenmcode.customCommands.CustomCommands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.GameRule.DO_DAYLIGHT_CYCLE;

public class SetTimeCommand implements CommandExecutor {

    private final CustomCommands plugin;

    public SetTimeCommand(CustomCommands plugin) {
        this.plugin = plugin;
    }

    // Incrementally update time
    public void setTime(World world, long targetTick) {
        new BukkitRunnable() {
            long currentTick = world.getTime();
            final long step = 50;

            @Override
            public void run() {
                if (currentTick >= targetTick) {
                    this.cancel();
                }

                currentTick += step;
                currentTick %= 24000;

                world.setTime(currentTick);
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (!(commandSender instanceof Player player)) {
            TextComponent message = Component.text("This command can only be used by a player.")
                    .color(TextColor.color(162, 42, 42));

            commandSender.sendMessage(message);

            return true;
        }

        if (!player.hasPermission("customcommands.operator")) {
            TextComponent message = Component.text("You do not have permission to run this command.")
                    .color(TextColor.color(162, 42, 42));

            player.sendMessage(message);

            return true;
        }

        if (strings.length != 1) {
            return false;
        }

        World world = player.getWorld();
        String time = strings[0].toLowerCase();

        return switch (time) {
            case "day" -> {
                setTime(world, 1000);
                yield true;
            }
            case "night" -> {
                setTime(world, 13000);
                yield true;
            }
            case "noon" -> {
                setTime(world, 6000);
                yield true;
            }
            case "midnight" -> {
                setTime(world, 18000);
                yield true;
            }
            case "freeze" -> {
                world.setGameRule(DO_DAYLIGHT_CYCLE, false);
                yield true;
            }
            case "unfreeze" -> {
                world.setGameRule(DO_DAYLIGHT_CYCLE, true);
                yield true;
            }
            default -> false;
        };
    }
}
