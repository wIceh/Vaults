package me.yesice.vaults;

import me.yesice.vaults.commands.VaultsCommand;
import me.yesice.vaults.listeners.VaultListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Vaults extends JavaPlugin {

    private static Vaults instance;
    private File vaultsFile;
    private FileConfiguration vaultsConfig;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        loadFiles();

        instance = this;

        getCommand("vaults").setExecutor(new VaultsCommand());

        getServer().getPluginManager().registerEvents(new VaultListener(), this);
    }

    private void loadFiles() {
        vaultsFile = new File(getDataFolder().getAbsolutePath() + "/vaults.yml");
        if (!vaultsFile.exists()) {
            try {
                vaultsFile.createNewFile();

                YamlConfiguration config = YamlConfiguration.loadConfiguration(vaultsFile);
                config.createSection("vaults");
                config.save(vaultsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        vaultsConfig = YamlConfiguration.loadConfiguration(vaultsFile);
    }

    public void saveVaultsConfig() {
        try {
            vaultsConfig.save(vaultsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vaults getInstance() {
        return instance;
    }

    public File getVaultsFile() {
        return vaultsFile;
    }

    public FileConfiguration getVaultsConfig() {
        return vaultsConfig;
    }
}
