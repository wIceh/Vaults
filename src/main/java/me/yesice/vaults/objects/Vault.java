package me.yesice.vaults.objects;

import me.yesice.vaults.constants.VaultType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Vault {

    private final String owner;
    private final VaultType vaultType;
    private final Inventory inventory;

    public Vault(String owner, VaultType vaultType, Inventory inventory) {
        this.owner = owner;
        this.vaultType = vaultType;
        this.inventory = inventory;
    }

    public Vault(String owner, VaultType vaultType) {
        this.owner = owner;
        this.vaultType = vaultType;

        this.inventory = Bukkit.createInventory(null, 9 * 6, Component.text(String.format("Vault (%s)", vaultType.displayName())));
    }

    public String getOwner() {
        return owner;
    }

    public OfflinePlayer getOwnerPlayer() {
        return Bukkit.getOfflinePlayer(UUID.fromString(owner));
    }

    public VaultType getVaultType() {
        return vaultType;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
