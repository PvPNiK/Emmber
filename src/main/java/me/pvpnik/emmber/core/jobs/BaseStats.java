package me.pvpnik.emmber.core.jobs;

import java.util.Arrays;

public enum BaseStats {

    one(1, 90, 5, 40, 5, 1),
    two(2, 330, 10, 50, 10, 2),
    three(3, 512, 12, 58, 11, 2),
    four(4, 735, 14, 68, 12, 2),
    five(5, 1012, 17, 79, 13, 2),
    six(6, 1518, 20, 91, 15, 2),
    seven(7, 3001, 23, 104, 17, 2),
    eight(8, 3751, 30, 151, 20, 4),
    nine(9, 4670, 34, 172, 22, 4),
    ten(10, 5817, 38, 195, 24, 4),
    eleven(11, 7345, 43, 217, 27, 4),
    twelve(12, 9389, 48, 242, 30, 4),
    thirteen(13, 11486, 54, 269, 33, 4),
    fourteen(14, 23001, 61, 303, 36, 4),
    fifteen(15, 27412, 82, 432, 43, 6),
    sixteen(16, 32890, 90, 477, 47, 6),
    seventeen(17, 39470, 99, 511, 51, 6),
    eighteen(18, 47365, 110, 543, 54, 6),
    nineteen(19, 57111, 122, 589, 58, 6),
    twenty(20, 68205, 134, 638, 63, 6),
    twentyOne(21, -1, 147, 692, 69, 6),
    twentyTwo(22, 1518, 184, 912, 91, 9);

    public int level;
    public int expForNextLevel;
    public int baseDamage;
    public int baseHealth;
    public int baseArmor;
    public int baseCrit;

    BaseStats(int level, int expForNextLevel, int dmg, int hp, int armor, int crit) {
        this.level = level;
        this.expForNextLevel = expForNextLevel;
        this.baseDamage = dmg;
        this.baseHealth = hp;
        this.baseArmor = armor;
        this.baseCrit = crit;
    }

    public static BaseStats getLevel(int level) {
        for (BaseStats baseStats : BaseStats.values()) {
            if (baseStats.level == level) {
                return baseStats;
            }
        }
        return BaseStats.one;
    }

/*
Level, Exp for next lvl
23, 3001
24, 3751
25, 4670
26, 5817
27, 7345
28, 9389
29, 11486
30, 23001
31, 27412
32, 32890
*/

}
