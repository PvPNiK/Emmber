package me.pvpnik.emmber.items.food;

public enum Foods {

    APPLE(2),
    BAKED_POTATO(2),
    BEETROOT(1),
    BEETROOT_SOUP(3),
    BREAD(2),
    CAKE(1),
    CARROT(1),
    CHORUS_FRUIT(2),
    COOKED_CHICKEN(3),
    COOKED_FISH(2),
    COOKED_MUTTON(3),
    GRILLED_PORK(4),
    COOKED_RABBIT(2),
    COOKIE(1),
    GOLDEN_CARROT(3),
    MELON(1),
    MUSHROOM_SOUP(3),
    POTATO(1),
    PUMPKIN_PIE(4),
    RABBIT_STEW(5),
    RAW_BEEF(1),
    RAW_CHICKEN(1),
    RAW_FISH(1),
    MUTTON(1),
    PORK(1),
    RABBIT(1),
    COOKED_BEEF(4);
    
    private double healthToGive;
    
    Foods(double healthToGive) {
        this.healthToGive = healthToGive;
    }

    public double getHealthToGive() {
        return healthToGive;
    }
}
