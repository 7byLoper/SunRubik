package by.loper.sunrubik;

import by.loper.sunrubik.commands.SunRubikCommand;
import by.loper.sunrubik.events.Events;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SunRubik extends JavaPlugin {
    private static SunRubik instance;
    public static PlayerPointsAPI ppAPI;
    Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            ppAPI = PlayerPoints.getInstance().getAPI();
        }else{
            getLogger().severe(String.format("[%s] - Disabled due to no PlayerPoints dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.log.warning( "");
        this.log.warning(ChatColor.GREEN + "  |SunRubik Enabled   ");
        this.log.warning( "");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.0    ");
        this.log.warning( "");
        getCommand("sunRubik").setExecutor(new SunRubikCommand());
        getCommand("sunRubik").setTabCompleter(new SunRubikCommand());
        Bukkit.getPluginManager().registerEvents(new Events(),this);
    }

    @Override
    public void onDisable() {

        this.log.warning( "");
        this.log.warning(ChatColor.GREEN + "  |SunRubik Disabled   ");
        this.log.warning( "");
        this.log.warning(ChatColor.GREEN + "  |Version : 1.0    ");
        this.log.warning( "");

    }
    public static SunRubik getInstance() {
        return instance;
    }
}
