package me.pvpnik.emmber.core.shops.npcs;

import me.pvpnik.emmber.core.bank.inventory.BankMainInventory;
import me.pvpnik.emmber.core.buyer.BuyerListener;
import me.pvpnik.emmber.core.shops.npcs.shop.*;
import me.pvpnik.emmber.core.stars.AnimationStage;
import me.pvpnik.emmber.core.stars.StarInventory;
import me.pvpnik.emmber.core.stars.StarUpgradeAnimation;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Optional;

public class ShopNPCListener implements Listener {

    private HashMap<String, ShopNPC> stringNpcShops = new HashMap<>();
    private HashMap<Integer, ShopNPC> intNpcShops = new HashMap<>();

    public ShopNPCListener() {
        addNpcShop("141", new ShopNPCArmor());
        addNpcShop("223", new ShopNPCWeapons());
        addNpcShop("396", new ShopNPCBakery());
        addNpcShop("241", new ShopNPCAvi());
        addNpcShop("425", new ShopNPCLevel81());
        addNpcShop("426", new ShopNPCLevel82());
        addNpcShop("427", new ShopNPCLevel83());
    }

    public Optional<ShopNPC> getNpcShop(NPC npc) {
        if (stringNpcShops.containsKey(npc.getName())) {
            return Optional.ofNullable(stringNpcShops.get(npc.getName()));
        }
        return Optional.ofNullable(intNpcShops.get(npc.getId()));
    }

    /**
     * Registers a shop to npc
     * @param id - {@link NPC#getId()} or {@link NPC#getName()}
     * @param shopNPC - NPC's shop
     */
    public void addNpcShop(String id, ShopNPC shopNPC) {
        try {
            int intId = Integer.parseInt(id);
            intNpcShops.put(intId, shopNPC);
        } catch (Exception e) {
            stringNpcShops.put(id, shopNPC);
        }
    }

    @EventHandler
    public void onNPCClick(NPCRightClickEvent e) {
        Player player = e.getClicker();
        if (e.getNPC().getId() == 352) {
            BuyerListener.openBuyerInv(player);
            return;
        }
        if (e.getNPC().getId() == 410 || e.getNPC().getId() == 146) { // bank
            player.openInventory(BankMainInventory.getBankOptionInv());
            return;
        }
        if (e.getNPC().getId() == 430) { // star
            StarUpgradeAnimation.pStage.put(player.getUniqueId(), AnimationStage.OFF);
            player.openInventory(StarInventory.get(player, null));
            return;
        }
        //player.sendMessage("id: " + e.getNPC().getId() + ", name: " + e.getNPC().getName());
        getNpcShop(e.getNPC()).ifPresent(shopNPC -> shopNPC.openShop(player));
    }



}
