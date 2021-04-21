package me.pvpnik.emmber.items;

import me.pvpnik.emmber.Emmber;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.concurrent.atomic.AtomicReference;

public class EmmberSkull {

    private EmmberSkull() {}

    public static ItemStack getSkull(@Nonnull final String id) {
        AtomicReference<ItemStack> itemStack = new AtomicReference<>(new ItemStack(Material.SKULL));
        Emmber.getInstance().headDatabaseAPI.ifPresent(headDatabaseAPI -> {
            itemStack.set(headDatabaseAPI.getItemHead(id));
        });
        return itemStack.get();
    }

    public static ItemStack getSkull(Skulls skulls) {
        return getSkull(skulls.getId());
    }

    public enum Skulls {

        AdmazomHelmet("15014"),
        RavSkeletonHelmet("8188"),
        SandSkeletonHelmet("6863"),
        Bandit("8376");

        private String id;

        Skulls(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

}
