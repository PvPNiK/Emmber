package me.pvpnik.emmber.api.item;

import me.pvpnik.emmber.Emmber;

public enum ItemAtkSpeed {

    FAST(10, Emmber.getInstance().localFileManager.messages.getMessage("item.atkspeed.fast"), 1),
    REGULAR(16, Emmber.getInstance().localFileManager.messages.getMessage("item.atkspeed.regular"), 2),
    SLOW(22, Emmber.getInstance().localFileManager.messages.getMessage("item.atkspeed.slow"), 3);

    /**
     * Ticks between attacks
     */
    private int speed;
    /**
     * What will be showed at the item's lore
     */
    private String displayName;
    /**
     * Mining Fatigue
     * {@link org.bukkit.potion.PotionEffectType#SLOW_DIGGING}
     */
    private int potionEffectLvl;

    ItemAtkSpeed(int speed, String displayName, int potionEffectLvl) {
        this.setSpeed(speed);
        this.setDisplayName(displayName);
        this.setPotionEffectLvl(potionEffectLvl);
    }

    public static ItemAtkSpeed getEnum(String displayName) {
        for (ItemAtkSpeed ias : values()) {
            if (ias.getDisplayName().equals(displayName)) {
                return ias;
            }
        }

        throw new IllegalArgumentException();
    }

    public static ItemAtkSpeed getEnum(int speed) {
        for (ItemAtkSpeed ias : values()) {
            if (ias.getSpeed() == speed) {
                return ias;
            }
        }

        throw new IllegalArgumentException();
    }

    public int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDisplayName() {
        return displayName;
    }

    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPotionEffectLvl() {
        return potionEffectLvl;
    }

    private void setPotionEffectLvl(int potionEffectLvl) {
        this.potionEffectLvl = potionEffectLvl;
    }

}
