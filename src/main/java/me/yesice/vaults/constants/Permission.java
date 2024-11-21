package me.yesice.vaults.constants;

public enum Permission {
    VIP("group.vip"),
    VIP_PLUS("group.vip+"),
    MVP("group.mvp"),
    MVP_PLUS("group.mvp+");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String permission() {
        return permission;
    }
}
