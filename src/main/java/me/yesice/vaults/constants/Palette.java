package me.yesice.vaults.constants;

public enum Palette {
    GREEN("#26FF00"),
    RED("#E30000");

    private final String hex;

    Palette(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }
}
