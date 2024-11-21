package me.yesice.vaults.utils;

import me.yesice.vaults.Vaults;
import me.yesice.vaults.constants.VaultType;
import me.yesice.vaults.objects.Vault;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.io.IOException;
import java.util.Optional;

public class VaultUtil {

    public static Optional<Vault> getVault(OfflinePlayer player, VaultType vaultType) {
        String type = vaultType.name().toLowerCase();
        FileConfiguration config = Vaults.getInstance().getVaultsConfig();
        String inventory = config.getString("vaults." + player.getUniqueId() + "." + type);
        if (inventory == null) return Optional.empty();

        try {
            return Optional.of(new Vault(
                    player.getUniqueId().toString(),
                    vaultType,
                    InventoryUtil.fromBase64(inventory)
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveVault(OfflinePlayer player, VaultType vaultType, InventoryView inventoryView) {
        Inventory inventory = inventoryView.getTopInventory();
        String type = vaultType.name().toLowerCase();

        Vaults plugin = Vaults.getInstance();
        FileConfiguration config = plugin.getVaultsConfig();

        config.set("vaults." + player.getUniqueId() + "." + type, InventoryUtil.toBase64(inventory, inventoryView.getTitle()));
        plugin.saveVaultsConfig();
    }
}
