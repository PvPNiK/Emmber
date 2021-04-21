package me.pvpnik.emmber.api.entity;

import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public interface EmmberMob {

    public UUID getUniqueID();
    public int getLevel();
    public double getHp();
    public double getArmor();
    public int getMinDamage();
    public int getMaxDamage();
    public double getMovementSpeed();
    public int getExp();
    public int getMinMoney();
    public int getMaxMoney();

    LivingEntity getLivingEntity();



}
