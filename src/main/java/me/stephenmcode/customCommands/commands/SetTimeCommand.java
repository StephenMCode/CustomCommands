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

    public void setTime(World world, long targetTick) {
        new BukkitRunnable() {
            long currentTick = world.getTime();
            final boolean forward = currentTick < targetTick;
            final long step = 10;

            @Override
            public void run() {
                if ((forward && currentTick >= targetTick) || (!forward && currentTick <= targetTick)) {
                    this.cancel();
                }

                currentTick += (forward ? step : -step);
                world.setTime(currentTick);
            }
        }.runTaskTimer(plugin, 0L, 2L);
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

        switch (time) {
            case "day":
                setTime(world, 1000);
                break;
            case "night":
                setTime(world, 13000);
                break;
            case "noon":
                setTime(world, 6000);
                break;
            case "midnight":
                setTime(world, 18000);
                break;
            case "freeze":
                world.setGameRule(DO_DAYLIGHT_CYCLE, false);
                return true;
            case "unfreeze":
                world.setGameRule(DO_DAYLIGHT_CYCLE, true);
                return true;
            default:
                return false;
        }


        return false;
    }
}
