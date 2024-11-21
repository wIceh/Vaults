package me.yesice.vaults.constants;

public enum VaultType {
    PLAYER("Player"),
    VIP("VIP"),
    VIP_PLUS("VIP+"),
    MVP("MVP"),
    MVP_PLUS("MVP+");

    private final String displayName;

    VaultType(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
