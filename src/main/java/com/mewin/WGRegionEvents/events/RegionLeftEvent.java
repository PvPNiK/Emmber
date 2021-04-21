package com.mewin.WGRegionEvents.events;

import com.mewin.WGRegionEvents.MovementWay;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;

/**
 * Already Left
 */
public class RegionLeftEvent extends RegionEvent {
    public RegionLeftEvent(ProtectedRegion region, Player player, MovementWay movement, PlayerEvent parent) {
        super(region, player, movement, parent);
    }
}
