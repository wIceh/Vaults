package me.yesice.vaults.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.yesice.vaults.constants.Palette;
import me.yesice.vaults.constants.Permission;
import me.yesice.vaults.constants.VaultType;
import me.yesice.vaults.listeners.VaultListener;
import me.yesice.vaults.objects.Vault;
import me.yesice.vaults.utils.Util;
import me.yesice.vaults.utils.VaultUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

import static net.kyori.adventure.text.Component.text;

public class VaultsGui {

    public void open(Player player) {
        Gui gui = Gui.gui()
                .title(text(Util.toSmallText("vaults")))
                .rows(3)
                .disableAllInteractions()
                .create();

        GuiItem filler = ItemBuilder.from(Material.PAPER)
                .name(text("§r"))
                .model(62)
                .asGuiItem();

        int[] indexes = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        for (int index : indexes)
            gui.setItem(index, filler);

        GuiItem playerVault = ItemBuilder.from(Material.CHEST)
                .name(Component.text(Util.toSmallText("vault"), TextColor.fromHexString(Palette.GREEN.getHex()))
                        .append(Component.text(" §fΘ")))
                .lore(List.of(
                        text("§r"),
                        text("§e" + Util.toSmallText("clicca per aprire il vault"))
                ))
                .asGuiItem(event -> {
                    openVault(player, VaultType.PLAYER);
                });

        GuiItem vipVault = ItemBuilder.from(Material.CHEST)
                .name(Component.text(Util.toSmallText("vault"), TextColor.fromHexString(Palette.GREEN.getHex()))
                        .append(Component.text(" §f\uecc1")))
                .lore(List.of(
                        text("§r"),
                        text("§e" + Util.toSmallText("clicca per aprire il vault"))
                ))
                .asGuiItem(event -> {
                    if (!player.hasPermission(Permission.VIP.permission())) {
                        player.sendMessage(Component.text("Non hai questo vip.", TextColor.fromHexString(Palette.RED.getHex())));
                        return;
                    }

                    openVault(player, VaultType.VIP);
                });

        GuiItem vipPlusVault = ItemBuilder.from(Material.CHEST)
                .name(Component.text(Util.toSmallText("vault"), TextColor.fromHexString(Palette.GREEN.getHex()))
                        .append(Component.text(" §f\uecc2")))
                .lore(List.of(
                        text("§r"),
                        text("§e" + Util.toSmallText("clicca per aprire il vault"))
                ))
                .asGuiItem(event -> {
                    if (!player.hasPermission(Permission.VIP_PLUS.permission())) {
                        player.sendMessage(Component.text("Non hai questo vip.", TextColor.fromHexString(Palette.RED.getHex())));
                        return;
                    }

                    openVault(player, VaultType.VIP_PLUS);
                });

        GuiItem mvpVault = ItemBuilder.from(Material.CHEST)
                .name(Component.text(Util.toSmallText("vault"), TextColor.fromHexString(Palette.GREEN.getHex()))
                        .append(Component.text(" §f\uecc3")))
                .lore(List.of(
                        text("§r"),
                        text("§e" + Util.toSmallText("clicca per aprire il vault"))
                ))
                .asGuiItem(event -> {
                    if (!player.hasPermission(Permission.MVP.permission())) {
                        player.sendMessage(Component.text("Non hai questo vip.", TextColor.fromHexString(Palette.RED.getHex())));
                        return;
                    }

                    openVault(player, VaultType.MVP);
                });

        GuiItem mvpPlusVault = ItemBuilder.from(Material.CHEST)
                .name(Component.text(Util.toSmallText("vault"), TextColor.fromHexString(Palette.GREEN.getHex()))
                        .append(Component.text(" §f\uecc4")))
                .lore(List.of(
                        text("§r"),
                        text("§e" + Util.toSmallText("clicca per aprire il vault"))
                ))
                .asGuiItem(event -> {
                    if (!player.hasPermission(Permission.MVP_PLUS.permission())) {
                        player.sendMessage(Component.text("Non hai questo vip.", TextColor.fromHexString(Palette.RED.getHex())));
                        return;
                    }

                    openVault(player, VaultType.MVP_PLUS);
                });

        gui.setItem(10, playerVault);
        gui.setItem(13, vipVault);
        gui.setItem(14, vipPlusVault);
        gui.setItem(15, mvpVault);
        gui.setItem(16, mvpPlusVault);

        gui.open(player);
    }

    public void openVault(Player player, VaultType type) {
        Vault vault = VaultUtil.getVault(player, type).orElse(null);
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, Component.text(String.format("Vault (%s)", type.displayName())));

        if (vault == null) vault = new Vault(player.getUniqueId().toString(), type, inventory);
        else inventory = vault.getInventory();

        player.openInventory(inventory);
        VaultListener.getVaultMap().put(player.getUniqueId(), vault);
    }
}
