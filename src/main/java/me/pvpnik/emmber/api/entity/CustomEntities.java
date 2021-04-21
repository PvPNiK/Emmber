package me.pvpnik.emmber.api.entity;

import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.mobs.regular.skeleton.*;
import me.pvpnik.emmber.mobs.regular.bat.*;
import me.pvpnik.emmber.mobs.regular.zombie.*;
import me.pvpnik.emmber.mobs.regular.zombieVillager.*;
import me.pvpnik.emmber.mobs.regular.spider.*;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Method;

public enum CustomEntities {

    /**
     * IDs: https://minecraft.gamepedia.com/Java_Edition_data_value/Pre-flattening/Entity_IDs
     */

    WeakSkeleton("WeakSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, WeakSkeleton.class, getCustomName("weakskeleton")),
    RavSkeleton("RavSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, RavSkeleton.class, getCustomName("ravskeleton")),
    SandSkeleton("SandSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, SandSkeleton.class, getCustomName("sandskeleton")),
    DrakenSkeleton("DrakenSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, DrakenSkeleton.class, getCustomName("drakenskeleton")),
    UpgradedSkeleton("UpgradedSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, UpgradedSkeleton.class, getCustomName("upgradedskeleton")),
    NormalSkeleton("NormalSkeleton", 51, EntityType.SKELETON, EntitySkeleton.class, NormalSkeleton.class, getCustomName("normalskeleton")),

    SkeletonManager("SkeletonManager", 6, EntityType.STRAY, EntitySkeletonStray.class, SkeletonManager.class, getCustomName("skeletonmanager")),

    Admazom("Admazom", 54, EntityType.ZOMBIE, EntityZombie.class, Admazom.class, getCustomName("admazom")),
    WeakZombie("WeakZombie", 54, EntityType.ZOMBIE, EntityZombie.class, WeakZombie.class, getCustomName("weakzombie")),
    NormalZombie("NormalZombie", 54, EntityType.ZOMBIE, EntityZombie.class, NormalZombie.class, getCustomName("normalzombie")),
    //Admazom("Admazom", 51, EntityType.ZOMBIE, EntityZombie.class, Admazom.class, getCustomName("skeleton")),

    WeakGoblinmau("WeakGoblinmau", 27, EntityType.ZOMBIE_VILLAGER, EntityZombieVillager.class, WeakGoblinmau.class, getCustomName("weakgoblinmau")),
    FighterGoblin("FighterGoblin", 27, EntityType.ZOMBIE_VILLAGER, EntityZombieVillager.class, FighterGoblin.class, getCustomName("fightergoblin")),
    CaveGoblin("CaveGoblin", 27, EntityType.ZOMBIE_VILLAGER, EntityZombieVillager.class, CaveGoblin.class, getCustomName("cavegoblin")),
    GoblinManager("GoblinManager", 27, EntityType.ZOMBIE_VILLAGER, EntityZombieVillager.class, GoblinManager.class, getCustomName("goblinmanager")),

    CaveSpider("CaveSpider", 59, EntityType.SPIDER, EntityCaveSpider.class, CaveSpider.class, getCustomName("cavespider")),
    SandSpider("SandSpider", 52, EntityType.SPIDER, EntitySpider.class, SandSpider.class, getCustomName("sandspider")),

    PoisonousBat("PoisonousBat", 65, EntityType.BAT, EntityBat.class, PoisonousBat.class, getCustomName("poisonousbat"));


    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends Entity> nmsClass;
    private Class<? extends Entity> customClass;
    private MinecraftKey key;
    private MinecraftKey oldKey;
    private String customName;

    CustomEntities(String name, int id, EntityType entityType, Class<? extends Entity> nmsClass, Class<? extends Entity> customClass, String customName) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
        this.key = new MinecraftKey(name);
        this.oldKey = EntityTypes.b.b(nmsClass);
        this.customName = customName;
    }

    public static void registerEntities() { for (CustomEntities ce : CustomEntities.values()) ce.register(); }
    public static void unregisterEntities() { for (CustomEntities ce : CustomEntities.values()) ce.unregister(); }

    private void register() {
        EntityTypes.d.add(key);
        EntityTypes.b.a(id, key, customClass);
    }

    private void unregister() {
        EntityTypes.d.remove(key);
        EntityTypes.b.a(id, oldKey, nmsClass);
    }

    public void spawn(Location location) {
        try {
            Method method = customClass.getMethod("spawn", Location.class);
            method.invoke(null, location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCustomName(String mobIdinMessagesFile) {
        return Emmber.getInstance().localFileManager.messages.getMessage("mobs." + mobIdinMessagesFile);
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Class<?> getCustomClass() {
        return customClass;
    }

    public String getCustomName() {
        return customName;
    }
}