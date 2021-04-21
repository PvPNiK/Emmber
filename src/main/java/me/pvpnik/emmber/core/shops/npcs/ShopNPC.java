package me.pvpnik.emmber.core.shops.npcs;

import me.mattstudios.mfgui.gui.guis.Gui;
import me.pvpnik.emmber.Emmber;
import me.pvpnik.emmber.api.files.Messages;
import me.pvpnik.emmber.api.item.ItemLoreUtils;
import me.pvpnik.emmber.api.jobs.EmmberJob;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ShopNPC {

    default String getInvTitle(String shopsName) {
        Messages messages = Emmber.getInstance().localFileManager.messages;
        String seller = messages.getMessage("stores.seller");
        String name = messages.getMessage("stores.names." + shopsName);
        return name + " " + seller;
    }

    default void buy(ItemStack itemStack, Player player) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }
        int price = ItemLoreUtils.getPrice(itemStack);
        if (price <= 0) {
            return;
        }
        EmmberJob emmberJob = Emmber.getInstance().playerManager.get(player);
        Messages messages = Emmber.getInstance().localFileManager.messages;
        if (emmberJob.getMoney() >= price) {
            if (player.getInventory().firstEmpty() == -1) {
                player.sendMessage(messages.getMessage("stores.noinvspace"));
            } else {
                emmberJob.withdraw(price);
                player.getInventory().addItem(itemStack);
                String part1 = messages.getMessage("stores.bought.part1");
                String part2 = messages.getMessage("stores.bought.part2");
                String part3 = messages.getMessage("stores.bought.part3");
                player.sendMessage(part3 + " " + price + " " + part2 + " " + itemStack.getItemMeta().getDisplayName() + " " + part1);
            }
        } else {
            player.sendMessage(messages.getMessage("stores.nomoney"));
        }
    }

    void openShop(Player player);

}
