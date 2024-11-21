package me.yesice.vaults.listeners;

import me.yesice.vaults.objects.Vault;
import me.yesice.vaults.utils.VaultUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VaultListener implements Listener {

    private static final Map<UUID, Vault> vaultMap = new HashMap<>();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().startsWith("Vault ")) return;

        Player player = (Player) event.getPlayer();
        if (!vaultMap.containsKey(player.getUniqueId())) return;

        Vault vault = vaultMap.get(player.getUniqueId());
        VaultUtil.saveVault(vault.getOwnerPlayer(), vault.getVaultType(), event.getView());
    }

    public static Map<UUID, Vault> getVaultMap() {
        return vaultMap;
    }
}
