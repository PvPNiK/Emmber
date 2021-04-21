package me.pvpnik.emmber.managers;

import me.pvpnik.emmber.api.spawner.EntitySpawner;
import me.pvpnik.emmber.spawners.continuation.*;
import me.pvpnik.emmber.spawners.gate.*;
import me.pvpnik.emmber.spawners.sandmaze.*;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class SpawnerManager {

    Set<EntitySpawner> spawners = new HashSet<>();

    public SpawnerManager(Plugin plugin) {
        spawners.add(new WeakSkeletonGate1());
        spawners.add(new WeakSkeletonGate2());
        spawners.add(new WeakSkeletonGate3());

        spawners.add(new NormalSkeletonContinuation1());
        spawners.add(new NormalSkeletonContinuation2());
        spawners.add(new NormalSkeletonContinuation3());
        spawners.add(new NormalSkeletonContinuation4());
        spawners.add(new NormalSkeletonContinuation5());
        spawners.add(new NormalSkeletonContinuation6());

        spawners.add(new SandMazeSkeleton1());
        spawners.add(new SandMazeSkeleton2());
        spawners.add(new SandMazeSkeleton3());
        spawners.add(new SandMazeSkeleton4());
        spawners.add(new SandMazeSkeleton5());
        spawners.add(new SandMazeSkeleton6());
        spawners.add(new SandMazeSkeleton7());
        spawners.add(new SandMazeSkeleton8());
        spawners.add(new SandMazeSkeleton9());
        spawners.add(new SandMazeSkeleton10());
        spawners.add(new SandMazeSkeleton11());
        spawners.add(new SandMazeSkeleton12());
        spawners.add(new SandMazeSkeleton13());
        spawners.add(new SandMazeSkeleton14());
    }

    public void removeEntity(UUID uuid) {
        spawners.forEach(entitySpawner -> entitySpawner.removeEntity(uuid));
    }

    public void removeEntities() {
        spawners.forEach(entitySpawner -> removeEntities());
    }


}
