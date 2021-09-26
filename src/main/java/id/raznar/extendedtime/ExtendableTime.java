package id.raznar.extendedtime;

import org.bukkit.plugin.java.JavaPlugin;

public final class ExtendableTime extends JavaPlugin {
    public ConfigHandler config;

    @Override
    public void onEnable() {
        this.config = new ConfigHandler(this);

        new VariableTime(this);
        new Commands(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
