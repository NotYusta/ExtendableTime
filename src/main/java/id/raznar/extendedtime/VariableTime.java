package id.raznar.extendedtime;

import id.raznar.extendedtime.Util.Utils;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class VariableTime {
    private final ExtendableTime plugin;
    private final ConfigHandler config;

    private final Map<World, Double> worldTimeDay = new HashMap<>();
    private final Map<World, Double> worldSpeedDay = new HashMap<>();
    private final Map<World, Double> worldTimeNight = new HashMap<>();
    private final Map<World, Double> worldSpeedNight = new HashMap<>();

    public VariableTime(ExtendableTime main) {
        this.plugin = main;
        this.config = main.config;

        this.setupWorldConfig();
        this.setRepeatingTask();
    }

    private void setupWorldConfig() {
        for(World world: plugin.getServer().getWorlds()) {
            if(!config.getConfig().contains(world.getName())) {
                config.getConfig().addDefault(world.getName() + ".day", 1.0);
                config.getConfig().addDefault(world.getName() + ".night", 1.0);
                config.getConfig().options().copyDefaults(true);
                config.saveConfig();
                worldSpeedDay.put(world, 1.0);
                worldTimeDay.put(world, 0.0);
                worldSpeedNight.put(world, 1.0);
                worldTimeNight.put(world, 0.0);
            } else {
                if (config.getConfig().contains(world.getName() + ".day") && config.getConfig().contains(world.getName() + ".night")) {
                    try {
                        worldSpeedDay.put(world, Math.abs(config.getConfig().getDouble(world.getName() + ".day")));
                        worldTimeDay.put(world, 0.0);
                        worldSpeedNight.put(world, Math.abs(config.getConfig().getDouble(world.getName() + ".night")));
                        worldTimeNight.put(world, 0.0);
                    } catch (Exception e) {
                        Utils.log("World " + world.getName() + " has a bad format!");
                    }
                } else {
                    Utils.log("World " + world.getName() + " has a bad format!");
                }
            }
        }
    }

    private void setRepeatingTask() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (World world : plugin.getServer().getWorlds()) {
                if (world.getTime() > 12000 && worldTimeNight.containsKey(world)) {
                    world.setFullTime(world.getFullTime() - 1);
                    while (worldTimeNight.get(world) > 1) {
                        world.setFullTime(world.getFullTime() + 1);
                        worldTimeNight.put(world, worldTimeNight.get(world) - 1);
                        if (world.getTime() < 12000) {
                            worldTimeNight.put(world, 0.0);
                        }
                    }
                    worldTimeNight.put(world, worldSpeedNight.get(world) + worldTimeNight.get(world));
                } else if (worldTimeDay.containsKey(world)) {
                    world.setFullTime(world.getFullTime() - 1);
                    while (worldTimeDay.get(world) > 1) {
                        world.setFullTime(world.getFullTime() + 1);
                        worldTimeDay.put(world, worldTimeDay.get(world) - 1);
                        if (world.getTime() > 12000) {
                            worldTimeDay.put(world, 0.0);
                        }
                    }
                    worldTimeDay.put(world, worldSpeedDay.get(world) + worldTimeDay.get(world));
                }
            }
        }, 0, 1);
    }
}
