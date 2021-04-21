package me.pvpnik.emmber.api.holograms.temporary;

import me.pvpnik.emmber.Emmber;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HologramDamage {

    private int count = 20;

    public HologramDamage(Player player, int dmg, Location location) {
        /*if (!Emmber.getInstance().protocolManager.isPresent()) {
            serverSide(player, dmg, location);
            return;
        }*/
        serverSide(player, dmg, location);
    }

    private void serverSide(Player player, int dmg, Location location) {
        ArmorStand armorStand = location.getWorld().spawn(location, ArmorStand.class);

        armorStand.setAI(false);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(dmg <= 0 ? ChatColor.RED + "Miss" : ChatColor.RED + "" + dmg);

        /*Hologram hologram = HologramsAPI.createHologram(Emmber.getInstance(), location);

        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.showTo(player);
        visibilityManager.setVisibleByDefault(false);

        hologram.appendTextLine(ChatColor.RED + "" + dmg);*/

        new BukkitRunnable() {
            @Override
            public void run() {
                count--;
                if (count <= 0) {
                    armorStand.remove();
                    cancel();
                    return;
                }
                armorStand.teleport(armorStand.getLocation().add(0, 0.02, 0));
            }
        }.runTaskTimerAsynchronously(Emmber.getInstance(), 2, 2);
    }

}
